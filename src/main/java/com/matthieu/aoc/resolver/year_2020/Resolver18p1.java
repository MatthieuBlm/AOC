package com.matthieu.aoc.resolver.year_2020;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver18p1 implements Resolver {

	protected List<String> operations;
	protected List<Long> results;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.operations = values;
	}

	@Override
	public boolean solve() throws SolveException {

		results = operations.stream().mapToLong(this::calcFrom).boxed().collect(Collectors.toList());
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.results.stream().mapToLong(l -> l).sum());
	}
	

	protected long calcFrom(String operation) {
		int lastIndexOfParenthesis = 0;
		
		while((lastIndexOfParenthesis = operation.lastIndexOf("(")) != -1) {
			int closeParenthesis = operation.indexOf(")", lastIndexOfParenthesis);
			
			String subOpe = operation.substring(lastIndexOfParenthesis + 1, closeParenthesis);
			
			long result = calc(subOpe);
			
			operation = operation.substring(0, lastIndexOfParenthesis) + result + operation.subSequence(closeParenthesis + 1, operation.length());
		}
		
		
		return calc(operation);
	}

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
		
		long result = numbers.remove(0);
		
		for (Character operator : operande) {
			if(operator == '+') {
				result += numbers.remove(0);
			} else if(operator == '*') {
				result *= numbers.remove(0);
			} else {
				throw new IllegalArgumentException("Unknown operator " + operator);
			}
		}
		
		return result;
	}

}
