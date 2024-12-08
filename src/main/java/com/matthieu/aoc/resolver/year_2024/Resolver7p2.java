package com.matthieu.aoc.resolver.year_2024;

import java.util.ArrayList;
import java.util.List;


public class Resolver7p2 extends Resolver7p1 {

	@Override
	protected long calc(long a, long b, Operator operator) {
		switch(operator) {
		case ADD:
			return a + b;
		case MULT:
			return a * b;
		case CONCAT:
			return Long.parseLong(a + "" + b);
			default :
				throw new IllegalStateException();
		}
	}
	
	@Override
	protected List<Operator> getNextOperatorCombination(List<Operator> operators) {
		List<Operator> next = new ArrayList<>(operators);
		
		if(operators.stream().allMatch(o -> o == Operator.CONCAT)) {
			return null;
		}

		for (int i = 0; i < operators.size(); i++) {
			if(operators.get(i) == Operator.ADD) {
				next.set(i, Operator.MULT);
				break;
			} else if(operators.get(i) == Operator.MULT) {
				next.set(i, Operator.CONCAT);
				break;
			} else {
				next.set(i, Operator.ADD);
			}
		}
		
		return next;
	}
}
