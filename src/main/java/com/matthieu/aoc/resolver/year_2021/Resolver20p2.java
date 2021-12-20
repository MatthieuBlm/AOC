package com.matthieu.aoc.resolver.year_2021;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;

public class Resolver20p2 extends Resolver20p1 {


	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		
		this.enhancementStep = 50;
	}
}
