package com.matthieu.aoc.resolver.year_2021;

import java.util.List;

import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.year_2021.DigitDisplay;

public class Resolver8p2 extends Resolver8p1 {

	private long result;
	
	@Override
	public boolean solve() throws SolveException {
		
		for (int i = 0; i < this.patterns.size(); i++) {
			List<String> pattern = this.patterns.get(i);
			List<String> output = this.outputs.get(i);
			
			String one = pattern.stream().filter(s -> s.length() == 2).findAny().orElseThrow();
			String four = pattern.stream().filter(s -> s.length() == 4).findAny().orElseThrow();
			String seven = pattern.stream().filter(s -> s.length() == 3).findAny().orElseThrow();
			
			DigitDisplay digit = new DigitDisplay(one, four, seven);
			digit.initialize(pattern);

			String outputValue = output.stream().map(digit::getDigit).map(String::valueOf).reduce((a, b) -> a + b).orElseThrow();
			
			result += Long.parseLong(outputValue);
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.result);
	}

}
