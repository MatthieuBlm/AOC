package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Direction;
import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.Polygon;
import com.matthieu.aoc.model.matrix.Matrix;

public class Resolver10p2 extends Resolver10p1 {

	private Matrix<Character> path;
	private Polygon loop;
	private int pointsInside;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		
		this.path = new Matrix<>(map.getWidth(), map.getHeight(), () -> '.');
		this.path.set(start.x(), start.y(), '|');
		this.loop = new Polygon();
		this.loop.addPoint(start.x(), start.y());
	}
	
	@Override
	public boolean solve() throws Exception {
		PipePos current = this.currentPositions.get(0);
		
		while(current != null) {
			
			this.path.set(current.x, current.y, current.c);
			
			if(current.c != '-' && current.c != '|') {
				this.loop.addPoint(current.x, current.y);
			}
			
			current = nextPos(current);
		}
		
		this.path.forEach((x, y, isPath) -> {
			if(isPath == '.' && isInside(x, y)) {
				this.pointsInside++;
			}
		});
		
		return true;
	}
	
	@Override
	public String get() {
		return this.pointsInside + "";
	}
	
	@Override
	protected PipePos nextPos(PipePos current) {
		final List<PipePos> nexts = new ArrayList<>();
		
		this.map.forEachNeigthboursCross(current.x, current.y, (nextX, nextY, c) -> {
			if(nextX - current.x == 1 && pipeToEast(current.c) && pipeToWest(c) && current.from != Direction.EAST) {
				nexts.add(new PipePos(nextX, nextY, c, Direction.WEST));
				
			} else if(nextX - current.x == -1 && pipeToWest(current.c) && pipeToEast(c) && current.from != Direction.WEST) {
				nexts.add(new PipePos(nextX, nextY, c, Direction.EAST));
				
			} else if(nextY - current.y == 1 && pipeToSouth(current.c) && pipeToNorth(c) && current.from != Direction.SOUTH) {
				nexts.add(new PipePos(nextX, nextY, c, Direction.NORTH));
				
			} else if(nextY - current.y == -1 && pipeToNorth(current.c) && pipeToSouth(c) && current.from != Direction.NORTH) {
				nexts.add(new PipePos(nextX, nextY, c, Direction.SOUTH));
			}
		});
		
		if(nexts.size() > 1) {
			throw new IllegalStateException(nexts.stream().map(PipePos::toString).collect(Collectors.joining()));
		} else if(nexts.isEmpty()) {
			return null;
		}
		
		return nexts.get(0);
	}
	
	private boolean isInside(int x, int y) {
		int intersections = 0;
		Point p = new Point(--x, y);
		
		while(p.x() >= 0) {
			char currentPipe = this.path.get(p.x(), p.y());
			
			if(currentPipe == '|' || currentPipe == 'F' || currentPipe == '7' ) {
				intersections++;
			}
			
			p.setX(--x);
		}
		
		return intersections % 2 == 1;
	}
	
}
