package com.matthieu.aoc.resolver.year_2021;

public class Resolver7p2 extends Resolver7p1 {

	@Override
	protected long calcFuel(int pos, int i) {
		long sum = 0;
		
		for (int j = 1; j <= super.calcFuel(pos, i); j++) {
			sum += j;
		}
		
		return sum;
	}


}
