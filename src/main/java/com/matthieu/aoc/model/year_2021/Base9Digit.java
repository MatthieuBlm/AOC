package com.matthieu.aoc.model.year_2021;

public class Base9Digit {

	private int[] value;
	
	public Base9Digit(String initialValue) {
		char[] chars = initialValue.toCharArray();
		this.value = new int[chars.length];
		
		for (int i = 0; i < value.length; i++) {
			this.value[i] = Character.getNumericValue(chars[i]);
		}
	}
	
	public int get(int index) {
		return value[index];
	}
	
	public void decr() {
		value[value.length - 1]--;
		
		for (int i = value.length - 1; i > 0; i--) {
			int digit = value[i];
			
			if(digit == 0) {
				value[i] = 9;
				value[i - 1]--;
			} else {
				break;
			}
		}
	}
	
	public void incr() {
		value[value.length - 1]++;
		
		for (int i = value.length - 1; i > 0; i--) {
			int digit = value[i];
			
			if(digit == 10) {
				value[i] = 1;
				value[i - 1]++;
			} else {
				break;
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < value.length; i++) {
			sb.append(this.value[i]);
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Base9Digit d = new Base9Digit("99999999999999");

		long start = System.currentTimeMillis();
		for (long i = 0; i < 1000000000000l; i++) {
			d.decr();
		}
		
		System.out.println(d);
		System.out.println(System.currentTimeMillis() - start + "ms");
		
	}
	
}
