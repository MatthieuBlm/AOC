package com.matthieu.aoc.resolver.year_2022;

import java.util.ArrayList;
import java.util.List;

public class Resolver5p2 extends Resolver5p1 {

	@Override
	protected void moveCrates(int nbToMove, int idSource, int idDest) {
		List<Character> toMove = new ArrayList<>();

		for (int i = 0; i < nbToMove; i++) {
			List<Character> relatedSource = crates.get(idSource - 1);
			
			toMove.add(relatedSource.remove(relatedSource.size() - 1));
		}
		
		for (int i = toMove.size() - 1; i >= 0; i--) {
			List<Character> relatedDest = crates.get(idDest - 1);
			
			relatedDest.add(toMove.get(i));
		}
		
	}

}
