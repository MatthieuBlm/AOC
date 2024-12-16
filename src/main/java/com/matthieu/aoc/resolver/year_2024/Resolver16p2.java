package com.matthieu.aoc.resolver.year_2024;

import java.util.LinkedList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Direction;
import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver16p2 extends Resolver16p1 {

    private int minDistance;
    private Matrix<Boolean> bestTiles;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        super.prepareData(values);
        this.bestTiles = new Matrix<>(maze.getWidth(), maze.getHeight(), () -> Boolean.FALSE);
    }

    @Override
    public boolean solve() throws Exception {
        super.solve();
        minDistance = this.distances.get(start);

        this.maze.forEachNeigthboursCross(start.x(), start.y(), (xn, yn, cn) -> {
            if (cn == '.') {
                explore(start, new Point(xn, yn), 1, Direction.fromVector(start.x() - xn, start.y() - yn), new LinkedList<>());
            }
        });

        return true;
    }

    @Override
    public String get() {
        return this.bestTiles.stream().filter(b -> b.booleanValue()).count() + "";
    }

    private void browse(Point current) {
        // tileOnPath++;

        if (current.equals(end)) {
            return;
        }

        List<Duo<Integer, Integer>> neightbours = Matrix.getNeigthboursCrossCoords(current.x(), current.y());
        final int currentDistance = this.distances.get(current);

        neightbours.forEach(n -> {
            if (maze.get(n.x(), n.y()) != '.' ||
                    (distances.get(n.x(), n.y()) - currentDistance != -1
                    && distances.get(n.x(), n.y()) - currentDistance != -1000
                            && Math.abs(distances.get(n.x(), n.y()) - currentDistance) != -1001)) {
                return;
            }

            browse(new Point(n.x(), n.y()));
        });

    }

    protected void explore(
            Point from,
            Point current,
            int currentDistance,
            Direction currentDirection,
            List<Point> currentPath
    ) {
        if (current.equals(end) && currentDistance == minDistance) {
            currentPath.stream().forEach(p -> bestTiles.set(p, Boolean.TRUE));
            return;
        }

        this.maze.forEachNeigthboursCross(current.x(), current.y(), (xn, yn, cn) -> {
            if (cn != '.' || (xn == from.x() && yn == from.y())) {
                return;
            }

            int delta = Math.abs(this.distances.get(xn, yn) - this.distances.get(current));

            if (delta < 2000 && !currentPath.contains(current)) {
                Direction nextDirection = Direction.fromVector(current.x() - xn, current.y() - yn);
                int turn = currentDirection == nextDirection ? 0 : 1;
                List<Point> newPath = new LinkedList<>(currentPath);
                newPath.add(current);

                this.explore(current, new Point(xn, yn), currentDistance + 1 + turn * 1000, nextDirection, newPath);
            }
        });
    }

    private String getPrintableDistance() {
        Matrix<String> print = new Matrix<>(maze.getWidth(), maze.getHeight(), () -> "######");

        this.distances.cellStream()
                .filter(cell -> cell.value() != Integer.MAX_VALUE)
                .forEach(cell -> print.set(cell.x(), cell.y(), String.format("|%04d|", cell.value())));

        return print.toString();
    }

}
