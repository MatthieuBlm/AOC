package com.matthieu.aoc.resolver.year_2023;

public class Resolver18p2 extends Resolver18p1 {

	@Override
	protected int getInstructionDistance(Instruction i) {
		return i.d2;
	}
}
