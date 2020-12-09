package com.matthieu.aoc_2020.service.resolver.year_2018;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.matthieu.aoc_2020.exception.PrepareDataException;
import com.matthieu.aoc_2020.exception.SolveException;
import com.matthieu.aoc_2020.service.resolver.Resolver;

public class Resolver4p1 implements Resolver {
	
	private Map<String, String> map;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.map = values.stream()
						.map(s -> s.split("] "))
						.sorted((s1, s2) -> s1[0].compareTo(s2[0]))
						.collect(Collectors.toMap(s -> s[0].replace("[", ""), s -> s[1]));
		
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
