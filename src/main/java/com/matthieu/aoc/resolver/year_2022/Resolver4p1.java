package com.matthieu.aoc.resolver.year_2022;

import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver4p1 implements Resolver {
	
	protected List<Duo<Duo<Integer, Integer>, Duo<Integer, Integer>>> assignments;
	protected long overlapCount;

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		overlapCount = 0;
		assignments = values.stream().map(l -> l.split(",")).map(assignLine -> {
			String [] a1 = assignLine[0].split("-");
			String [] a2 = assignLine[1].split("-");
			
			Duo<Integer, Integer> elf1 = new Duo<>(Integer.parseInt(a1[0]), Integer.parseInt(a1[1]));
			Duo<Integer, Integer> elf2 = new Duo<>(Integer.parseInt(a2[0]), Integer.parseInt(a2[1]));
			
			return new Duo<>(elf1, elf2);
		}).collect(Collectors.toList());
	}

	@Override
	public boolean solve() throws SolveException {
		
		for (Duo<Duo<Integer, Integer>, Duo<Integer, Integer>> assignment : assignments) {
			if(fullyOverlap(assignment.a(), assignment.b())) {
				overlapCount++;
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		return overlapCount + "";
	}
	
	protected boolean fullyOverlap(Duo<Integer, Integer> a1, Duo<Integer, Integer> a2) {
		return (a1.a() <= a2.a() && a1.b() >= a2.b()) || (a2.a() <= a1.a() && a2.b() >= a1.b()); 
	}

}
