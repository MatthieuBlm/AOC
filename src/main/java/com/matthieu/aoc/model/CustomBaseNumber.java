package com.matthieu.aoc.model;

import java.util.Arrays;

public class CustomBaseNumber {

	private char[] baseDigits;
	private int[] value;
	
	public CustomBaseNumber(int size, char[] baseDigits) {
		this.baseDigits = baseDigits;
		this.value = new int[size];
	}
	
	public void increment() {
		this.value[0]++;
		
		for (int i = 0; i < value.length; i++) {
			if(value[i] >= this.baseDigits.length) {
				value[i] = 0;
				
				if(i + 1 < value.length) {
					value[i + 1]++;
				}
			}
		}
	}
	
	public char get(int i) {
		return this.baseDigits[value[i]];
	}
	
	public String stringValue() {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < value.length; i++) {
			if(builder.length() == 0) {
				builder.append(get(i));
			} else {
				builder.insert(0, get(i));
			}
		}
		
		return builder.toString();
	}
	
	public boolean isMaxValue() {
		return Arrays.stream(value).allMatch(i -> i >= baseDigits.length - 1);
	}
	
	@Override
	public String toString() {
		return this.stringValue();
	}
	
}
