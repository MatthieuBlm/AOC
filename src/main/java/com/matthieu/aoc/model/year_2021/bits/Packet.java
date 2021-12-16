package com.matthieu.aoc.model.year_2021.bits;

import java.util.ArrayList;
import java.util.List;

public abstract class Packet {

	private int version;
	private int typeId;
	protected List<Packet> subPackets;
	
	
	public abstract long getValue();
	
	public Packet(StringBuilder binaryData) {
		this.subPackets = new ArrayList<>();
		this.version = Integer.parseInt(this.consume(binaryData, 3), 2);
		this.typeId = Integer.parseInt(this.consume(binaryData, 3), 2);
	}
	
	public String consume(StringBuilder binaryData, int n) {
		String data = binaryData.substring(0, n);
		
		binaryData.delete(0, n);
		
		return data;
	}
	
	
	public long getVersionSum() {
		return this.version + this.subPackets.stream().mapToLong(Packet::getVersionSum).sum();
	}

	public List<Packet> getSubPacket() {
		return subPackets;
	}

	public int getVersion() {
		return version;
	}

	public int getTypeId() {
		return typeId;
	}
}
