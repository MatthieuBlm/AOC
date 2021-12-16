package com.matthieu.aoc.model.year_2021.bits;

import java.util.ArrayList;
import java.util.List;

public class PacketParser {

	public List<Packet> parse(StringBuilder data) {
		List<Packet> packets = new ArrayList<>();
		
		while(data.length() > 0) {
			int typeId = Integer.parseInt(data.substring(3, 6), 2);
			
			if(typeId == 4) {
				packets.add(new LiteralPacket(data));
			} else {
				packets.add(new OperatorPacket(data));
			}
		}
		
		return packets;
	}
	
}
