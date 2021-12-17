package com.matthieu.aoc.model.year_2021.bits;

import com.matthieu.aoc.model.year_2021.bits.impl.LengthTypeID;

public abstract class OperatorPacket extends Packet {

	private int subPacketLength;
	
	
	public OperatorPacket(StringBuilder binaryData) {
		super(binaryData);
		
		LengthTypeID lengthTypeId = LengthTypeID.valueOf(Integer.parseInt(this.consume(binaryData, 1)));
		
		this.subPacketLength = Integer.parseInt(this.consume(binaryData, lengthTypeId.getLength()), 2);
		
		switch(lengthTypeId) {
			case TOTAL_LENGTH:
			
				StringBuilder subPacketsData = new StringBuilder(this.consume(binaryData, this.subPacketLength));

				do {
					this.subPackets.add(PacketParser.parsePacket(subPacketsData));
				} while(subPacketsData.length() > 0);
			
				break;
				
			case PACKET_NUMBER:
			
				for (int i = 0; i < this.subPacketLength; i++) {
					this.subPackets.add(PacketParser.parsePacket(binaryData));
				}
				
				break;
			default:
				throw new IllegalArgumentException("Unknown length type ID "+ lengthTypeId);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("[").append(super.getVersion()).append(",").append(super.getTypeId()).append("] : ");
		builder.append("\n");
		
		for (Packet packet : subPackets) {
			builder.append("    ").append(packet.toString());
			builder.append("\n");
		}
		
		return builder.toString();
	}
}
