package com.matthieu.aoc.resolver.year_2022;

import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver4p2 extends Resolver4p1 {

	@Override
	public boolean solve() throws SolveException {
		
		for (Duo<Duo<Integer, Integer>, Duo<Integer, Integer>> assignment : assignments) {
			if(partialOverlap(assignment.a(), assignment.b())) {
				overlapCount++;
			}
		}
		
		return true;
	}

	private boolean partialOverlap(Duo<Integer, Integer> a1, Duo<Integer, Integer> a2) {
		return !((a1.b() < a2.a() && a1.a() < a2.a()) || (a2.b() < a1.a() && a2.a() < a1.a()));
	}
}
