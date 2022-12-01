package com.matthieu.aoc.model.year_2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class AmphipodsMap {

	private final char[] openspace;
	private final List<List<Character>> rooms;
	private final int roomSize;

	
	public AmphipodsMap(int roomSize, char[] openspace, List<List<Character>> rooms) {
		this.roomSize = roomSize;
		this.openspace = openspace;
		this.rooms = rooms;
	}

	public char[] getOpenspace() {
		return openspace;
	}
	
	public List<List<Character>> getRooms() {
		return this.rooms;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(openspace);
		result = prime * result + ((rooms == null) ? 0 : rooms.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AmphipodsMap other = (AmphipodsMap) obj;
		if (!Arrays.equals(openspace, other.openspace))
			return false;
		if (rooms == null) {
			if (other.rooms != null)
				return false;
		} else if (!rooms.equals(other.rooms))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("#############\n#");
		
		List<Character> roomA = new ArrayList<>();
		List<Character> roomB = new ArrayList<>();
		List<Character> roomC = new ArrayList<>();
		List<Character> roomD = new ArrayList<>();
		
		roomA.addAll(this.rooms.get(0));
		roomB.addAll(this.rooms.get(1));
		roomC.addAll(this.rooms.get(2));
		roomD.addAll(this.rooms.get(3));

		List<List<Character>> tmpRooms = Arrays.asList(roomA, roomB, roomC, roomD);
		
		for (List<Character> room : tmpRooms) {
			while(room.size() < this.roomSize)
				room.add(0, '.');
		}
		
		builder.append(this.openspace)
				.append("#\n")
				.append("###")
				.append(tmpRooms.get(0).get(0)).append('#').append(tmpRooms.get(1).get(0)).append('#').append(tmpRooms.get(2).get(0)).append('#').append(tmpRooms.get(3).get(0))
				.append("###\n");
		
		for (int ai = 1; ai < this.roomSize; ai++) {
			builder.append("  ");
			
			for (int ri = 0; ri < 4; ri++) {
				builder.append('#').append(tmpRooms.get(ri).get(ai));
			}
			
			builder.append("#  \n");
		}
		
		builder.append("  #########  \n");

		return builder.toString();
	}
}
