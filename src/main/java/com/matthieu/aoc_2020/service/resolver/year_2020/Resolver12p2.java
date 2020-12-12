package com.matthieu.aoc_2020.service.resolver.year_2020;

import com.matthieu.aoc_2020.exception.SolveException;
import com.matthieu.aoc_2020.model.Direction;
import com.matthieu.aoc_2020.model.Ship;
import com.matthieu.aoc_2020.model.WayPointShip;

public class Resolver12p2 extends Resolver12p1 {

	@Override
	public boolean solve() throws SolveException {
		
		this.ship = new WayPointShip(Direction.EAST);

		super.solve();
		
		return true;
	}

	
	public static void main(String[] args) {
		Ship s = new WayPointShip(Direction.EAST);
		
		s.moveForward(10);
		System.out.println(s);
		s.move(Direction.NORTH, 3);
		System.out.println(s);
		s.moveForward(7);
		System.out.println(s);
		s.turnRight(90);
		System.out.println(s);
		s.moveForward(11);
		System.out.println(s);
	}
}
