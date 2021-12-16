package com.matthieu.aoc.model.year_2021.bits.impl;

import com.matthieu.aoc.model.year_2021.bits.OperatorPacket;

public class LowerThanPacket extends OperatorPacket {

	public LowerThanPacket(StringBuilder binaryData) {
		super(binaryData);
	}

	@Override
	public long getValue() {
		return this.getSubPacket().get(0).getValue() < this.getSubPacket().get(1).getValue() ? 1 : 0;
	}

}
