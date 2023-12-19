package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Direction;
import com.matthieu.aoc.model.matrix.Cell;
import com.matthieu.aoc.model.matrix.CharMatrix;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver16p1 implements Resolver {

	protected CharMatrix map;
	protected Matrix<List<Direction>> lights;
	protected List<Cell<Direction>> beams;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.map = new CharMatrix(values);
		this.lights = new Matrix<>(map.getWidth(), map.getHeight(), () -> new ArrayList<>());
		this.beams = new ArrayList<>();
		this.beams.add(new Cell<>(0, 0, Direction.EAST));
//		this.lights.get(0, 0).add(Direction.EAST);
	}

	@Override
	public boolean solve() throws Exception {
		
		while(!beams.isEmpty()) {
			List<Cell<Direction>> nextBeams = new ArrayList<>();
			
			for (Cell<Direction> beam : beams) {
				
				if(	beam.x() >= 0 && beam.x() <= lights.getMaxX() && 
					beam.y() >= 0 && beam.y() <= lights.getMaxY() && 
					lights.get(beam.x(), beam.y()).indexOf(beam.value()) == -1) {
					
					if(map.get(beam.x(), beam.y()) == '|' && (beam.value() == Direction.EAST || beam.value() == Direction.WEST)) {
						addBeamIfNecessary(beam.x(), beam.y(), Direction.NORTH, nextBeams);
						addBeamIfNecessary(beam.x(), beam.y(), Direction.SOUTH, nextBeams);
					} else if(map.get(beam.x(), beam.y()) == '-' && (beam.value() == Direction.NORTH || beam.value() == Direction.SOUTH)) {
						addBeamIfNecessary(beam.x(), beam.y(), Direction.WEST, nextBeams);
						addBeamIfNecessary(beam.x(), beam.y(), Direction.EAST, nextBeams);
					} else if(map.get(beam.x(), beam.y()) == '\\') {
						
						if(beam.value() == Direction.EAST) {
							addBeamIfNecessary(beam.x(), beam.y(), Direction.SOUTH, nextBeams);
						} else if(beam.value() == Direction.SOUTH) {
							addBeamIfNecessary(beam.x(), beam.y(), Direction.EAST, nextBeams);
						} else if(beam.value() == Direction.WEST) {
							addBeamIfNecessary(beam.x(), beam.y(), Direction.NORTH, nextBeams);
						} else if(beam.value() == Direction.NORTH) {
							addBeamIfNecessary(beam.x(), beam.y(), Direction.WEST, nextBeams);
						}
						
					} else if(map.get(beam.x(), beam.y()) == '/') {
						
						if(beam.value() == Direction.EAST) {
							addBeamIfNecessary(beam.x(), beam.y(), Direction.NORTH, nextBeams);
						} else if(beam.value() == Direction.NORTH) {
							addBeamIfNecessary(beam.x(), beam.y(), Direction.EAST, nextBeams);
						} else if(beam.value() == Direction.WEST) {
							addBeamIfNecessary(beam.x(), beam.y(), Direction.SOUTH, nextBeams);
						} else if(beam.value() == Direction.SOUTH) {
							addBeamIfNecessary(beam.x(), beam.y(), Direction.WEST, nextBeams);
						}
						
					} else {
						nextBeams.add(beam);
						this.lights.get(beam.x(), beam.y()).add(beam.value());
					}
				}
			}
			
			beams = nextBeams;
			
			for (Cell<Direction> beam : beams) {
				if(beam.value() == Direction.EAST) {
					beam.x(beam.x() + 1);
				} else if(beam.value() == Direction.SOUTH) {
					beam.y(beam.y() + 1);
				} else if(beam.value() == Direction.WEST) {
					beam.x(beam.x() - 1);
				} else if(beam.value() == Direction.NORTH) {
					beam.y(beam.y() - 1);
				}
			}
			
			printLights();
		}
		
		return true;
	}

	@Override
	public String get() {
		return this.lights.stream().filter(Predicate.not(List::isEmpty)).count() + "";
	}

	protected void addBeamIfNecessary(int x, int y, Direction direction, List<Cell<Direction>> target) {
		if(isNotAlreayVisitedCell(x, y, direction)) {
			lights.get(x, y).add(direction);
			target.add(new Cell<>(x, y, direction));
			
			List<Direction> lightsDirection = lights.get(x, y);
			lightsDirection.add(direction);
			lights.set(x, y, lightsDirection);
		}
	}
	
	protected boolean isNotAlreayVisitedCell(int x, int y, Direction direction) {
		return this.lights.get(x, y).indexOf(direction) == -1;
	}
	
	protected void printLights() {
		for (int y = 0; y < this.lights.getHeight(); y++) {
			for (int x = 0; x < this.lights.getWidth(); x++) {
				if(this.map.get(x, y) == '.') {
					if(this.lights.get(x, y).isEmpty()) {
						System.out.print('.');
					} else if(this.lights.get(x, y).size() == 1) {
						if(this.lights.get(x, y).get(0) == Direction.EAST) {
							System.out.print('>');
						} else if(this.lights.get(x, y).get(0) == Direction.NORTH) {
							System.out.print('^');
						} else if(this.lights.get(x, y).get(0) == Direction.SOUTH) {
							System.out.print('v');
						} else if(this.lights.get(x, y).get(0) == Direction.WEST) {
							System.out.print('<');
						}
					} else {
						System.out.print(this.lights.get(x, y).size());
					}
				} else {
					System.out.print(this.map.get(x, y));
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}
}
