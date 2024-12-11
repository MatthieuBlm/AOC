package com.matthieu.aoc.resolver.year_2024;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;

public class Resolver11p2 extends Resolver11p1 {

    protected Map<Long, Long> stones; // Map Stone value with total count

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.stones = Arrays.asList(values.get(0).split(" ")).stream()
                .map(Long::parseLong)
                .collect(Collectors.toMap(Function.identity(), v -> 1l));
        this.blinkingTimes = 75;
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
        return this.stones.values().stream().mapToLong(Long::longValue).sum() + "";
    }

    @Override
    protected void moveStones() {
        Map<Long, Long> newStones = new HashMap<>();

        for (Map.Entry<Long, Long> stone : this.stones.entrySet()) {

            if (stone.getKey() == 0) {
                addStones(1l, stone.getValue(), newStones);
            } else if (stone.getKey().toString().length() % 2 == 0) {
                splitStone(stone.getKey(), stone.getValue(), newStones);
            } else {
                multiply(stone.getKey(), stone.getValue(), newStones);
            }
        }

        stones = newStones;
    }

    protected void splitStone(Long index, Long amount, Map<Long, Long> stones) {
        String stoneValue = index.toString();
        Long leftValue = Long.valueOf(stoneValue.substring(0, stoneValue.length() / 2).toString());
        Long rightValue = Long.valueOf(stoneValue.substring(stoneValue.length() / 2).toString());

        addStones(leftValue, amount, stones);
        addStones(rightValue, amount, stones);
    }

    protected void addStones(Long index, Long amount, Map<Long, Long> stones) {
        if (stones.get(index) == null) {
            stones.put(index, amount);
        } else {
            stones.put(index, stones.get(index) + amount);
        }
    }

    protected void multiply(Long index, Long amount, Map<Long, Long> stones) {
        addStones(index * 2024, amount, stones);
    }
}
