package com.matthieu.aoc.resolver.year_2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.matthieu.aoc.model.Direction;
import com.matthieu.aoc.model.matrix.Matrix;

public class Resolver9p2 extends Resolver9p1 {
	
	private List<Integer> basinsSize = new ArrayList<>();
	private Matrix<Boolean> alreadyInBasin = new Matrix<>(100, 100, () -> Boolean.FALSE);

	
	@Override
	protected void processLowestPoint(Integer current, int x, int y) {
		List<Integer> basin = new ArrayList<>();
		
		this.buildBasin(basin, x, y, null);
		
		this.basinsSize.add(basin.size());
	}


	private void buildBasin(List<Integer> basin, int x, int y, Direction from) {
		Integer i = this.matrix.getQuietly(x, y);
		boolean alreadyVisit = Boolean.TRUE.equals(this.alreadyInBasin.getQuietly(x, y));
		
		if(i == null || i == 9 || alreadyVisit)
			return;
		
		basin.add(i);
		alreadyInBasin.set(x, y, Boolean.TRUE);
		
		if(from != Direction.NORTH)
			buildBasin(basin, x, y + 1, Direction.SOUTH);
		
		if(from != Direction.SOUTH)
			buildBasin(basin, x, y - 1, Direction.NORTH);
		
		if(from != Direction.WEST)
			buildBasin(basin, x + 1, y, Direction.EAST);
		
		if(from != Direction.EAST)
			buildBasin(basin, x - 1, y, Direction.WEST);
	}

	@Override
	public String get() {
		Collections.sort(this.basinsSize);
		int n = this.basinsSize.size();
		
		return String.valueOf(this.basinsSize.get(n -1) * this.basinsSize.get(n - 2) * this.basinsSize.get(n - 3));
	}

}
