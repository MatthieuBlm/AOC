package com.matthieu.aoc_2020.service.resolver.year_2020;

import com.matthieu.aoc_2020.exception.SolveException;

public class Resolver10p2 extends Resolver10p1 {

	@Override
	public boolean solve() throws SolveException {
		result = 1L;
		int[] increment = {1, 1, 2, 4, 7};
		
		for (int i = 0, sequence = 1; i < super.adapters.size() - 1; i += sequence - 1) {
			sequence = 1;
			
			for (int j = 1; j < 5 && (j + i) < adapters.size(); j++)
				sequence += adapters.get(i + j) == adapters.get(i) + j ? 1 : 0;
			
			result *= increment[sequence - 1];
			
			if(sequence < 3) i++;
		}
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.result);
	}
}