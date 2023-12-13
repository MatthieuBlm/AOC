package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Orientation;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.ListUtils;

public class Resolver13p1 implements Resolver {

	protected List<Matrix<Character>> maps;
	protected List<Duo<Orientation, Integer>> reflexions;
	
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		reflexions = new ArrayList<>();
		maps = new ArrayList<>();
		List<String> mapLines = new ArrayList<>();
		
		// Add blank line at the end if none exists
		if(!ListUtils.getLast(values).equals("")) {
			values.add("");
		}
		
		for (String line : values) {
			if(line.equals("")) {
				maps.add(new Matrix<>(mapLines, s -> s.charAt(0)));
				mapLines = new ArrayList<>();
				
			} else {
				mapLines.add(line);
			}
		}
	}

	@Override
	public boolean solve() throws Exception {
		this.reflexions = this.maps.stream().map(this::getMirrorSymetryWeight).toList();
		return true;
	}

	@Override
	public String get() {
		return reflexions.stream().mapToInt(r -> r.a() == Orientation.VERTICAL ? r.b() : r.b() * 100).sum() + "";
	}
	
	protected Duo<Orientation, Integer> getMirrorSymetryWeight(Matrix<Character> map) {
		return this.getMirrorSymetryWeight(map, Optional.empty());
	}
	
	protected Duo<Orientation, Integer> getMirrorSymetryWeight(Matrix<Character> map, Optional<Duo<Orientation, Integer>> reflexionToIgnore) {
		int verticalSymetryIndex = this.hasVerticalSymetry(map, reflexionToIgnore);
		
		if(verticalSymetryIndex != -1) {
			return new Duo<>(Orientation.VERTICAL, verticalSymetryIndex);
		} else {
			int horizontalSymetryIndex = this.hasHorizontalSymetry(map, reflexionToIgnore);
			
			if(horizontalSymetryIndex != -1) {
				return new Duo<>(Orientation.HORIZONTAL, horizontalSymetryIndex);
			}
		}
		
		return null;
	}
	
	protected int hasHorizontalSymetry(Matrix<Character> map, Optional<Duo<Orientation, Integer>> reflexionToIgnore) {
		outerloop:
		for (int y = 0; y < map.getHeight() - 1; y++) {
			// Found two same row consecutive
			if(map.getRow(y).equals(map.getRow(y + 1))) {
				// Check all others
				int otherYCheck;
				for (int yCheck = y; yCheck >= 0 && (otherYCheck = y + y - yCheck + 1) <= map.getMaxY(); yCheck--) {
					if(!map.getRow(yCheck).equals(map.getRow(otherYCheck))) {
						continue outerloop;
					}
				}
				
				if(!(reflexionToIgnore.isPresent() 
						&& reflexionToIgnore.get().a() == Orientation.HORIZONTAL
						&& reflexionToIgnore.get().b() == y + 1)) {
					return y + 1;
				}
			}
		}
		
		return -1;
	}

	protected int hasVerticalSymetry(Matrix<Character> map, Optional<Duo<Orientation, Integer>> reflexionToIgnore) {
		outerloop:
		for (int x = 0; x < map.getWidth() - 1; x++) {
			// Found two same row consecutive
			if(map.getColumn(x).equals(map.getColumn(x + 1))) {
				// Check all others
				int otherXCheck;
				for (int xCheck = x - 1; xCheck >= 0 && (otherXCheck = x + x - xCheck + 1) <= map.getMaxX(); xCheck--) {
					if(!map.getColumn(xCheck).equals(map.getColumn(otherXCheck))) {
						continue outerloop;
					}
				}
				
				if(!(reflexionToIgnore.isPresent() 
						&& reflexionToIgnore.get().a() == Orientation.VERTICAL 
						&& reflexionToIgnore.get().b() == x + 1)) {
					return x + 1;
				}
			}
		}
		
		return -1;
	}
}
