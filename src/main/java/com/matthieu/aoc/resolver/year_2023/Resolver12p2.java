package com.matthieu.aoc.resolver.year_2023;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.matthieu.aoc.exception.PrepareDataException;

public class Resolver12p2 extends Resolver12p1 {

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		this.conditionsRecords = conditionsRecords.stream()
				.map(r -> new ConditionRecords(
						IntStream.range(0, 4).mapToObj(i -> r.conditions()).collect(Collectors.joining("?")), 
						IntStream.range(0, 4).mapToObj(i -> r.groups()).flatMap(List::stream).toList()))
				.toList();
	}
	
	
	
}