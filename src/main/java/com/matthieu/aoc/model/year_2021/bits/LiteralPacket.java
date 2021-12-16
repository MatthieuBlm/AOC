package com.matthieu.aoc.model.year_2021.bits;

import java.util.List;

public class LiteralPacket extends Packet {

	private List<String> data;
	
	public LiteralPacket(StringBuilder binaryData) {
		super(binaryData);
		
		int bitConsumes = 0;
		String buffer = null;
		
		// While leading 0 of 5 bits group is not 0
		do {
			buffer = this.consume(binaryData, 5);
			bitConsumes += 5;
			data.add(buffer);
			
		} while(buffer.charAt(0) != '0');
		
		int fullPacketSize = this.toMultiple(bitConsumes, 4);
		
		this.consume(binaryData, fullPacketSize - bitConsumes);
	}

}
