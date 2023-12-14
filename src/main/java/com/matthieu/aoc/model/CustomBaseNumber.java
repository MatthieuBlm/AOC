package com.matthieu.aoc.model;

import java.util.Arrays;

public class CustomBaseNumber {

	private char[] baseDigits;
	private int[] value;
	private int base;
	
	public CustomBaseNumber(int length, char[] baseDigits) {
		this.baseDigits = baseDigits;
		this.value = new int[length];
		this.base = baseDigits.length;
	}
	
	public CustomBaseNumber(int length, int base) {
		this.value = new int[length];
		this.base = base;
	}
	
	public CustomBaseNumber increment() {
		this.value[0]++;
		
		for (int i = 0; i < value.length; i++) {
			if(value[i] >= this.base) {
				value[i] = 0;
				
				if(i + 1 < value.length) {
					value[i + 1]++;
				}
			}
		}
		
		return this;
	}
	
	public char get(int i) {
		return this.baseDigits[value[i]];
	}
	
	public int[] getDecimalValue() {
		return this.value;
	}
	
	public int getDecimalValue(int i) {
		return this.value[i];
	}
	
	public void setDecimalValue(int i, int value) {
		this.value[i] = value;
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
	
	public String decimalValue() {
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < value.length; i++) {
			result.append(value[i]).append(" ");
		}
		
		return result.toString();
	}
	
	public boolean isMaxValue() {
		return Arrays.stream(value).allMatch(i -> i >= base - 1);
	}
	
	public int length() {
		return this.value.length;
	}
	
	@Override
	public String toString() {
		if(this.baseDigits != null) {
			return this.stringValue();
		} else {
			return this.decimalValue();
		}
	}
	
}
