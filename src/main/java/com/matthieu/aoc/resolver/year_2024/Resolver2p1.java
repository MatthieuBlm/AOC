package com.matthieu.aoc.resolver.year_2024;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.matrix.Row;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver2p1 implements Resolver {

    protected Matrix<Integer> data;
    protected List<Row<Integer>> safeRow;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.data = new Matrix<>(values, Integer::parseInt);
    }

    @Override
    public boolean solve() throws Exception {
        safeRow = this.data.getRows().stream().filter(this::isSafe).toList();

        return true;
    }

    @Override
    public String get() {
        return this.safeRow.size() + "";
    }

    protected boolean isSafe(Row<Integer> row) {
        int way = 0; // positive -> increasing, negative -> decreasing

        for (int i = 0; i < row.size() - 1; i++) {
            int difference = row.get(i) - row.get(i + 1);

            if (way == 0) {
                way = difference;
            }

            if (difference == 0) {
                return false;
            } else if ((isPositive(way) && isNegative(difference)) || (isNegative(way) && isPositive(difference))) {
                return false;
            } else if (Math.abs(difference) > 3) {
                return false;
            }
        }

        return true;
    }

    protected static boolean isPositive(int i) {
        return i > 0;
    }

    protected static boolean isNegative(int i) {
        return i < 0;
    }

}
