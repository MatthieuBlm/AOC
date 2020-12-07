package com.matthieu.aoc_2020.service.resolver.year_2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.matthieu.aoc_2020.exception.PrepareDataException;
import com.matthieu.aoc_2020.exception.SolveException;
import com.matthieu.aoc_2020.model.Point;

public class Resolver3p2 extends Resolver3p1 {

	private List<Point> slopes;
	private List<String> collision;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		
		this.collision = new ArrayList<>();
		this.slopes = Arrays.asList(new Point(1, 1), new Point(3, 1), new Point(5, 1), new Point(7, 1), new Point(1, 2));
	}
	
	@Override
	public boolean solve() throws SolveException {
		for (Point point : slopes) {
			this.setA(point.getX());
			this.setB(point.getY());
			
			super.solve();
			
			this.collision.add(super.get());
		}

		super.treesEnconters = this.collision.stream().mapToInt(Integer::valueOf).reduce(1, (a, b) -> a * b);
		
		return true;
	}
}
