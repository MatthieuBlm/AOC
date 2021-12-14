package com.matthieu.aoc.resolver.year_2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver14p1 implements Resolver {

	protected List<Character> polymer;
	protected Map<String, Character> rules;
	protected int steps;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.polymer = new ArrayList<>();
		this.rules = new HashMap<>();
		this.steps = 10;
		
		for (char c : values.remove(0).toCharArray()) {
			this.polymer.add(c);
		}
		
		values.remove(0); // Remove empty line
		
		for (String line : values) {
			String [] splitted = line.split(" -> ");
			
			this.rules.put(splitted[0], splitted[1].charAt(0));
		}
	}

	@Override
	public boolean solve() throws SolveException {
		
		for (int i = 0; i < this.steps; i++) {
			List<Duo<Integer, Character>> toInsert = new ArrayList<>();
			
			for (int j = this.polymer.size() - 1; j > 0 ; j--) {
				String key = Character.toString(this.polymer.get(j - 1)) + Character.toString(this.polymer.get(j));
				Character newChar = this.rules.get(key);
				
				if(newChar != null) {
					toInsert.add(new Duo<>(j, newChar));
				}
			}
			
			for (Duo<Integer, Character> duo : toInsert) {
				this.polymer.add(duo.a(), duo.b());
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		Set<Character> unique = new HashSet<>(this.polymer);
		
		List<Integer> frequency = unique.stream().map(c -> Collections.frequency(this.polymer, c)).collect(Collectors.toList());
		
		int leastCommon = Collections.min(frequency);
		int mostCommon = Collections.max(frequency);
		
		return String.valueOf(mostCommon - leastCommon);
	}

}
