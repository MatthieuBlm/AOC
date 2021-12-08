package com.matthieu.aoc.resolver.year_2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver8p1 implements Resolver {

	protected List<List<String>> patterns;
	protected List<List<String>> outputs;
	protected long uniqueNumber;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.patterns = new ArrayList<>();
		this.outputs = new ArrayList<>();

		for (String line : values) {
			String [] splitted = line.split(" \\| ");
			
			
			this.patterns.add(Arrays.asList(splitted[0].split(" ")));
			this.outputs.add(Arrays.asList(splitted[1].split(" ")));
		}
	}

	@Override
	public boolean solve() throws SolveException {
		this.uniqueNumber = outputs.stream()
									.flatMap(List::stream)
									.map(String::length)
									.filter(i -> (i == 2 || i == 3 || i == 4 || i == 7))
									.count();
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.uniqueNumber);
	}

}
