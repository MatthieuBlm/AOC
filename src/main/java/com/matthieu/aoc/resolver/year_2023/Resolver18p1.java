package com.matthieu.aoc.resolver.year_2023;

import java.text.DecimalFormat;
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
			
			if(getInstructionWay(i) == Direction.EAST) {
				current = new Point(current.x() + getInstructionDistance(i), current.y());
			} else if(getInstructionWay(i) == Direction.SOUTH) {
				current = new Point(current.x(), current.y() + getInstructionDistance(i));
			} else if(getInstructionWay(i) == Direction.WEST) {
				current = new Point(current.x() - (getInstructionDistance(i)), current.y());
			} else if(getInstructionWay(i) == Direction.NORTH) {
				current = new Point(current.x(), current.y() - (getInstructionDistance(i)));
			}
			
			polygon.addPoint(current);
		}
		
		return true;
	}

	@Override
	public String get() {
		double interior = (polygon.getArea() + 1 - (polygon.getPerimeter() / 2l));
		
		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumFractionDigits(1);
		
		return df.format((interior + this.polygon.getPerimeter())) + "";
	}

	protected int getInstructionDistance(Instruction i) {
		return i.d1;
	}
	
	protected Direction getInstructionWay(Instruction i) {
		return i.way;
	}
	
	protected class Instruction {
		protected Direction way;
		protected Direction way2;
		protected int d1;
		protected int d2;
		
		public Instruction(List<String> values) {
			this.d1 = Integer.parseInt(values.get(1));
			this.d2 = Integer.parseInt(values.get(2).substring(0, 5), 16);
			
			this.way = values.get(0).equals("R") ? Direction.EAST : 
				values.get(0).equals("D") ? Direction.SOUTH :
				values.get(0).equals("L") ? Direction.WEST : 
				Direction.NORTH;

			this.way2 = values.get(2).charAt(5) == '0' ? Direction.EAST :
				values.get(2).charAt(5) == '1' ? Direction.SOUTH :
					values.get(2).charAt(5) == '2' ? Direction.WEST : 
						Direction.NORTH;
		}
		

		@Override
		public String toString() {
			return way +" "+ d1 +" "+ d2;
		}
		
	}
	
}
