package com.matthieu.aoc.model.year_2021.bits.impl;

import com.matthieu.aoc.model.year_2021.bits.OperatorPacket;
import com.matthieu.aoc.model.year_2021.bits.Packet;

public class ProductPacket extends OperatorPacket {

	public ProductPacket(StringBuilder binaryData) {
		super(binaryData);
	}

	@Override
	public long getValue() {
		return this.subPackets.stream().mapToLong(Packet::getValue).reduce((a, b) -> a * b).getAsLong();
	}

}
