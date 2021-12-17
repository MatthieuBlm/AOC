package com.matthieu.aoc.resolver.year_2021;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.year_2021.bits.Packet;
import com.matthieu.aoc.model.year_2021.bits.PacketParser;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver16p1 implements Resolver {

	protected String hexValue;
	protected StringBuilder binaryValue;
	protected long versionSum;

	protected List<Packet> packets;
	
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.hexValue = values.get(0);
		this.binaryValue = this.hexToBenary(this.hexValue);
	}

	@Override
	public boolean solve() throws SolveException {
		
		this.packets = PacketParser.parse(binaryValue);
		
		this.versionSum = this.packets.stream().mapToLong(Packet::getVersionSum).sum();
		
		return true;
	}

	@Override
	public String get() {
		System.out.println(this.packets.get(0).toString());
		
		return String.valueOf(this.versionSum);
	}

	
	protected StringBuilder hexToBenary(String value) {
		StringBuilder result = new StringBuilder();
		
		for (char c : value.toCharArray()) {
			result.append(this.getBinary(String.valueOf(c)));
		}
		
		return result;
	}
	
	protected String getBinary(String hexString) {
		int intString = Integer.parseInt(hexString, 16);
		StringBuilder result = new StringBuilder(Integer.toBinaryString(intString));
		
		// Add leading '0'
		while(result.length() < 4)
			result.insert(0, "0");
		
		return result.toString();
	}
}
