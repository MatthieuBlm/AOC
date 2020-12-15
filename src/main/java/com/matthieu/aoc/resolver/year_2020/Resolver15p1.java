package com.matthieu.aoc.resolver.year_2020;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver15p1 implements Resolver {

	protected List<Integer> numbers;
	protected int resultTurn;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.numbers = Arrays.asList(values.get(0).split(",")).stream().map(Integer::valueOf).collect(Collectors.toList());
		this.resultTurn = 2020;
	}

	@Override
	public boolean solve() throws SolveException {
		
		for (int i = 0; i < resultTurn; i++) {
			this.makeTurn();
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.numbers.get(this.resultTurn - 1));
	}

	protected void makeTurn() {
		int lastNumber = this.numbers.get(this.numbers.size() - 1);
		
		if(Collections.frequency(numbers, lastNumber) == 1) {
			numbers.add(0);
		} else {
			int turnId = numbers.size();
			int previousTurnId = 0;
			
			for (int i = numbers.size() - 2; i >= 0; i--) {
				if(numbers.get(i) == lastNumber) {
					previousTurnId = i + 1;
					break;
				}
			}
			
			this.numbers.add(turnId - previousTurnId);
		}
	}
	
}
