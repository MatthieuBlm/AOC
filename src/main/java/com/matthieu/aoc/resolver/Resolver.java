package com.matthieu.aoc.resolver;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;

public interface Resolver {

	void prepareData(List<String> values) throws PrepareDataException;
	
	boolean solve() throws SolveException;
	
	String get();
}
