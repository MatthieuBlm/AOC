package com.matthieu.aoc.model.year_2021.bits.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.model.year_2021.bits.Packet;

public class LiteralPacket extends Packet {

	private List<String> data;
	
	public LiteralPacket(StringBuilder binaryData) {
		super(binaryData);
		
		this.data = new ArrayList<>();
		
		String buffer = null;
		
		// While leading 0 of 5 bits group is not 0
		do {
			buffer = this.consume(binaryData, 5);
			data.add(buffer.substring(1));
			
		} while(buffer.charAt(0) != '0');
	}
	
	public long getValue() {
		return Long.parseLong(this.data.stream().collect(Collectors.joining()), 2);
	}
	
	@Override
	public String toString() {
		return String.format("[%s,%s] : %s", super.getVersion(), super.getTypeId(), this.getValue());
	}

}
