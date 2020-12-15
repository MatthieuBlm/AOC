package com.matthieu.aoc.resolver.year_2020;

import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver10p1 implements Resolver {

	protected List<Integer> adapters;
	protected Long result;
	private Integer currentJoltage;
	private Integer oneJolts;
	private Integer threeJolts;
	
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		adapters = values.stream()
						.map(Integer::valueOf)
						.sorted((i1, i2) -> i1.compareTo(i2))
						.collect(Collectors.toList());
		
		adapters.add(0, 0);
		adapters.add(adapters.get(adapters.size() - 1) + 3);
		
		this.currentJoltage = 0;
		this.oneJolts = 0;
		this.threeJolts = 0;
	}

	@Override
	public boolean solve() throws SolveException {
		
		for (int i = 0; i < adapters.size(); i++) {
			Integer adapter = this.adapters.get(i);
			
			if((adapter -  currentJoltage) == 3) {
				threeJolts++;
			} else if((adapter - currentJoltage) == 1) {
				oneJolts++;
			}
			
			currentJoltage = adapter;
		}

		result = (long) (oneJolts * threeJolts);
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(oneJolts * threeJolts);
	}

}
