package com.matthieu.aoc.resolver.year_2022;

import java.util.Arrays;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver8p1 implements Resolver {
	
	protected Matrix<Integer> grid;
	protected Matrix<Boolean> visibleTrees;
	protected List<Duo<Integer, Integer>> directions;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.grid = new Matrix<>(values, Integer::parseInt);
		this.visibleTrees = new Matrix<>(grid.getWidth(), grid.getHeight(), () -> Boolean.FALSE);
		this.directions = Arrays.asList(new Duo<>(0, 1), new Duo<>(1, 0), new Duo<>(0, -1), new Duo<>(-1, 0));
	}

	@Override
	public boolean solve() throws SolveException {
		grid.forEach((x, y, h) -> {
			if(isVisible(x, y, h)) {
				this.visibleTrees.set(x, y, Boolean.TRUE);
			}
		});
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.visibleTrees.stream().mapToInt(b -> b.equals(Boolean.TRUE) ? 1 : 0).sum());
	}
	
	protected boolean isVisible(int x, int y, int height) {
		return directions.stream().anyMatch(d -> isVisibleFrom(x, y, height, d));
	}
	
	protected boolean isVisibleFrom(int x,int y, int height, Duo<Integer, Integer> direction) {
		Integer neightbourHeight;
		Duo<Integer, Integer> pos = new Duo<>(x + direction.x(), y + direction.y());
		
		while((neightbourHeight = this.grid.getQuietly(pos.x(), pos.y())) != null){
			pos.x(pos.x() + direction.x());
			pos.y(pos.y() + direction.y());
			
			if(neightbourHeight >= height) {
				return false;
			}
		}
		
		return true;
	}
}
