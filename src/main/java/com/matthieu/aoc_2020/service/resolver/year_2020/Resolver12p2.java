package com.matthieu.aoc_2020.service.resolver.year_2020;

import com.matthieu.aoc_2020.exception.SolveException;
import com.matthieu.aoc_2020.model.Direction;
import com.matthieu.aoc_2020.model.WayPointShip;

public class Resolver12p2 extends Resolver12p1 {

	@Override
	public boolean solve() throws SolveException {
		
		this.ship = new WayPointShip(Direction.EAST);

		super.solve();
		
		return true;
	}
}
