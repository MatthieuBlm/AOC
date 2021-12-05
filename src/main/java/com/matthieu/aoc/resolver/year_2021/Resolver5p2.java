package com.matthieu.aoc.resolver.year_2021;

import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver5p2 extends Resolver5p1 {

	@Override
	public boolean solve() throws SolveException {
		
		for (Duo<Duo<Integer, Integer>, Duo<Integer, Integer>> coords : this.points) {
			this.trace(coords.a(), coords.b());
		}
		
		return true;
	}

	
}
