package com.matthieu.aoc.resolver.year_2022;

import com.matthieu.aoc.exception.SolveException;

public class Resolver3p2 extends Resolver3p1 {


	@Override
	public boolean solve() throws SolveException {

		for (int i = 0; i < ruckstacks.size(); i += 3) {
			String ruckstack1 = ruckstacks.get(i);
			String ruckstack2 = ruckstacks.get(i + 1);
			String ruckstack3 = ruckstacks.get(i + 2);
			
			for (char c : ruckstack1.toCharArray()) {
				if(ruckstack2.contains(c + "") && ruckstack3.contains(c + "")) {
					result += getPriority(c);
					break;
				}
			}
		}
		
		return true;
		
	}

}
