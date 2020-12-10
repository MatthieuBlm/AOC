package com.matthieu.aoc_2020.service.resolver.year_2020;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc_2020.exception.SolveException;

public class Resolver10p2 extends Resolver10p1 {

	@Override
	public boolean solve() throws SolveException {
		List<Integer> possibilities = new ArrayList<>();
		int sequence = 1;
		
		// For each adapter
		for (int i = 0; i < super.adapters.size() - 1; i += sequence - 1) {
			sequence = 1;
			
			for (int j = 1; j < 5 && (j + i) < adapters.size(); j++)
				sequence += adapters.get(i + j) == adapters.get(i) + j ? 1 : 0;
			
			if(sequence == 3)
				possibilities.add(2);
			else if(sequence == 4)
				possibilities.add(4);
			else if(sequence == 5)
				possibilities.add(7);
			else
				i++;
			
		}
		result = possibilities.stream().mapToLong(i -> i).reduce(1, (a, b) -> a * b);
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.result);
	}
}
