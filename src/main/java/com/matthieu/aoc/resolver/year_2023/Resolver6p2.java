package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;

public class Resolver6p2 extends Resolver6p1 {

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.times = Arrays.asList(Long.parseLong(values.get(0).replace("Time:", "").replace(" ", "")));
		this.distances = Arrays.asList(Long.parseLong(values.get(1).replace("Distance:", "").replace(" ", "")));
		this.winPossibilities = new ArrayList<>();
	}
}
