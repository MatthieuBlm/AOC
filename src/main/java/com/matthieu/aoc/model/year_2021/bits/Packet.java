package com.matthieu.aoc.model.year_2021.bits;

public class Packet {

	private int version;
	private int typeId;
	
	
	public Packet(StringBuilder binaryData) {
		this.version = Integer.parseInt(this.consume(binaryData, 3), 2);
		this.typeId = Integer.parseInt(this.consume(binaryData, 3), 2);
	}
	
	public String consume(StringBuilder binaryData, int n) {
		String data = binaryData.substring(0, n);
		
		binaryData.delete(0, n);
		
		return data;
		
	}
	
	protected int toMultiple(int i, int multiple) {
		int result = i;
		while(result % multiple != 0) {
			result++;
		}
		
		return result;
	}

	public int getVersion() {
		return version;
	}

	public int getTypeId() {
		return typeId;
	}
}
