package com.matthieu.aoc.resolver.year_2018;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Resolver5p2 extends Resolver5p1 {

	@Override
	public boolean solve() throws Exception {
		AtomicInteger minUnits = new AtomicInteger(Integer.MAX_VALUE);
		String basePolymer = super.polymer;
		
		List.of("aA", "bB", "cC", "dD").forEach(toReplace -> {
			this.polymer = basePolymer.replaceAll(toReplace.charAt(0) + "", "").replaceAll(toReplace.charAt(1) + "", "");
			
			try {
				super.solve();
				
				if(this.unitsCount < minUnits.intValue()) {
					minUnits.set(this.unitsCount);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
		
		
		
		this.unitsCount = minUnits.intValue();
		return true;
	}
}
