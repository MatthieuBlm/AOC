package com.matthieu.aoc.resolver.year_2023;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;

public class Resolver1p2 extends Resolver1p1 {
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		values.stream().forEach(line -> {
			String n = "";
			
			for (int i = 0; i < line.length(); i++) {
				String number = findNumber(line, i);
				
				if(number != null) {
					n += number;
					break;
				}
				
			}

			for (int i = line.length() - 1; i >= 0; i--) {
				String number = findNumber(line, i);
				
				if(number != null) {
					n += number;
					break;
				}
			}
			
			calibration.add(Integer.parseInt(n));
		});
	}
	
	private String findNumber(String line, int searchIndex) {
		if(line.charAt(searchIndex) >= '0' && line.charAt(searchIndex) <= '9') {
			return line.charAt(searchIndex) + "";
			
		} else {
			for (int j = 3; j <= 5; j++) {
				NUMBER number = this.getNumber(line.substring(searchIndex, Math.min(line.length(), searchIndex + j)));
				
				if(number != null) {
					return number.value();
				}
			}
		}
		
		return null;
	}
	
	private NUMBER getNumber(String s) {
		if(s.length() < 3 || s.length() > 5) {
			return null;
		}
		
		try {
			return NUMBER.valueOf(s);
		} catch(Exception e) {
			return null;
		}
	}
	
	private enum NUMBER {
		one("1"),
		two("2"),
		three("3"),
		four("4"),
		five("5"),
		six("6"),
		seven("7"),
		eight("8"),
		nine("9");
		
		private String i;
		
		private NUMBER(String i) {
			this.i = i;
		}
		
		protected String value() {
			return this.i;
		}
	}
	
}
