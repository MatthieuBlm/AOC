package com.matthieu.aoc.resolver.year_2023;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.Extractor;

public class Resolver8p1 implements Resolver {
	
	protected char[] instructions;
	protected Map<String, Duo<String, String>> nodes;
	protected long steps;

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.instructions = values.remove(0).toCharArray();
		
		// Remove blank line
		values.remove(0);
		
		this.nodes = values.stream()
				.map(Extractor::extractWords)
				.collect(Collectors.toMap(words -> words.get(0), words -> new Duo<>(words.get(1), words.get(2))));
	}

	@Override
	public boolean solve() throws Exception {
		String position = "AAA";
		int instructionIndex = 0;
		
		while(!position.equals("ZZZ")) {
			if(this.instructions[instructionIndex] == 'L') {
				position = this.nodes.get(position).a();
				
			} else if(this.instructions[instructionIndex] == 'R') {
				position = this.nodes.get(position).b();
				
			} else {
				throw new IllegalStateException(position);
			}
			
			steps++;
			instructionIndex = (int) (steps % instructions.length);
		}
		
		return true;
	}

	@Override
	public String get() {
		return this.steps + "";
	}

}
