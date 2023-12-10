package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Direction;
import com.matthieu.aoc.model.matrix.Cell;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver10p1 implements Resolver {
	
	protected Matrix<Character> map;
	protected Matrix<Integer> distance;
	protected Cell<Character> start;
	protected List<PipePos> currentPositions;

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.map = new Matrix<>(values, s -> s.charAt(0), "");
		this.distance = new Matrix<>(map.getWidth(), map.getHeight(), () -> null);
		this.start = this.map.cellStream().filter(c -> c.value() == 'S').findAny().orElseThrow();
		this.currentPositions = new ArrayList<>();
		
		
		this.map.forEachNeigthboursCross(start.x(), start.y(), (x, y, c) -> {
			if(x - start.x() == 1 && pipeToWest(c)) {
				currentPositions.add(new PipePos(x, y, c, Direction.WEST));
				
			} else if(x - start.x() == - 1 && pipeToEast(c)) {
				currentPositions.add(new PipePos(x, y, c, Direction.EAST));
				
			} else if(y - start.y() == 1 && pipeToNorth(c)) {
				currentPositions.add(new PipePos(x, y, c, Direction.NORTH));
				
			} else if(y - start.y() == -1 && pipeToSouth(c)) {
				currentPositions.add(new PipePos(x, y, c, Direction.SOUTH));
			}
		});
		
	}

	@Override
	public boolean solve() throws Exception {
		
		while(!this.currentPositions.isEmpty()) {
			
			this.currentPositions = currentPositions.stream()
					.map(this::nextPos)
					.filter(pos -> pos.c != 'S').toList();
		}
		
		return true;
	}

	@Override
	public String get() {
		return this.distance.stream().mapToInt(Integer::intValue).max() + "";
	}
	
	protected PipePos nextPos(PipePos current) {
		final List<PipePos> nexts = new ArrayList<>();
		
		this.map.forEachNeigthboursCross(current.x, current.y, (nextX, nextY, c) -> {
			if(current.x - nextX == 1 && pipeToWest(current.c) && pipeToEast(c) && current.from != Direction.EAST) {
				nexts.add(new PipePos(nextX, nextY, c, Direction.WEST));
				
			} else if(current.y - nextX == - 1 && pipeToEast(current.c) && pipeToWest(c) && current.from != Direction.WEST) {
				nexts.add(new PipePos(nextX, nextY, c, Direction.EAST));
				
			} else if(current.y - nextY == 1 && pipeToNorth(current.c) && pipeToSouth(c) && current.from != Direction.SOUTH) {
				nexts.add(new PipePos(nextX, nextY, c, Direction.NORTH));
				
			} else if(current.y - nextY == -1 && pipeToSouth(current.c) && pipeToNorth(c) && current.from != Direction.NORTH) {
				nexts.add(new PipePos(nextX, nextY, c, Direction.SOUTH));
			}
		});
		
		if(nexts.size() != 1) {
			throw new IllegalStateException(nexts.stream().map(PipePos::toString).collect(Collectors.joining()));
		}
		
		PipePos next = nexts.get(0);

		// Increment distance calculation
		Integer nextDistance = this.distance.get(next.x, next.y);
		int currentIncremented = this.distance.get(current.x, current.y) + 1;
		
		// If next is null, use current increment, or else use minimum of these two values
		int newDistance = nextDistance == null ? currentIncremented : Math.min(currentIncremented, nextDistance);
		this.distance.set(next.x, next.y, newDistance);
		
		return next;
	}
	
	protected boolean pipeToNorth(char c) {
		return c == '|' || c == 'L' || c == 'J';
	}
	protected boolean pipeToEast(char c) {
		return c == '-' || c == 'F' || c == 'L';
	}
	protected boolean pipeToSouth(char c) {
		return c == '|' || c == '7' || c == 'F';
	}
	protected boolean pipeToWest(char c) {
		return  c == '-' || c == 'J' || c == '7';
	}
	
	protected Direction nextDirection(Character currentPipe, Direction from) {
		switch(currentPipe) {
		case '|':
		case '-':
			return from;
		case 'F':
			return from == Direction.SOUTH ? Direction.EAST : Direction.SOUTH;
		case '7':
			return from == Direction.SOUTH ? Direction.WEST : Direction.SOUTH;
		case 'J':
			return from == Direction.NORTH ? Direction.WEST : Direction.NORTH;
		case 'L':
			return from == Direction.NORTH ? Direction.WEST : Direction.NORTH;
		}
		
		throw new IllegalStateException(currentPipe + " " + from);
	}
	
	protected class PipePos {
		protected int x;
		protected int y;
		protected char c;
		protected Direction from;
		
		public PipePos(int x, int y, char c, Direction from) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.from = from;
		}

		@Override
		public String toString() {
			return "PipePos [x=" + x + ", y=" + y + ", c=" + c + ", from=" + from + "]";
		}
		
	}

}
