package com.matthieu.aoc.resolver.year_2025;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver3p1 implements Resolver {
	
	protected List<String> banks;
	protected List<Long> joltage;
	
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.banks = values;
		this.joltage = new ArrayList<>();
	}

	@Override
	public boolean solve() throws Exception {

		this.joltage = banks.stream().map(this::getHighestJoltage).toList();
		
		return true;
	}

	@Override
	public String get() {
		return joltage.stream().mapToLong(Long::longValue).sum() + "";
	}
	
	protected Long getHighestJoltage(String bank) {
		int highestId = 0;
		int highestValue = 0;

		for (int i = highestId; i < bank.length() - 1; i++) {
			int value = Integer.parseInt(bank.substring(i, i + 1));
			
			if(value > highestValue) {
				highestId = i;
				highestValue = value;
			}
		}

		int secondId = bank.length() - 1;
		int secondValue = 0;
		
		for (int i = secondId; i > highestId; i--) {
			int value = Integer.parseInt(bank.substring(i, i + 1));
			
			if(value > secondValue) {
				secondId = i;
				secondValue = value;
			}
		}
		
		return Long.parseLong(highestValue + "" + secondValue);
	}

}
