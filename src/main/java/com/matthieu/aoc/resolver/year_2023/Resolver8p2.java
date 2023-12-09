package com.matthieu.aoc.resolver.year_2023;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.service.Calculator;

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
		List<List<String>> positions = this.nodes.keySet().stream()
				.filter(key -> key.endsWith("A"))
				.map(key -> new ArrayList<>(Arrays.asList(key)))
				.collect(Collectors.toCollection(ArrayList::new));
		
		while(!positions.isEmpty()) {
			for (int i = 0; i < positions.size(); i++) {
				List<String> positionHistory = positions.get(i);
				
				positionHistory.add(this.getNextNode(getHead(positionHistory), instructionIndex));
			}
			
			steps++;
			instructionIndex = (int) (steps % instructions.length);
			
			for (int i = 0; i < positions.size(); i++) {
				String head = getHead(positions.get(i));
				// Lap done
				if(head.endsWith("Z")) {
					long loopStart = positions.get(i).indexOf(this.getNextNode(head, instructionIndex));
					long loopSize = positions.get(i).size() - loopStart;
					System.out.println(loopStart + " " + loopSize);
					
					optimizedStep = optimizedStep == null ? BigInteger.valueOf(loopSize) : Calculator.bigLeastCommonMultiple(BigInteger.valueOf(loopSize), optimizedStep);
					
					positions = positions.stream()
							.filter(history -> !getHead(history).endsWith("Z"))
							.collect(Collectors.toCollection(ArrayList::new));
					
					break;
				}
			}
		}
		
		return true;
	}
	
	@Override
	public String get() {
		return this.optimizedStep.toString();
	}
	
	private static String getHead(List<String> list) {
		return list.get(list.size() - 1);
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
	
	public static void main(String[] args) {
		List<BigInteger> numbers = Arrays.asList(BigInteger.valueOf(12878), 
				BigInteger.valueOf(14628), 
				BigInteger.valueOf(16225), 
				BigInteger.valueOf(18492), 
				BigInteger.valueOf(19596), 
				BigInteger.valueOf(21567));
		
		List<BigInteger> carlos = Arrays.asList(BigInteger.valueOf(16271l), 
				BigInteger.valueOf(24253l), 
				BigInteger.valueOf(14429l), 
				BigInteger.valueOf(22411l), 
				BigInteger.valueOf(18727l), 
				BigInteger.valueOf(20569l));
		
		System.out.println(carlos.stream().reduce(Calculator::bigLeastCommonMultiple).get());
		
	}
}
