package com.matthieu.aoc.resolver.year_2021;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;

public class Resolver6p2 extends Resolver6p1 {

	private Map<Integer, Long> fishes;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		
		this.fishes = new HashMap<>();
		
		for (int i = 0; i <= 8; i++) {
			this.fishes.put(i, 0L);
		}

		for (Integer f : this.lanternfishes) {
			Long fishCount = fishes.get(f);
			
			fishes.put(f, ++fishCount);
		}
	}

	@Override
	public boolean solve() throws SolveException {
		
		for (int i = 0; i < 256; i++) {
			this.simulateDay(this.fishes);
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.fishes.values().stream().mapToLong(i -> i).sum());
	}
	
	protected void simulateDay(Map<Integer, Long> fishCounts) {
		long newFishNumber = fishCounts.get(0);
		
		for (int i = 0; i < 8; i++) {
			fishCounts.put(i, fishCounts.get(i + 1));
		}
		
		fishCounts.put(6, fishCounts.get(6) + newFishNumber);
		fishCounts.put(8, newFishNumber);
	}

}
