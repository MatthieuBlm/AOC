package com.matthieu.aoc.resolver.year_2024;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.model.matrix.Row;

public class Resolver2p2 extends Resolver2p1 {

    @Override
    protected boolean isSafe(Row<Integer> row) {
        if (super.isSafe(row)) {
            return true;
        }

        for (int i = 0; i < row.size(); i++) {
            List<Integer> newRow = new ArrayList<>(row.get());
            newRow.remove(i);

            if (super.isSafe(new Row<>(newRow))) {
                return true;
            }
        }

        return false;

    }
}
