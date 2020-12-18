package com.matthieu.aoc.resolver.year_2020;

import java.util.ArrayList;
import java.util.List;

public class Resolver18p2 extends Resolver18p1 {

	@Override
	protected long calc(String part) {
		String[] ops = part.split(" ");
		List<Integer> numbers = new ArrayList<>();
		List<Character> operande = new ArrayList<>();
		
		for (int i = 0; i < ops.length; i++) {
			if(ops[i].charAt(0) == '+' || ops[i].charAt(0) == '*') {
				operande.add(ops[i].charAt(0));
			} else {
				numbers.add(Integer.valueOf(ops[i]));
			}
		}
		
		int plusIndex = 0;
		while((plusIndex = operande.indexOf('+')) != -1) {
			int operande2 = numbers.remove(plusIndex + 1);
			int operande1 = numbers.remove(plusIndex);
			
			numbers.add(plusIndex, operande1 + operande2);
			operande.remove(plusIndex);
		}
		
		return numbers.stream().mapToLong(i -> i).reduce(1, (a, b) -> a * b);
	}
}
