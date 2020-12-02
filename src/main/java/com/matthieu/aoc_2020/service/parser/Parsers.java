package com.matthieu.aoc_2020.service.parser;

public class Parsers {

	private Parsers() { }
	
	
	public static Parser<Integer> toInt() {
		return s -> Integer.parseInt(s);
	}
	
	public static Parser<Character> toChar() {
		return s -> s.charAt(0);
	}
	
	public static Parser<String> toStr() {
		return s -> s;
	}
	
	public static Parser<Double> toDouble() {
		return s -> Double.parseDouble(s);
	}
	
	public static Parser<Long> toLong() {
		return s -> Long.parseLong(s);
	}
	
}
