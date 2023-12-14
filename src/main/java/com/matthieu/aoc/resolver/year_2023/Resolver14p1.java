package com.matthieu.aoc.resolver.year_2023;

import java.util.List;
import java.util.stream.IntStream;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Direction;
import com.matthieu.aoc.model.matrix.CharMatrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver14p1 implements Resolver {

	protected CharMatrix map;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.map = new CharMatrix(values);
	}

	@Override
	public boolean solve() throws Exception {
		this.tilt(Direction.NORTH);
		
		return true;
	}

	@Override
	public String get() {
		return getNorthLoad() + "";
	}
	
	protected long getNorthLoad() {
		return IntStream.range(0, this.map.getHeight())
					.map(y -> map.getRow(y).stream()
							.filter(c -> c == 'O')
							.mapToInt(rock -> map.getHeight() - y)
							.sum())
					.sum();
	}

	protected void tilt(Direction direction) {
		if(direction == Direction.NORTH) {
			this.tiltNorth();
		} else if(direction == Direction.EAST) {
			this.tiltEast();
		} else if(direction == Direction.SOUTH) {
			this.tiltSouth();
		} else if(direction == Direction.WEST) {
			this.tiltWest();
		}
	}
	

	private void tiltNorth() {
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				if(map.get(x, y) == 'O') {
					for (int newY = y - 1; newY >= 0 && map.get(x, newY) != '#' && map.get(x, newY) != 'O'; newY--) {
						this.map.set(x, newY, 'O');
						this.map.set(x, newY + 1, '.');
					}
				}
			}
		}
	}

	private void tiltWest() {
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				if(map.get(x, y) == 'O') {
					for (int newX = x - 1; newX >= 0 && map.get(newX, y) != '#' && map.get(newX, y) != 'O'; newX--) {
						this.map.set(newX, y, 'O');
						this.map.set(newX + 1, y, '.');
					}
				}
			}
		}
	}
	
	private void tiltSouth() {
		for (int y = map.getMaxY(); y >= 0; y--) {
			for (int x = 0; x < map.getWidth(); x++) {
				if(map.get(x, y) == 'O') {
					for (int newY = y + 1; newY <= map.getMaxY() && map.get(x, newY) != '#' && map.get(x, newY) != 'O'; newY++) {
						this.map.set(x, newY, 'O');
						this.map.set(x, newY - 1, '.');
					}
				}
			}
		}
	}
	
	private void tiltEast() {
		for (int x = map.getMaxX(); x >= 0; x--) {
			for (int y = 0; y < map.getHeight(); y++) {
				if(map.get(x, y) == 'O') {
					for (int newX = x + 1; newX <= map.getMaxX() && map.get(newX, y) != '#' && map.get(newX, y) != 'O'; newX++) {
						this.map.set(newX, y, 'O');
						this.map.set(newX - 1, y, '.');
					}
				}
			}
		}
		
	}
	
}
