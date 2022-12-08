package com.matthieu.aoc.resolver.year_2022;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver8p1 implements Resolver {
	
	protected Matrix<Integer> grid;
	protected Matrix<Boolean> visibleTrees;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.grid = new Matrix<>(values, Integer::parseInt);
		this.visibleTrees = new Matrix<>(grid.getWidth(), grid.getHeight(), () -> Boolean.FALSE);
	}

	@Override
	public boolean solve() throws SolveException {
		// Edges
		for (int x = 0; x < grid.getWidth(); x++) this.visibleTrees.set(x, 0, Boolean.TRUE);
		for (int x = 0; x < grid.getWidth(); x++) this.visibleTrees.set(x, grid.getHeight() - 1, Boolean.TRUE);
		for (int y = 0; y < grid.getHeight(); y++) this.visibleTrees.set(0, y, Boolean.TRUE);
		for (int y = 0; y < grid.getHeight(); y++) this.visibleTrees.set(grid.getWidth() - 1, y, Boolean.TRUE);
		
		grid.forEach((x, y, t) -> {
			if(x > 0 && x < grid.getWidth() - 1 && y > 0 && y < grid.getHeight() - 1) {
				if(isVisible(x, y, t)) {
					this.visibleTrees.set(x, y, Boolean.TRUE);
				}
			}
		});
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.visibleTrees.stream().mapToInt(b -> b.equals(Boolean.TRUE) ? 1 : 0).sum());
	}
	
	protected boolean isVisible(int x, int y, int height) {
		
		return isVisibleFromTop(x, y, height) || isVisibleFromRight(x, y, height) ||
				isVisibleFromBottom(x, y, height) || isVisibleFromLeft(x, y, height);
	}
	
	protected boolean isVisibleFromLeft(int x, int y, int height) {
		for (int i = 0; i < x; i++) {
			if(this.grid.get(i, y) >= height) {
				return false;
			}
		}
		
		return true;
	}
	
	protected boolean isVisibleFromRight(int x, int y, int height) {
		for (int i = grid.getWidth() - 1; i > x; i--) {
			if(this.grid.get(i, y) >= height) {
				return false;
			}
		}
		
		return true;
	}

	protected boolean isVisibleFromTop(int x, int y, int height) {
		for (int i = 0; i < y; i++) {
			if(this.grid.get(x, i) >= height) {
				return false;
			}
		}
		
		return true;
	}

	protected boolean isVisibleFromBottom(int x, int y, int height) {
		for (int i = grid.getHeight() - 1; i > y; i--) {
			if(this.grid.get(x, i) >= height) {
				return false;
			}
		}
		
		return true;
	}

}
