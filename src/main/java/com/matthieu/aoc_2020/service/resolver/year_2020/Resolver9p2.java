package com.matthieu.aoc_2020.service.resolver.year_2020;

import java.util.TreeSet;

import com.matthieu.aoc_2020.exception.SolveException;

public class Resolver9p2 extends Resolver9p1 {

	private Long result = 0L;
	
	@Override
	public boolean solve() throws SolveException {
		super.solve();
		
		TreeSet<Long> sumedNumber = new TreeSet<>();
		Long sum;
		int j;

		for (int i = 0; i < code.size(); i++) {
			sumedNumber.clear();
			sum = 0L;
			j = 0;
					
			while(sum < super.firstIntruder) {
				Long toSum = this.code.get(i + j);
				sum += toSum;
				sumedNumber.add(toSum);
				
				j++;
			}
			
			if(sum.equals(super.firstIntruder)) {
				result = sumedNumber.first() + sumedNumber.last();
				return true;
			}
			
			
		}
		
		return false;
	}
	
	@Override
	public String get() {
		return String.valueOf(this.result);
	}

}
