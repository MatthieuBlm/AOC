package com.matthieu.aoc.resolver.year_2023;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Resolver8p2 extends Resolver8p1 {

	private BigInteger optimizedStep;
	
//	@Override
//	public boolean solve() throws Exception {
//		List<String> positions = this.nodes.keySet().stream().filter(key -> key.endsWith("A")).collect(Collectors.toCollection(ArrayList::new));
//		int instructionIndex = 0;
//		
//		while(!positions.stream().allMatch(pos -> pos.endsWith("Z"))) {
//			if(this.instructions[instructionIndex] == 'L') {
//				for (int i = 0; i < positions.size(); i++) {
//					positions.set(i, this.nodes.get(positions.get(i)).a());
//				}
//				
//			} else if(this.instructions[instructionIndex] == 'R') {
//				for (int i = 0; i < positions.size(); i++) {
//					positions.set(i, this.nodes.get(positions.get(i)).b());
//				}
//				
//			} else {
//				throw new IllegalStateException();
//			}
//			
//			steps++;
//			instructionIndex = (int) (steps % instructions.length);
//		}
//		
//		return true;
//	}
	
	@Override
	public boolean solve() throws Exception {
		int instructionIndex = 0;
		List<String> positions = this.nodes.keySet().stream()
				.filter(key -> key.endsWith("A"))
				.collect(Collectors.toCollection(ArrayList::new));
		
		while(!positions.isEmpty()) {
			if(this.instructions[instructionIndex] == 'L') {
				for (int i = 0; i < positions.size(); i++) {
					positions.set(i, this.nodes.get(positions.get(i)).a());
				}
				
			} else if(this.instructions[instructionIndex] == 'R') {
				for (int i = 0; i < positions.size(); i++) {
					positions.set(i, this.nodes.get(positions.get(i)).b());
				}
				
			} else {
				throw new IllegalStateException();
			}
			
			steps++;
			instructionIndex = (int) (steps % instructions.length);
			
			for (int i = 0; i < positions.size(); i++) {
				// Lap done
				if(positions.get(i).endsWith("Z")) {
					optimizedStep = optimizedStep == null ? new BigInteger(steps + "") : optimizedStep.multiply(new BigInteger(steps+ ""));
				}
			}
			
			positions = positions.stream()
					.filter(pos -> !pos.endsWith("Z"))
					.collect(Collectors.toCollection(ArrayList::new));
		}
		
		return true;
	}
	
	@Override
	public String get() {
		return this.optimizedStep.toString();
	}
	
}
