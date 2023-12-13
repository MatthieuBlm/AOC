package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.matthieu.aoc.model.Orientation;
import com.matthieu.aoc.model.matrix.Cell;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver13p2 extends Resolver13p1 {

	@Override
	public boolean solve() throws Exception {
		super.solve();
		
		List<Duo<Orientation, Integer>> part1Reflexions = new ArrayList<>(this.reflexions);
		this.reflexions = new ArrayList<>();
		
		outerloop:
		for (int i = 0; i < maps.size(); i++) {
			Matrix<Character> map = this.maps.get(i);
			
			for (Cell<Character> cell : map.getCells()) {
				map.set(cell.x(), cell.y(), invert(map.get(cell.x(), cell.y())));
				
				Duo<Orientation, Integer> symetry = super.getMirrorSymetryWeight(map, Optional.of(part1Reflexions.get(i)));
				
				if(symetry != null) {
					reflexions.add(symetry);
					continue outerloop;
				}
				
				map.set(cell.x(), cell.y(), invert(map.get(cell.x(), cell.y())));
			}
		}
		return true;
	}
	
	protected Character invert(Character c) {
		return c.charValue() == '.' ? '#' : '.';
	}
}
