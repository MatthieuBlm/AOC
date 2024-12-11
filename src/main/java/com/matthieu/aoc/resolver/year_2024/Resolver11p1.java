package com.matthieu.aoc.resolver.year_2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver11p1 implements Resolver {

    protected List<Long> stones;
    protected int blinkingTimes;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.stones = Arrays.asList(values.get(0).split(" ")).stream().map(Long::parseLong)
                .collect(Collectors.toCollection(ArrayList::new));
        this.blinkingTimes = 25;
    }

    @Override
    public boolean solve() throws Exception {

        for (int i = 0; i < blinkingTimes; i++) {
            moveStones();
            System.out.println("Stone moved " + i + " times. Have " + stones.size() + " stones");
        }

        return true;
    }

    @Override
    public String get() {
        return this.stones.size() + "";
    }

    protected void moveStones() {
        for (int i = 0; i < stones.size(); i++) {
            Long stone = this.stones.get(i);

            if (stone == 0) {
                setToOne(i);
            } else if (stone.toString().length() % 2 == 0) {
                splitStone(i);
                i++;
            } else {
                multiply(i);
            }
        }
    }

    protected void splitStone(int index) {
        String stoneValue = this.stones.get(index).toString();
        Long leftValue = Long.valueOf(stoneValue.substring(0, stoneValue.length() / 2).toString());
        Long rightValue = Long.valueOf(stoneValue.substring(stoneValue.length() / 2).toString());

        this.stones.set(index, leftValue);
        this.stones.add(index + 1, rightValue);
    }

    protected void multiply(int index) {
        this.stones.set(index, this.stones.get(index) * 2024l);
    }

    protected void setToOne(int index) {
        this.stones.set(index, 1l);
    }

}
