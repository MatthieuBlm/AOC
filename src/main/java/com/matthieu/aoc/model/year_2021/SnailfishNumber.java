package com.matthieu.aoc.model.year_2021;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.matthieu.aoc.model.tuple.Duo;

public class SnailfishNumber {

	private StringBuilder number;
	
	
	public SnailfishNumber(String initialValue) {
		this.number = new StringBuilder(initialValue);
	}
	
	
	public void add(String toAdd) {
		number.insert(0, '[').append(",").append(toAdd).append(']');
	}
	
	public boolean explodeIfAny() {
		int depth = 0;
		
		for (int i = 0; i < number.length(); i++) {
			if(number.charAt(i) == '[')
				depth++;
			else if(number.charAt(i) == ']')
				depth--;
			
			if(depth > 4) {
				String toExplode = this.takeNumber(i);
				Duo<Integer, Integer> values = this.toRegular(toExplode);
				
				number.insert(i, 0);
				
				this.addToTheRight(i, values.b());
				this.addToTheLeft(i, values.a());
				
				return true;
			}
		}
		
		return false;
	}
	
	public boolean splitIfAny() {
		for (int i = 0; i < number.length(); i++) {
			Integer n = this.getNumber(i);
			
			if(n == null || n < 10)
				continue;
			
			StringBuilder newNumber = new StringBuilder("[");
			
			newNumber.append((int) Math.floor(n / 2d)).append(',').append((int) Math.ceil(n / 2d)).append(']');
			
			number.delete(i, i + 2);
			number.insert(i, newNumber);
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Take and remove snailfish number.
	 * @param start beginning of number.
	 * @return the number;
	 */
	public String takeNumber(int start) {
		char startingChar = number.charAt(start);
		
		if(startingChar != '[')
			throw new IllegalArgumentException("The char at index "+ start +" ('"+ startingChar +"') isn't a starting number char");
		
		int depth = 1;
		int end = start;
		
		do {
			end++;
			
			if(number.charAt(end) == '[')
				depth++;
			else if(number.charAt(end) == ']')
				depth--;
			
		} while(depth > 0);
		
		String result = number.substring(start, end + 1);
		number.delete(start, end + 1);
		
		return result;
	}

	public Duo<Integer, Integer> toRegular(String value) {
		String[] formatted = value.replace("[", "").replace("]", "").split(",");
		
		return new Duo<>(Integer.parseInt(formatted[0]), Integer.parseInt(formatted[1]));
	}
	
	public void addToTheRight(int i, int value) {
		for (int j = i + 1; j < number.length(); j++) {
			Integer n = this.getNumber(j);
			
			if(n != null) {
				n += value;
				
				this.setNumber(j, n);
				return;
			}
		}
	}

	public void addToTheLeft(int i, int value) {
		for (int j = i - 1; j >= 0; j--) {
			Integer n = this.getNumber(j);
			
			if(n != null) {
				n += value;
				
				this.setNumber(j, n);
				
				return;
			}
		}
	}

	public Integer getNumber(int i) {
		char middle = this.number.charAt(i);
		
		// Not between '0' and '9'
		if(!this.isNumber(middle)) {
			return null;
		}
		
		String buffer = number.substring(Math.max(0, i - 1), i + 2);
		buffer = buffer.replace("[", "").replace(",", "").replace("]", "");
		
		try {
			return Integer.parseInt(buffer);
		} catch(NumberFormatException e) {
			return null;
		}
	}
	
	public void setNumber(int i, int value) {
		Integer n = this.getNumber(i);
		
		if(n == null)
			throw new IllegalArgumentException("char at position "+ i +" isn't a number ("+ number.toString() +")");
		

		int start = i;
		if(this.isNumber(number.charAt(i - 1))) {
			start = i - 1;
		}
		
		while(this.isNumber(number.charAt(start))) {
			number.delete(start, start + 1);
		}
		
		number.insert(start, value);
		
	}
	
	public void reduce() {
		
		do {
		
			while(this.explodeIfAny()) {}

		} while(this.splitIfAny());
		
	}
	
	private boolean isNumber(char c) {
		return c >= 48 && c <= 57;
	}
	
	public int getMagnitude() {
		Pattern pattern = Pattern.compile(".[0-9]+\\,[0-9]+.");
		StringBuilder numberClone = new StringBuilder(this.number);
		
		while(!this.isNumber(numberClone.charAt(0))) {
			
			Matcher matcher = pattern.matcher(numberClone.toString());
			
			if(matcher.find()) {
				Duo<Integer, Integer> values = this.toRegular(matcher.group());
				Integer value = 3 * values.a() + 2 * values.b();
				
				numberClone.delete(matcher.start(), matcher.end());
				numberClone.insert(matcher.start(), value);
			}
		}
		
		return Integer.parseInt(numberClone.toString());
	}
	
	@Override
	public String toString() {
		return this.number.toString();
	}
}
