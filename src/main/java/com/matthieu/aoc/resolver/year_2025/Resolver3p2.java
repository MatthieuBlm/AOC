package com.matthieu.aoc.resolver.year_2025;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Resolver3p2 extends Resolver3p1 {

	@Override
	protected Long getHighestJoltage(String bank) {
		int[][] values = new int[2][12]; //[id, highest value][i]
		
		for(int j = values[0][0]; j < bank.length() - 12; j++) {
			int value = Integer.parseInt(bank.substring(j, j + 1));
			
			if(value > values[1][0]) {
				values[0][0] = j;
				values[1][0] = value;
			}
		}

		for (int i = 1; i < 12; i++) {
			
			for(int j = values[0][i - 1] + 1; j <= bank.length() - (12 - i); j++) {
				
				int value = Integer.parseInt(bank.substring(j, j + 1));
				
				if(value > values[1][i]) {
					values[0][i] = j;
					values[1][i] = value;
				}
				
			}
			
		}
		
		System.out.println(bank + " " + Long.parseLong(Arrays.stream(values[1]).mapToObj(i -> i + "").collect(Collectors.joining())));
		StringBuilder indexesPosition = new StringBuilder();
		
		for (int j = 0; j < bank.length(); j++) {
			final int jj = j;
			if(Arrays.stream(values[0]).anyMatch(i -> i == jj)) {
				indexesPosition.append("^");
			} else {
				indexesPosition.append(" ");
			}
		}
		System.out.println(indexesPosition.toString());
		
		return Long.parseLong(Arrays.stream(values[1]).mapToObj(i -> i + "").collect(Collectors.joining()));
	}
}
