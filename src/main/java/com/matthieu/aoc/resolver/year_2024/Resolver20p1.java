package com.matthieu.aoc.resolver.year_2024;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.graph.DijkstraSolver;
import com.matthieu.aoc.model.graph.Node;
import com.matthieu.aoc.model.matrix.CharMatrix;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver20p1 implements Resolver {

    protected CharMatrix map;
    protected Matrix<Node> graph;
    protected int raceLenght;
    protected int minTimeSave;
    protected Set<Point> relevantCheats;
    protected Point start;
    protected Point end;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.map = new CharMatrix(values);
        this.graph = new Matrix<>(map.getWidth(), map.getHeight(), (x, y) -> this.map.get(x, y) != '#' ? new Node() : null);
        this.minTimeSave = 100;
        this.relevantCheats = new HashSet<>();

        this.start = this.map.cellStream().filter(cell -> cell.value() == 'S').map(cell -> new Point(cell.x(), cell.y()))
                .findAny().orElseThrow();
        this.end = this.map.cellStream().filter(cell -> cell.value() == 'E').map(cell -> new Point(cell.x(), cell.y()))
                .findAny().orElseThrow();

        this.map.set(start, '.');
        this.map.set(end, '.');

        linkNodes();
        DijkstraSolver.calculateShortestPathFromSource(this.graph.get(end));
        this.raceLenght = this.graph.get(start).getDistance();
    }

    @Override
    public boolean solve() throws Exception {

        this.map.forEach((x, y, c) -> {
            if (c != '#') {
                return;
            }
            // Is in wall

            this.relevantCheats.addAll(goThroughWall(new Point(x, y)));
        });

        return true;
    }

    protected Set<Point> goThroughWall(Point from) {
        Set<Point> cheatArrivals = new HashSet<>();

        this.map.forEachNeigthboursCross(from.x(), from.y(), (nx, ny, nc) -> {
            if (nc != '.') {
                return;
            }
            // Is in path

            Point cheatDirection = new Point(nx - from.x(), ny - from.y());
            Point beforeCheatPos = new Point(from.x() - cheatDirection.x(), from.y() - cheatDirection.y());
            Point cheatArrival = new Point(nx, ny);

            // Not through 1 tile wall
            try {
                if (this.map.get(beforeCheatPos) != '.') {
                    return;
                }
            } catch (Exception e) {
                // Out of map
                return;
            }

            int cheatStartDistance = this.graph.get(beforeCheatPos.x(), beforeCheatPos.y()).getDistance();
            int cheatArrivalDistance = this.graph.get(cheatArrival).getDistance();

            if (cheatStartDistance - cheatArrivalDistance - 2 >= this.minTimeSave) {
                cheatArrivals.add(new Point(nx, ny));
            }
        });
        
        return cheatArrivals;
    }

    @Override
    public String get() {
        return relevantCheats.size() + "";
    }

    protected void linkNodes() {
        this.graph.cellStream().forEach(cell -> {
            if (cell.value() != null) {
                this.graph.forEachNeigthboursCross(cell.x(), cell.y(), (xn, yn, cn) -> {
                    if (cn != null) {
                        cell.value().addDestination(cn, 1);
                    }
                });
            }
        });
    }

    protected String printMap(Point toRemove) {
        CharMatrix clone = new CharMatrix(map.getWidth(), map.getHeight(), map::get);

        if (toRemove != null) {
            clone.set(toRemove, 'X');
        }

        return clone.toString();
    }

}
