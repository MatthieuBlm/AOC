package com.matthieu.aoc.resolver.year_2024;

import java.util.List;
import java.util.regex.Pattern;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver3p1 implements Resolver {

    protected String memory;
    protected Pattern pattern;
    protected long sum;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.memory = values.stream().reduce((acc, value) -> acc + value).orElseThrow();
        this.pattern = Pattern.compile("mul\\([0-9]+,[0-9]+\\)");
        this.sum = 0;
    }

    @Override
    public boolean solve() throws Exception {
        sum = pattern.matcher(memory).results()
                .map(result -> result.group(0))
                .mapToLong(this::mul)
                .sum();

        return true;
    }

    @Override
    public String get() {
        return sum + "";
    }

    protected long mul(String expression) {
        String[] operands = expression.replaceAll("mul\\(", "").replaceAll("\\)", "").split(",");

        return Long.parseLong(operands[0]) * Long.parseLong(operands[1]);
    }

}
