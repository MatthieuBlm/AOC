package com.matthieu.aoc.model.year_2021.bits.impl;

import com.matthieu.aoc.model.year_2021.bits.OperatorPacket;
import com.matthieu.aoc.model.year_2021.bits.Packet;

public class SumPacket extends OperatorPacket {

	public SumPacket(StringBuilder binaryData) {
		super(binaryData);
	}

	@Override
	public long getValue() {
		return this.subPackets.stream().mapToLong(Packet::getValue).sum();
	}

}
