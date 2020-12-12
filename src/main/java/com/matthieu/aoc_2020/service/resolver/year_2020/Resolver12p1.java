package com.matthieu.aoc_2020.service.resolver.year_2020;

import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc_2020.exception.PrepareDataException;
import com.matthieu.aoc_2020.exception.SolveException;
import com.matthieu.aoc_2020.model.Direction;
import com.matthieu.aoc_2020.model.Ship;
import com.matthieu.aoc_2020.model.tuple.Tuple;
import com.matthieu.aoc_2020.service.parser.Parser;
import com.matthieu.aoc_2020.service.resolver.Resolver;

public class Resolver12p1 implements Resolver {

	protected Ship ship;
	protected List<Tuple<Character, Integer>> instructions;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		Parser<Tuple<Character, Integer>> instParser = s -> new Tuple<>(Character.valueOf(s.charAt(0)), Integer.valueOf(s.substring(1)));
		
		this.instructions = values.stream().map(instParser::parse).collect(Collectors.toList());
		this.ship = new Ship(Direction.EAST);
	}

	@Override
	public boolean solve() throws SolveException {

		for (Tuple<Character, Integer> instruction : instructions) {
			
			if(instruction.getKey().equals('N')) {
				this.ship.move(Direction.NORTH, instruction.getValue());
				
			} else if(instruction.getKey().equals('S')) {
				this.ship.move(Direction.SOUTH, instruction.getValue());
				
			} else if(instruction.getKey().equals('W')) {
				this.ship.move(Direction.WEST, instruction.getValue());
				
			} else if(instruction.getKey().equals('E')) {
				this.ship.move(Direction.EAST, instruction.getValue());
				
			} else if(instruction.getKey().equals('F')) {
				this.ship.moveForward(instruction.getValue());
				
			} else if(instruction.getKey().equals('L')) {
				this.ship.turnLeft(instruction.getValue());
				
			} else if(instruction.getKey().equals('R')) {
				this.ship.turnRight(instruction.getValue());
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.getManathanDistance());
	}
	
	
	protected int getManathanDistance() {
		return Math.abs(this.ship.getX()) + Math.abs(this.ship.getY());
	}

}
