package com.matthieu.aoc.resolver.year_2024;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.graph.DijkstraSolver;
import com.matthieu.aoc.model.graph.Node;
import com.matthieu.aoc.model.matrix.CharMatrix;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.Extractor;

public class Resolver18p1 implements Resolver {

    protected final int width = 71;
    protected final int height = 71;

    protected Matrix<Node> memory;
    protected List<Point> bytesFalling;
    protected int toTake;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.memory = new Matrix<Node>(width, height, () -> new Node());
        this.bytesFalling = values.stream()
                .map(Extractor::extractNumbers)
                .map(numbers -> new Point(numbers.get(0).intValue(), numbers.get(1).intValue()))
                .toList();
        this.toTake = 1024;
    }

    @Override
    public boolean solve() throws Exception {

        makeBytesFall();
        linkNodes();

        DijkstraSolver.calculateShortestPathFromSource(memory.get(0, 0));

        return true;
    }

    @Override
    public String get() {
        return this.memory.get(width - 1, height - 1).getDistance() + "";
    }

    protected void makeBytesFall() {
        for (int i = 0; i < toTake; i++) {
            makeByteFall(bytesFalling.get(i));
        }
    }

    protected void makeByteFall(Point fallenByte) {
        this.memory.set(fallenByte, null);
    }

    protected void linkNodes() {
        this.memory.cellStream().forEach(cell -> {
            if (cell.value() != null) {
                this.memory.forEachNeigthboursCross(cell.x(), cell.y(), (xn, yn, cn) -> {
                    if (cn != null) {
                        cell.value().addDestination(cn, 1);
                    }
                });
            }
        });
    }

    protected String printMap() {
        CharMatrix print = new CharMatrix(memory.getWidth(), memory.getHeight(), () -> '.');

        this.memory.cellStream().forEach(cell -> {
            if (cell.value() == null) {
                print.set(cell.x(), cell.y(), '#');
            }
        });

        return print.toString();
    }

}
