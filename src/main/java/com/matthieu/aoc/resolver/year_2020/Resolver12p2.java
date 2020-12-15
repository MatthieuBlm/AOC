package com.matthieu.aoc.resolver.year_2020;

import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.Direction;
import com.matthieu.aoc.model.year_2020.WayPointShip;

public class Resolver12p2 extends Resolver12p1 {

	@Override
	public boolean solve() throws SolveException {
		
		this.ship = new WayPointShip(Direction.EAST);

		super.solve();
		
		return true;
	}
}
