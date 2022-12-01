package com.matthieu.aoc.resolver.year_2022;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver1p1 implements Resolver {
	
	List<Long> calories;
	long result = 0l;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.calories = new ArrayList<>();
		
		long acc = 0;
		
		for (String string : values) {
			if(string.equals("")) {
				calories.add(acc);
				acc = 0;
			} else {
				acc += Long.parseLong(string);
			}
		}
	}

	@Override
	public boolean solve() throws SolveException {
		this.result = this.calories.stream()
									.sorted((l1, l2) -> l2.compareTo(l1))
									.findFirst()
									.get();
		return true;
	}

	@Override
	public String get() {
		return result + "";
	}

	protected int findMaxId() {
		long max = 0;
		int maxId = 0;
		
		for (int i = 0; i < calories.size(); i++) {
			long c = calories.get(i);
			
			if(c > max) {
				max = c;
				maxId = i;
			}
		}
		
		return maxId;
	}
}
