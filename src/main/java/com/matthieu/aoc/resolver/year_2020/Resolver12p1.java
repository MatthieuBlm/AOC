package com.matthieu.aoc.resolver.year_2020;

import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.Direction;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.model.year_2020.Ship;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.parser.Parser;

public class Resolver12p1 implements Resolver {

	protected Ship ship;
	protected List<Duo<Character, Integer>> instructions;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		Parser<Duo<Character, Integer>> instParser = s -> new Duo<>(Character.valueOf(s.charAt(0)), Integer.valueOf(s.substring(1)));
		
		this.instructions = values.stream().map(instParser::parse).collect(Collectors.toList());
		this.ship = new Ship(Direction.EAST);
	}

	@Override
	public boolean solve() throws SolveException {

		for (Duo<Character, Integer> instruction : instructions) {
			
			if(instruction.a().equals('N')) {
				this.ship.move(Direction.NORTH, instruction.b());
				
			} else if(instruction.a().equals('S')) {
				this.ship.move(Direction.SOUTH, instruction.b());
				
			} else if(instruction.a().equals('W')) {
				this.ship.move(Direction.WEST, instruction.b());
				
			} else if(instruction.a().equals('E')) {
				this.ship.move(Direction.EAST, instruction.b());
				
			} else if(instruction.a().equals('F')) {
				this.ship.moveForward(instruction.b());
				
			} else if(instruction.a().equals('L')) {
				this.ship.turnLeft(instruction.b());
				
			} else if(instruction.a().equals('R')) {
				this.ship.turnRight(instruction.b());
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
