package com.matthieu.aoc.model.year_2021.bits;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.model.year_2021.bits.impl.EqualsToPacket;
import com.matthieu.aoc.model.year_2021.bits.impl.GreaterThanPacket;
import com.matthieu.aoc.model.year_2021.bits.impl.LiteralPacket;
import com.matthieu.aoc.model.year_2021.bits.impl.LowerThanPacket;
import com.matthieu.aoc.model.year_2021.bits.impl.MaximumPacket;
import com.matthieu.aoc.model.year_2021.bits.impl.MinimumPacket;
import com.matthieu.aoc.model.year_2021.bits.impl.ProductPacket;
import com.matthieu.aoc.model.year_2021.bits.impl.SumPacket;

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
		
		if(typeId == 0) {
			return new SumPacket(data);
		} else if(typeId == 1) {
			return new ProductPacket(data);
		} else if(typeId == 2) {
			return new MinimumPacket(data);
		} else if(typeId == 3) {
			return new MaximumPacket(data);
		} else if(typeId == 4) {
			return new LiteralPacket(data);
		} else if(typeId == 5) {
			return new GreaterThanPacket(data);
		} else if(typeId == 6) {
			return new LowerThanPacket(data);
		} else if(typeId == 7) {
			return new EqualsToPacket(data);
		}
		
		throw new IllegalArgumentException("Unknown type ID "+ typeId);
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
