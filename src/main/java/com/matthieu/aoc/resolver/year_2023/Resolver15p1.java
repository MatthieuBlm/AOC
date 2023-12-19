package com.matthieu.aoc.resolver.year_2023;

import java.util.List;
import java.util.stream.Stream;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver15p1 implements Resolver {

	protected int maxResult;
	protected int factor;
	protected List<Integer> results;
	protected List<String> steps;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.maxResult = 256;
		this.factor = 17;
		this.steps = Stream.of(values.get(0).split(",")).toList();
	}

	@Override
	public boolean solve() throws Exception {
		
		this.results = this.steps.stream().map(this::hash).toList();
		
		return true;
	}

	@Override
	public String get() {
		return results.stream().mapToLong(Integer::intValue).sum() + "";
	}
	
	protected int hash(String s) {
		int hash = 0;
		
		for(char c : s.toCharArray()) {
			hash = ((hash + c) * this.factor) % this.maxResult;
		}
		
		return hash;
	}
	
}
