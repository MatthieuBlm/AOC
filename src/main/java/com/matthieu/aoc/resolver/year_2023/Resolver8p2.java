package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.service.Calculator;

public class Resolver8p2 extends Resolver8p1 {

	private long optimizedStep;
	
	@Override
	public boolean solve() throws Exception {
		int instructionIndex = 0;
		List<String> positions = this.nodes.keySet().stream()
				.filter(key -> key.endsWith("A"))
				.collect(Collectors.toCollection(ArrayList::new));
		
		while(!positions.isEmpty()) {
			for (int i = 0; i < positions.size(); i++) {
				positions.set(i, this.getNextNode(positions.get(i), instructionIndex));
			}
			
			steps++;
			instructionIndex = (int) (steps % instructions.length);
			
			for (int i = 0; i < positions.size(); i++) {
				// Lap done
				if(positions.get(i).endsWith("Z")) {
					optimizedStep = optimizedStep == 0 ? steps : Calculator.leastCommonMultiple(steps, optimizedStep);
					
					positions = positions.stream()
							.filter(pos -> !pos.endsWith("Z"))
							.collect(Collectors.toCollection(ArrayList::new));
					break;
				}
			}
		}
		return true;
	}
	
	@Override
	public String get() {
		return this.optimizedStep + "";
	}
	
	private String getNextNode(String node, int instructionIndex) {
		if(instructions[instructionIndex] == 'L') {
			return this.nodes.get(node).a();
			
		} else if(instructions[instructionIndex] == 'R') {
			return this.nodes.get(node).b();
			
		} else {
			throw new IllegalStateException();
		}
	}
}
