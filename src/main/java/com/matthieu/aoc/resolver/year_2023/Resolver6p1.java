package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.Extractor;

public class Resolver6p1 implements Resolver {

	protected List<Long> times;
	protected List<Long> distances;
	protected List<Long> winPossibilities;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.times = Extractor.extractNumbers(values.get(0));
		this.distances = Extractor.extractNumbers(values.get(1));
		this.winPossibilities = new ArrayList<>();
	}

	@Override
	public boolean solve() throws Exception {
		
		for (int i = 0; i < times.size(); i++) {
			long time = times.get(i);
			long distance = distances.get(i);
			long winCount = 0;
			
			for (int timePressed = 1; timePressed < time; timePressed++) {
				if((time - timePressed) * timePressed > distance) {
					winCount++;
				}
			}
			
			winPossibilities.add(winCount);
		}
		
		return true;
	}

	@Override
	public String get() {
		return this.winPossibilities.stream()
				.mapToLong(Long::longValue)
				.reduce((a, b) -> a * b)
				.getAsLong() + "";
	}

}
