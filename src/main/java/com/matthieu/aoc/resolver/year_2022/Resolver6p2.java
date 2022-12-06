package com.matthieu.aoc.resolver.year_2022;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;

public class Resolver6p2 extends Resolver6p1 {

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		
		this.sequnenceSize = 14;
	}

}
