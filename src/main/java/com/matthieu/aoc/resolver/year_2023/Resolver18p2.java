package com.matthieu.aoc.resolver.year_2023;

import com.matthieu.aoc.model.Direction;

public class Resolver18p2 extends Resolver18p1 {

	@Override
	protected int getInstructionDistance(Instruction i) {
		return i.d2;
	}
	
	@Override
	protected Direction getInstructionWay(Instruction i) {
		return i.way2;
	}
	
}
