package com.matthieu.aoc.resolver.year_2024;

public class Resolver1p2 extends Resolver1p1 {

    @Override
    public boolean solve() throws Exception {

        sum = this.left.stream()
                .mapToLong(n -> this.right.stream().filter(n2 -> n2.intValue() == n.intValue()).count() * n)
                .sum();

        return true;
    }
}
