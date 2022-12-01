package com.matthieu.aoc.resolver.year_2022;

import com.matthieu.aoc.exception.SolveException;

public class Resolver1p2 extends Resolver1p1 {

	@Override
	public boolean solve() throws SolveException {
		
		result = calories.stream()
							.sorted((l1, l2) -> l1.compareTo(l2))
							.skip(calories.size() - 3)
							.mapToInt(Long::intValue)
							.sum();
		
		return true;
	}
}
