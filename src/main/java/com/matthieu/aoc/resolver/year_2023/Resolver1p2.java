package com.matthieu.aoc.resolver.year_2023;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;

public class Resolver1p2 extends Resolver1p1 {
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		values.stream().forEach(line -> {
			String n = "";
			
			outerloop:
			for (int i = 0; i < line.length(); i++) {
				if(line.charAt(i) >= '0' && line.charAt(i) <= '9') {
					n += Integer.parseInt(line.charAt(i) + "");
					break;
					
				} else {
					for (int j = 3; j <= 5; j++) {
						NUMBER number = this.getNumber(line.substring(i, Math.min(line.length(), i + j)));
						
						if(number != null) {
							n += number.value();
							break outerloop;
						}
					}
				}
					
			}

			outerloop:
			for (int i = line.length() - 1; i >= 0; i--) {
				if(line.charAt(i) >= '0' && line.charAt(i) <= '9') {
					n += Integer.parseInt(line.charAt(i) + "");
					break;

				} else {
					for (int j = 3; j <= 5; j++) {
						NUMBER number = this.getNumber(line.substring(i, Math.min(line.length(), i + j)));
						
						if(number != null) {
							n += number.value();
							break outerloop;
						}
					}
				}
			}
			
			calibration.add(Integer.parseInt(n));
		});
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
