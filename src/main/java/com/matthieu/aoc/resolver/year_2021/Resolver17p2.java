package com.matthieu.aoc.resolver.year_2021;

import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver17p2 extends Resolver17p1 {

	private int validatedVelocity;
	
	
	@Override
	public boolean solve() throws SolveException {
		
		for (int vx = 0; vx < 290 ; vx++) {
			for (int vy = 2000; vy > -90; vy--) {
				
				Duo<Integer, Integer> velocity = new Duo<>(vx, vy);
				Duo<Integer, Integer> pointer = new Duo<>(0, 0);
				
				while (pointer.y() > yTarget.a()) {
					
					this.calcOneStep(pointer, velocity);
					
					if(this.isInTragetArea(pointer)) {
						validatedVelocity++;
						break;
					}
				}
			}
		}
		
		return true;
	}
	
	@Override
	public String get() {
		return String.valueOf(this.validatedVelocity);
	}
}
