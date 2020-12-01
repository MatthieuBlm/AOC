package com.matthieu.aoc_2020.model.resolver;

import java.util.List;

import com.matthieu.aoc_2020.exception.PrepareDataException;
import com.matthieu.aoc_2020.exception.SolveException;

public interface Resolver {

	void prepareData(List<String> values) throws PrepareDataException;
	
	boolean solve() throws SolveException;
	
	String get();
}
