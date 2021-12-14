package com.matthieu.aoc.resolver.year_2021;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;

public class Resolver14p2 extends Resolver14p1 {
	
	private Map<String, Long> polymerCouples;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		
		this.polymerCouples = new HashMap<>();

		for (int i = 0; i < polymer.size() - 1; i++) {
			String couple = this.polymer.get(i).toString() + this.polymer.get(i + 1).toString();
			
			this.increaseForCouple(this.polymerCouples, couple, 1);
		}
	}

	@Override
	public boolean solve() throws SolveException {
		
		for (int i = 0; i < 40; i++) {
			Map<String, Long> increment = new HashMap<>();
			
			for (Map.Entry<String, Long> currentCouple : this.polymerCouples.entrySet()) {
				Character produce = this.rules.get(currentCouple.getKey());
				
				String newCoupleA = currentCouple.getKey().charAt(0) + produce.toString();
				String newCoupleB = produce.toString() + currentCouple.getKey().charAt(1);
				
				this.increaseForCouple(increment, newCoupleA, currentCouple.getValue());
				this.increaseForCouple(increment, newCoupleB, currentCouple.getValue());
			}
			
			this.polymerCouples = increment;
			
		}
		
		return true;
	}
	
	@Override
	public String get() {
		Map<Character, Long> result = new HashMap<>();
		
		// Take all second char of all couples
		for (Map.Entry<String, Long> entry : this.polymerCouples.entrySet()) {
			char c = entry.getKey().charAt(1);
			
			Long value = result.computeIfAbsent(c, k -> 0l);
			
			value += entry.getValue();
			
			result.put(c, value);
		}

		// Take first char of first entry
		Map.Entry<String, Long> firstEntry = this.polymerCouples.entrySet().iterator().next();
		char c = firstEntry.getKey().charAt(0);
		
		Long value = result.computeIfAbsent(c, k -> 0l);
		
		value += firstEntry.getValue();
		
		result.put(c, value);
		
		
		long lowest = Collections.min(result.values());
		long highest = Collections.max(result.values());
		
		return String.valueOf(highest - lowest);
	}

	private void increaseForCouple(Map<String, Long> map, String couple, long v) {
		Long value = map.computeIfAbsent(couple, k -> 0L);
		
		value += v;
		
		map.put(couple, value);
	}
}
