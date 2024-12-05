package com.matthieu.aoc.resolver.year_2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver5p1 implements Resolver {

    protected List<Duo<Integer, Integer>> ordering;
    protected List<List<Integer>> updates;
    protected long sum;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.ordering = new ArrayList<>();
        this.updates = new ArrayList<>();
        boolean whiteLineSaw = false;

        for (String line : values) {
            if (line.equals("")) {
                whiteLineSaw = true;
            } else {
                if (!whiteLineSaw) {
                    String[] order = line.split("\\|");
                    ordering.add(new Duo<>(Integer.parseInt(order[0]), Integer.parseInt(order[1])));
                } else {
                    String[] update = line.split(",");
                    updates.add(Arrays.asList(update).stream().map(Integer::valueOf)
                            .collect(Collectors.toCollection(ArrayList::new)));
                }
            }
        }
    }

    @Override
    public boolean solve() throws Exception {
        this.sum = updates.stream()
                .filter(this::isValidUpdate)
                .map(this::getMiddle)
                .mapToInt(Integer::intValue)
                .sum();
        return true;
    }

    @Override
    public String get() {
        return sum + "";
    }

    protected boolean isValidUpdate(List<Integer> update) {
        return isNotAtRightPlace(update) == null;
    }

    protected Integer isNotAtRightPlace(List<Integer> update) {
        for (int i = 0; i < update.size(); i++) {
            Integer n = update.get(i);

            for (Duo<Integer, Integer> order : getRelatedOrdering(n)) {
                if ((order.a() == n && isBefore(update, i, order.b()) != -1) ||
                        (order.b() == n && isAfter(update, i, order.a()) != -1)) {
                    return n;
                }
            }
        }

        return null;
    }

    protected Integer getMiddle(List<Integer> update) {
        return update.get((int) Math.floor(update.size() / 2d));
    }

    protected int isAfter(List<Integer> update, int index, Integer shouldBeAfter) {
        for (int i = index + 1; i < update.size(); i++) {
            if (update.get(i) == shouldBeAfter) {
                return i;
            }
        }

        return -1;
    }

    protected int isBefore(List<Integer> update, int index, Integer shouldBeBefore) {
        for (int i = index - 1; i >= 0; i--) {
            if (update.get(i) == shouldBeBefore) {
                return i;
            }
        }

        return -1;
    }

    protected List<Duo<Integer, Integer>> getRelatedOrdering(Integer n) {
        return this.ordering.stream().filter(o -> o.a() == n || o.b() == n).toList();
    }

}
