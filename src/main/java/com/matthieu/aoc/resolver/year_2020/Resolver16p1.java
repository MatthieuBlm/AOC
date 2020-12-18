package com.matthieu.aoc.resolver.year_2020;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.parser.Parser;
import com.matthieu.aoc.service.validator.Validator;

public class Resolver16p1 implements Resolver {

	protected List<Validator> validators;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		Iterator<String> iterator = values.iterator();
		
		// departure track: 41-85 or 104-962
		Parser<Validator> parser = s -> {
			String[] bornes = s.split(": ")[1].split(" or ");
			
			final List<Duo<Integer, Integer>> criteria = Arrays.asList(bornes).stream()
															.map(b -> b.split("-"))
															.map(b -> new Duo<Integer, Integer>(Integer.parseInt(b[0]), Integer.parseInt(b[1])))
															.collect(Collectors.toList());
													
			return s2 -> {
				int i = Integer.parseInt(s2);
				return criteria.stream().allMatch(crit -> i >= crit.a() && i <= crit.b());
			};
		};
		
		String line;
		while (!(line = iterator.next()).isBlank()) {
			validators.add(parser.parse(line));
		}
		
	}

	@Override
	public boolean solve() throws SolveException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String get() {
		// TODO Auto-generated method stub
		return null;
	}

}
