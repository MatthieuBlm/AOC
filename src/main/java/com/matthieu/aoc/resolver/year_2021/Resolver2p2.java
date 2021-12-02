package com.matthieu.aoc.resolver.year_2021;

import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver2p2 extends Resolver2p1 {

	private int aim;
	
	@Override
	public boolean solve() throws SolveException {
		
		for (Duo<String, Integer> duo : this.instructions) {
			switch(duo.a()) {
			case "forward":
				this.horizontal += duo.b();
				this.depth += this.aim * duo.b();
				break;
			case "down":
				this.aim += duo.b();
				break;
			case "up":
				this.aim -= duo.b();
			}
		}
		
		return true;
	}
}
