package com.matthieu.aoc.resolver.year_2021;

import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.parser.Parsers;

public class Resolver1p1 implements Resolver {

	protected List<Integer> values;
	protected int result;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.values = values.stream().map(Parsers.toInt()::parse).collect(Collectors.toList());
	}

	@Override
	public boolean solve() throws SolveException {
		for (int i = 1; i < values.size(); i++) {
			if(values.get(i) > values.get(i - 1))
				result++;
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.result);
	}

}
