package com.matthieu.aoc.resolver.year_2024;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver1p1 implements Resolver {

    protected List<Integer> left;
    protected List<Integer> right;
    protected long sum;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.left = new ArrayList<>();
        this.right = new ArrayList<>();

        values.stream()
                .map(l -> l.replaceAll(" +", " "))
                .map(l -> l.split(" "))
                .forEach(l -> {
                    left.add(Integer.parseInt(l[0]));
                    right.add(Integer.parseInt(l[1]));
                });

        this.left.sort(Integer::compare);
        this.right.sort(Integer::compare);
    }

    @Override
    public boolean solve() throws Exception {

        for (int i = 0; i < this.left.size(); i++) {
            sum += Math.abs(this.right.get(i) - this.left.get(i));
        }

        return true;
    }

    @Override
    public String get() {
        return sum + "";
    }


}
