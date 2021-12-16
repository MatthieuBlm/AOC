package com.matthieu.aoc.model.year_2021.bits;

import java.util.ArrayList;
import java.util.List;

public class PacketParser {

	private PacketParser() {}
	
	public static List<Packet> parse(StringBuilder data) {
		List<Packet> packets = new ArrayList<>();
		
		while(data.length() > 0 && contains1(data)) {
			packets.add(parsePacket(data));
		}
		
		return packets;
	}
	
	public static Packet parsePacket(StringBuilder data) {
		int typeId = Integer.parseInt(data.substring(3, 6), 2);
		
		if(typeId == 4) {
			return new LiteralPacket(data);
		} else {
			return new OperatorPacket(data);
		}
	}
	
	protected static boolean contains1(StringBuilder builder) {
		for (int i = 0; i < builder.length(); i++) {
			if(builder.charAt(i) == '1') {
				return true;
			}
		}
		
		return false;
	}
}
