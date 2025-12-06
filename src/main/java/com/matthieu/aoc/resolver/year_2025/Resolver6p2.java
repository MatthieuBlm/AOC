package com.matthieu.aoc.resolver.year_2025;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;

public class Resolver6p2 extends Resolver6p1 {
	
	private List<String> numbers;
	private String operators;

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.results = new ArrayList<>();
		this.operators = values.remove(values.size() -  1);
		this.numbers = values;
	}
	
	@Override
	public boolean solve() throws Exception {
		
		for (int i = 0; i < operators.length(); i++) {
			String operator = operators.charAt(i) + "";
			
			if(!operator.equals(" ")) {
				// New operator found
				
				int endOfNumbersIndex = getNextOperatorIndexFrom(i) - 1;
				List<Long> operands = new ArrayList<>();

				for (int j = endOfNumbersIndex - 1; j >= i ; j--) {
					StringBuilder numberBuilder = new StringBuilder();
					
					for (String line : numbers) {
						numberBuilder.append(line.charAt(j));
					}
					
					operands.add(Long.parseLong(numberBuilder.toString().trim().replaceAll(" +", " ")));
				}
				
				this.results.add(makeOperation(operands, operator));
			}
		}
		
		return true;
	}
	
	private int getNextOperatorIndexFrom(int i) {
		for (int j = i + 1; j <= operators.length(); j++) {
			if(operators.length() == j) {
				return j + 1;
			} else if(operators.charAt(j) != ' ') {
				return j;
			}
		}
		
		return -1;
	}
	
}
