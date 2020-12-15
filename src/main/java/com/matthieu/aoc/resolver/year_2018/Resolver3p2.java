package com.matthieu.aoc.resolver.year_2018;

import java.awt.Rectangle;

public class Resolver3p2 extends Resolver3p1 {

	@Override
	public String get() {
		
		for (int k = 0; k < rects.size(); k++) {
			Rectangle r = rects.get(k);
			boolean ok = true;
			
			outerloop:
			for (int i = r.x; i < r.x + r.getWidth(); i++) {
				for (int j = r.y; j < r.y + r.getHeight(); j++) {
					if(this.claims[i][j] > 1) {
						ok = false;
						break outerloop;
					}
				}
			}
			
			if(ok) {
				return String.valueOf(k + 1);
			}
		}
		
		return "";
	}

}
