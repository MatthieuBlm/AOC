package com.matthieu.aoc.model.year_2021.bits;

import java.util.List;

public class OperatorPacket extends Packet {

	private List<Packet> packets;
	
	public OperatorPacket(StringBuilder binaryData) {
		super(binaryData);
	}

	public List<Packet> getPackets() {
		return packets;
	}
	
}
