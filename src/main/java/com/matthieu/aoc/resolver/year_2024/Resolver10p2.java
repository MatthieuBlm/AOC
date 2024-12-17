package com.matthieu.aoc.resolver.year_2024;

public class Resolver10p2 extends Resolver10p1 {

    @Override
    public String get() {
        return trails.values().stream()
        		.mapToLong(trails -> trails.stream().map(trail -> trail.getLast()).count())
        		.sum() + "";
    }
}
