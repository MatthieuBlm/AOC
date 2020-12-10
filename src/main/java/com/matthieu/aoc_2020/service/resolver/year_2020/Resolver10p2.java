package com.matthieu.aoc_2020.service.resolver.year_2020;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc_2020.exception.SolveException;

public class Resolver10p2 extends Resolver10p1 {

	private Long result;
	
	@Override
	public boolean solve() throws SolveException {
		List<Integer> possibilities = new ArrayList<>();
		result = 0L;
		
		// Add device
		adapters.add(0, 0);
		adapters.add(adapters.get(adapters.size() - 1) + 3);

		int consecutif = 1;
		
		// For each adapter
		for (int i = 0; i < super.adapters.size() - 1; i += consecutif - 1) {
			Integer adapter = adapters.get(i);
			consecutif = 1;
			
			Integer a = (i + 1) < adapters.size() ? adapters.get(i + 1) : null;
			Integer b = (i + 2) < adapters.size() ? adapters.get(i + 2) : null;
			Integer c = (i + 3) < adapters.size() ? adapters.get(i + 3) : null;
			Integer d = (i + 4) < adapters.size() ? adapters.get(i + 4) : null;
			
			if(a != null && a == adapter + 1)
				consecutif++;
			if(b != null && b == adapter + 2)
				consecutif++;
			if(c != null && c == adapter + 3)
				consecutif++;
			if(d != null && d == adapter + 4)
				consecutif++;
			
			if(consecutif == 3) {
				possibilities.add(2);
			} else if(consecutif == 4) {
				possibilities.add(4);
			}else if(consecutif == 5) {
				possibilities.add(7);
			} else {
				i++;
			}
			
		}
		
		result = possibilities.stream().mapToLong(i -> i).reduce(1, (a, b) -> a * b);
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.result);
	}
}
