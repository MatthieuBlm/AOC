package com.matthieu.aoc.resolver.year_2021;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver17p1 implements Resolver {

	protected Duo<Integer, Integer> xTarget;
	protected Duo<Integer, Integer> yTarget;
	protected int maxY;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		String[] coords = values.get(0).replace("target area: ", "").split(", ");
		String[] xCoords = coords[0].replace("x=", "").split("\\.\\.");
		String[] yCoords = coords[1].replace("y=", "").split("\\.\\.");

		xTarget = new Duo<>(Integer.parseInt(xCoords[0]), Integer.parseInt(xCoords[1]));
		yTarget = new Duo<>(Integer.parseInt(yCoords[0]), Integer.parseInt(yCoords[1]));
	}

	@Override
	public boolean solve() throws SolveException {
		int startXVelocity = 1;
		
		
		int initialXVelocity = 1;
		int initialYVelocity = 2000;
		
		while (maxY == 0) {
			
			Duo<Integer, Integer> velocity = new Duo<>(initialXVelocity, initialYVelocity);
			Duo<Integer, Integer> pointer = new Duo<>(0, 0);
			int iterationMaxY = 0;
			
			while (pointer.y() > yTarget.a()) {
				
				this.calcOneStep(pointer, velocity);

				if(pointer.y() > iterationMaxY) {
					iterationMaxY = pointer.y();
				}
				
				if(this.isInTragetArea(pointer)) {
					this.maxY = iterationMaxY;
					return true;
				}
			}
			
			if(pointer.x() < xTarget.b()) {
				initialXVelocity++;
				
			} else if(pointer.x() > xTarget.a()) {
				initialYVelocity--;
				initialXVelocity = startXVelocity;
			}
		}
		
		return false;
	}

	@Override
	public String get() {
		return String.valueOf(this.maxY);
	}
	
	protected boolean isInTragetArea(Duo<Integer, Integer> coord) {
		return coord.x() >= xTarget.a() && coord.x() <= xTarget.b() && coord.y() >= yTarget.a() && coord.y() <= yTarget.b();
	}
	
	protected void calcOneStep(Duo<Integer, Integer> pointer, Duo<Integer, Integer> velocity) {
		pointer.x(pointer.x() + velocity.x());
		pointer.y(pointer.y() + velocity.y());
		
		if(velocity.x() > 0)
			velocity.x(velocity.x() - 1);
		else if (velocity.x() < 0)
			velocity.x(velocity.x() + 1);
		
		velocity.y(velocity.y() - 1);
	}

}
