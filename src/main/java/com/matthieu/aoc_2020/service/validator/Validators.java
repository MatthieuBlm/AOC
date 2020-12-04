package com.matthieu.aoc_2020.service.validator;

public class Validators {

	private Validators() { }
	
	public static Validator isInt() {
		return s -> {
			try {
				Integer.parseInt(s);
			} catch(NumberFormatException e) {
				return false;
			}
			return true;
		};
	}

	public static Validator isDouble() {
		return s -> {
			try {
				Double.parseDouble(s);
			} catch(NumberFormatException e) {
				return false;
			}
			return true;
		};
	}
	
	public static Validator intBetween(int lowerBound, int upperBound) {
		return s -> isInt().validate(s) && Integer.parseInt(s) >= lowerBound && Integer.parseInt(s) <= upperBound;
	}

	public static Validator doubleBetween(double lowerBound, double upperBound) {
		return s -> isDouble().validate(s) && Double.parseDouble(s) >= lowerBound && Double.parseDouble(s) <= upperBound;
	}
	
	public static Validator lengthEq(int length) {
		return s -> s.length() == length;
	}
	
	public static Validator lengthBetween(int lowerBound, int upperBound) {
		return s -> s.length() >= lowerBound && s.length() <= upperBound;
	}
	
	public static Validator matches(String regex) {
		return s -> s.matches(regex);
	}
	
	public static Validator onlyAlpha() {
		return s -> s.matches("[a-zA-Z]*");
	}
	
	public static Validator onlyAlphaNumeric() {
		return s -> s.matches("[a-zA-Z0-9]*");
	}
	
}
