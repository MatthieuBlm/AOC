package com.matthieu.aoc.resolver.year_2023;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Direction;
import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.Polygon;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.Extractor;

public class Resolver18p1 implements Resolver {

	protected List<Instruction> instructions;
	protected Polygon polygon;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.instructions = values.stream()
				.map(Extractor::extractWords)
				.map(Instruction::new)
				.toList();
		
		this.polygon = new Polygon();
	}

	@Override
	public boolean solve() throws Exception {
		Point current = new Point(0, 0);
		
		for (Instruction i : instructions) {
			
			if(i.way == Direction.EAST) {
				current = new Point(current.x() + getInstructionDistance(i), current.y());
			} else if(i.way == Direction.SOUTH) {
				current = new Point(current.x(), current.y() + getInstructionDistance(i));
			} else if(i.way == Direction.WEST) {
				current = new Point(current.x() - (getInstructionDistance(i)), current.y());
			} else if(i.way == Direction.NORTH) {
				current = new Point(current.x(), current.y() - (getInstructionDistance(i)));
			}
			
			polygon.addPoint(current);
		}
		
		return true;
	}

	@Override
	public String get() {
		polygon.getPoints().stream().forEach(System.out::println);
		return this.polygon.getArea() + "";
	}

	protected int getInstructionDistance(Instruction i) {
		return i.d1;
	}
	
	protected class Instruction {
		protected Direction way;
		protected int d1;
		protected int d2;
		
		public Instruction(List<String> values) {
			this.d1 = Integer.parseInt(values.get(1));
			this.d2 = Integer.parseInt(values.get(2), 16);
			
			this.way = values.get(0).equals("R") ? Direction.EAST : 
				values.get(0).equals("D") ? Direction.SOUTH :
				values.get(0).equals("L") ? Direction.WEST : 
				Direction.NORTH;
		}

		@Override
		public String toString() {
			return way +" "+ d1 +" "+ d2;
		}
		
		
	}
	
}
