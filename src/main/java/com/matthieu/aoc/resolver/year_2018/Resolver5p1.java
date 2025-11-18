package com.matthieu.aoc.resolver.year_2018;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver5p1 implements Resolver {
	
	protected String polymer;
	protected int unitsCount;

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.polymer = values.get(0);
	}

	@Override
	public boolean solve() throws Exception {
		String basePolymer = polymer;
		boolean reactionHappend;
		
		do {
			StringBuilder builder = new StringBuilder();
			reactionHappend = false;
			
			for (int i = 0; i < basePolymer.length(); i++) {
				if(Math.abs(basePolymer.charAt(i) - basePolymer.charAt(Math.min(i + 1, basePolymer.length() - 1))) == 32) {
					reactionHappend = true;
					i++;
				} else {
					builder.append(basePolymer.charAt(i));
				}
			}
			
			basePolymer = builder.toString();
			
		} while(reactionHappend);
		
		unitsCount = basePolymer.length();
			
		return true;
	}

	@Override
	public String get() {
		return unitsCount + "";
	}

}
