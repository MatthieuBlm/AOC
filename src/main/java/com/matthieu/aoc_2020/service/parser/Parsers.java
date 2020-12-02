package com.matthieu.aoc_2020.service.parser;

public class Parsers {

	private Parsers() { }
	
	
	public static Parser<Integer> toInt() {
		return Integer::parseInt;
	}
	
	public static Parser<Character> toChar() {
		return s -> s.charAt(0);
	}
	
	public static Parser<String> toStr() {
		return s -> s;
	}
	
	public static Parser<Double> toDouble() {
		return Double::parseDouble;
	}
	
	public static Parser<Long> toLong() {
		return Long::parseLong;
	}
	
}
