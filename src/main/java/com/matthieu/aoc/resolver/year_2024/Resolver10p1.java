package com.matthieu.aoc.resolver.year_2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.matrix.Cell;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver10p1 implements Resolver {

    protected Matrix<Integer> map;
    protected List<Cell<Integer>> zeroCells;
    protected Map<Cell<Integer>, List<LinkedList<Cell<Integer>>>> trails;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.map = new Matrix<>(values, Integer::parseInt);
        this.zeroCells = this.map.cellStream().filter(cell -> cell.value() == 0).toList();
        this.trails = new HashMap<>();
    }

    @Override
    public boolean solve() throws Exception {
        this.zeroCells.stream()
                .forEach(origin -> {
                	LinkedList<Cell<Integer>> path = new LinkedList<>();
                	path.add(origin);
                	explore(origin, origin.x(), origin.y(), origin.x(), origin.y(), path);
                });
        return true;
    }

    @Override
    public String get() {
        return trails.values().stream()
        		.mapToLong(trails -> trails.stream().map(trail -> trail.getLast()).distinct().count())
        		.sum() + "";
    }

    protected void explore(Cell<Integer> origin, int fromX, int fromY, int x, int y, LinkedList<Cell<Integer>> path) {
        int currentHeight = this.map.get(x, y);

        this.map.forEachNeigthboursCross(x, y, (xn, yn, nHeight) -> {
            if ((fromX == xn && fromY == yn) || nHeight - currentHeight != 1) {
                return;
            }

            LinkedList<Cell<Integer>> newPath = new LinkedList<Cell<Integer>>();
            newPath.add(new Cell<>(xn, yn, nHeight));

            if (nHeight == 9) {
                this.trails.computeIfAbsent(origin, k -> new ArrayList<>()).add(newPath);
                return;
            }

            explore(origin, x, y, xn, yn, newPath);
        });
    }
}
