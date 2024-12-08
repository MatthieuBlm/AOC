package com.matthieu.aoc.resolver.year_2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.matrix.CharMatrix;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver8p1 implements Resolver {

	protected CharMatrix map;
	protected Matrix<List<Character>> antinodes;
	protected List<Character> antennas;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.map = new CharMatrix(values);
		this.antinodes = new Matrix<>(this.map.getWidth(), this.map.getHeight(), () -> new ArrayList<>());
		this.antennas = map.stream().filter(c -> c != '.').distinct().toList();
	}

	@Override
	public boolean solve() throws Exception {
		
		this.map.forEach((x, y, c) -> {
			if(!antennas.contains(c)) {
				return;
			}
			
			Point otherAntenna;
			int fromX = x;
			int fromY = y;
			
			while((otherAntenna = this.findOtherAntenna(fromX, fromY, c)) != null) {
				List<Point> antinodes = findAntinodes(new Point(x, y), otherAntenna);
				antinodes.forEach(this::markAntinode);
				fromX = otherAntenna.x();
				fromY = otherAntenna.y();
			}
		});
		
		
		return true;
	}

	@Override
	public String get() {
		return this.antinodes.stream().filter(Predicate.not(List::isEmpty)).count() + "";
		
	}

	protected Point findOtherAntenna(int fromX, int fromY, Character c) {
		// Finish current line
		for(int x = fromX + 1; x < map.getWidth(); x++) {
			if(this.map.get(x, fromY) == c) {
				return new Point(x, fromY);
			}
		}
		
		// Browse the rest of the map
		for (int y = fromY + 1; y < map.getHeight(); y++) {
			for(int x = 0; x < map.getWidth(); x++) {
				if(this.map.get(x, y) == c) {
					return new Point(x, y);
				}
			}
		}
		
		return null;
	}
	
	protected List<Point> findAntinodes(Point a, Point b) {
		Point distance = new Point(b.x() - a.x(), b.y() - a.y());
		
		return Arrays.asList(new Point(a.x() - distance.x(), a.y() - distance.y()),
				new Point(b.x() + distance.x(), b.y() + distance.y()));
	}
	
	protected void markAntinode(Point antinode) {
		try {
			this.antinodes.get(antinode.x(), antinode.y()).add('#');
		} catch(IllegalArgumentException e) {}
	}
}
