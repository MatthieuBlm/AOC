package com.matthieu.aoc.resolver.year_2025;

import com.matthieu.aoc.model.tuple.Duo;

public class Resolver1p2 extends Resolver1p1 {

	@Override
	public boolean solve() throws Exception {
		for (Duo<Character, Integer> rotation : rotations) {

			if(rotation.a() == 'L') {
				for (int i = 0; i < rotation.b(); i++) {
					if(--position % 100 == 0) {
						zeroCount++;
					}
				}
			} else {
				for (int i = 0; i < rotation.b(); i++) {
					if(++position % 100 == 0) {
						zeroCount++;
					}
				}
			}
		}
		
		return true;
	}
}
