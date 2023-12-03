package com.matthieu.aoc.resolver.year_2023;

import java.util.List;

import com.matthieu.aoc.exception.SolveException;

public class Resolver3p2 extends Resolver3p1 {

	@Override
	public boolean solve() throws SolveException {
		
		this.schema.forEach((x, y, c) -> {
			if(!isNumber(c) && c.charValue() == '*') {
				List<Integer> numbersAround = this.getNumerAround(x, y);
				
				if(numbersAround.size() == 2) {
					this.partNumber.add(numbersAround.get(0) * numbersAround.get(1));
				}
			}
		});
		
		return true;
	}
	
}
