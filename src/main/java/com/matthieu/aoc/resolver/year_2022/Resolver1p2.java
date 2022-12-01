package com.matthieu.aoc.resolver.year_2022;

import com.matthieu.aoc.exception.SolveException;

public class Resolver1p2 extends Resolver1p1 {

	@Override
	public boolean solve() throws SolveException {
		
		for (int i = 0; i < 3; i++) {
			int maxId = this.findMaxId();
			
			result += calories.get(maxId);
			
			calories.remove(maxId);
		}
		
		return true;
	}


}
