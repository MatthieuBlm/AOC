package com.matthieu.aoc.resolver.year_2021;

import com.matthieu.aoc.exception.SolveException;

public class Resolver1p2 extends Resolver1p1 {


	@Override
	public boolean solve() throws SolveException {
		
		for(int i = 3; i < this.values.size(); i++) {
			int a = this.values.get(i - 3) + this.values.get(i - 2) + this.values.get(i - 1);
			int b = this.values.get(i - 2) + this.values.get(i - 1) + this.values.get(i);
			
			if(a < b)
				result++;
		}
		
		return true;
	}

}
