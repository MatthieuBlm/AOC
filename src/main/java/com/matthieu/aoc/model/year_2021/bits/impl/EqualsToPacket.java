package com.matthieu.aoc.model.year_2021.bits.impl;

import com.matthieu.aoc.model.year_2021.bits.OperatorPacket;

public class EqualsToPacket extends OperatorPacket {

	public EqualsToPacket(StringBuilder binaryData) {
		super(binaryData);
	}

	@Override
	public long getValue() {
		return this.getSubPacket().get(0).getValue() == this.getSubPacket().get(1).getValue() ? 1 : 0;
	}

}
