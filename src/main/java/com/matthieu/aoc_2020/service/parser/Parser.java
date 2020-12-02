package com.matthieu.aoc_2020.service.parser;

@FunctionalInterface
public interface Parser<T> {

	T parse(String value);
	
}
