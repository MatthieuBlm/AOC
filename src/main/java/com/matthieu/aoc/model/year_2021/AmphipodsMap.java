package com.matthieu.aoc.model.year_2021;

import java.util.List;

public final class AmphipodsMap {

	private final char[] openspace;
	private final List<List<Character>> rooms;

	
	public AmphipodsMap(char[] openspace, List<List<Character>> rooms) {
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
		// TODO
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO
		return true;
	}

	
	
	
	
}
