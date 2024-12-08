package com.matthieu.aoc.resolver.year_2024;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.model.Point;

public class Resolver8p2 extends Resolver8p1 {
	
	@Override
	public String get() {
		return this.map.cellStream()
				.mapToLong(cell -> cell.value() != '.' || !this.antinodes.get(cell.x(), cell.y()).isEmpty() ? 1 : 0)
				.sum() + "";
	}
	
	@Override
	protected List<Point> findAntinodes(Point a, Point b) {
		Point distance = new Point(b.x() - a.x(), b.y() - a.y());
		List<Point> antinodes = new ArrayList<>();
		
		Point antinode;
		for(int i = 1; true; i++) {
			antinode = new Point(a.x() - distance.x() * i, a.y() - distance.y() * i);
			
			if(antinode.x() < 0 || antinode.y() < 0) {
				break;
			}

			antinodes.add(antinode);
		}
		
		for(int i = 1; true; i++) {
			antinode = new Point(b.x() + distance.x() * i, b.y() + distance.y() * i);
			
			if(antinode.x() > map.getMaxX() || antinode.y() > map.getMaxY()) {
				break;
			}
			
			antinodes.add(antinode);
		}
		
		return antinodes;
	}
}
