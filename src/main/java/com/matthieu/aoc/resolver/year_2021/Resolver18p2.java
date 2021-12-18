package com.matthieu.aoc.resolver.year_2021;

import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.year_2021.SnailfishNumber;

public class Resolver18p2 extends Resolver18p1 {

	private int maxMagnitude;
	
	@Override
	public boolean solve() throws SolveException {
		
		for (int i = 0; i < this.values.size(); i++) {
			for (int j = 0; j < this.values.size(); j++) {
				if(i == j)
					continue;
					
				
				SnailfishNumber n = new SnailfishNumber(this.values.get(i));

				n.add(this.values.get(j));
				n.reduce();
				
				this.maxMagnitude = Math.max(this.maxMagnitude, n.getMagnitude());
			}
		}
		
		return true;
	}
	
	@Override
	public String get() {
		return String.valueOf(this.maxMagnitude);
	}
}
