package com.matthieu.aoc.resolver.year_2025;

public class Resolver2p2 extends Resolver2p1 {

	@Override
	protected boolean repeatTwice(long i) {
		if(i < 10) {
			return false;
		}
		
		String stringId = i + "";
		
		for (int j = 1; j <= stringId.length() / 2; j++) {
			String idToTest = stringId.substring(0, j);
			
			if(stringId.replaceAll(idToTest, "").equals("")) {
				return true;
			}
		}
		
		return false;
	}
	
}
