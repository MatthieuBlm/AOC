package com.matthieu.aoc.model.year_2021.bits;

import java.util.stream.LongStream;

public class OperatorPacket extends Packet {

	private int lengthTypeId;
	private int subPacketLength;
	private String lengthType;
	
	public OperatorPacket(StringBuilder binaryData) {
		super(binaryData);
		
		this.lengthTypeId = Integer.parseInt(this.consume(binaryData, 1));
		this.subPacketLength = Integer.parseInt(this.consume(binaryData, this.getSubPacketNumberLength()), 2);
		
		
		if(this.lengthType.equals("TOTAL_LENGTH")) {
			StringBuilder subPacketsData = new StringBuilder(this.consume(binaryData, this.subPacketLength));

			do {
				this.subPackets.add(PacketParser.parsePacket(subPacketsData));
			} while(subPacketsData.length() > 0);
			
		} else if(this.lengthType.equals("PACKET_NUMBER")) {
			
			for (int i = 0; i < this.subPacketLength; i++) {
				this.subPackets.add(PacketParser.parsePacket(binaryData));
			}
		}
	}
	
	public long getValue() {
		LongStream s = this.subPackets.stream().mapToLong(Packet::getValue);
		
		if(super.getTypeId() == 0) {
			return s.sum();
			
		} else if(super.getTypeId() == 1) {
			return s.reduce((a, b) -> a * b).getAsLong();
			
		} else if(super.getTypeId() == 2) {
			return s.min().getAsLong();
			
		} else if(super.getTypeId() == 3) {
			return s.max().getAsLong();
			
		} else if(super.getTypeId() == 5) {
			return this.getSubPacket().get(0).getValue() > this.getSubPacket().get(1).getValue() ? 1 : 0;
			
		} else if(super.getTypeId() == 6) {
			return this.getSubPacket().get(0).getValue() < this.getSubPacket().get(1).getValue() ? 1 : 0;
			
		} else if(super.getTypeId() == 7) {
			return this.getSubPacket().get(0).getValue() == this.getSubPacket().get(1).getValue() ? 1 : 0;
			
		}
		
		throw new IllegalStateException("Unknown type ID "+ super.getTypeId());
	}

	private int getSubPacketNumberLength() {
		if(this.lengthTypeId == 0) {
			this.lengthType = "TOTAL_LENGTH";
			
			return 15;
		} else if(this.lengthTypeId == 1) {
			this.lengthType = "PACKET_NUMBER";
			
			return 11;
		}
		
		throw new IllegalStateException("Unknown length type ID "+ this.lengthTypeId);
	}
}
