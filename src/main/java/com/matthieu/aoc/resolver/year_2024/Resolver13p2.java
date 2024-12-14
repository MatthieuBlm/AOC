package com.matthieu.aoc.resolver.year_2024;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;

public class Resolver13p2 extends Resolver13p1 {

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.initliazeClawConfigs(values, 10000000000000l);
    }
}
