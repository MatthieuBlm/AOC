package com.matthieu.aoc.resolver.year_2023;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;

public class Resolver17p2 extends Resolver17p1 {

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		this.minStepLenght = 4;
		this.maxStepLength = 10;
	}
	
}
