package com.matthieu.aoc.service.parser;

@FunctionalInterface
public interface Parser<T> {

	T parse(String value);
	
}
