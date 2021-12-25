package com.matthieu.aoc.resolver.year_2021;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.WrappedMatrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver25p1 implements Resolver {

	protected WrappedMatrix<Character> map;
	protected boolean hasMoved;
	protected int step;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.map = new WrappedMatrix<>(values, s -> s.charAt(0));
		this.hasMoved = true;
		this.step = 0;
	}

	@Override
	public boolean solve() throws SolveException {
		
		while(hasMoved) {
			hasMoved = false;
			
			final WrappedMatrix<Character> newMap = new WrappedMatrix<>(map.getWidth(), map.getHeight(), () -> null);
			
			// East
			this.map.forEach((x, y, v) -> {
				
				if(v.equals('>') && map.getRight(x, y).equals('.')) {
					newMap.set(x, y, '.');
					newMap.setRight(x, y, '>');
					hasMoved = true;
				}
			});
			
			newMap.forEach((x, y, v) -> {
				if(v != null)
					map.set(x, y, v);
			});

			final WrappedMatrix<Character> newMap2 = new WrappedMatrix<>(map.getWidth(), map.getHeight(), () -> null);
			
			// West
			this.map.forEach((x, y, v) -> {

				if(v.equals('v') && map.getBottom(x, y).equals('.')) {
					newMap2.set(x, y, '.');
					newMap2.setBottom(x, y, 'v');
					hasMoved = true;
				}
			});
			
			newMap2.forEach((x, y, v) -> {
				if(v != null)
					map.set(x, y, v);
			});

			step++;
		}
		
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.step);
	}

}
