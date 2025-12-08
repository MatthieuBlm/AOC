package com.matthieu.aoc.model.year_2025;

import java.util.Objects;
import java.util.Set;

public class JunctionBox implements Comparable<JunctionBox> {

	private final int x;
	private final int y;
	private final int z;
	
	private Set<JunctionBox> circuit;

	public JunctionBox(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		circuit = null;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
	
	public Set<JunctionBox> getCircuit() {
		return circuit;
	}

	public void setCircuit(Set<JunctionBox> circuit) {
		this.circuit = circuit;
	}

	@Override
	public int compareTo(JunctionBox other) {
		return this.x - other.x + this.y - other.y + this.z - other.z;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JunctionBox other = (JunctionBox) obj;
		return x == other.x && y == other.y && z == other.z;
	}

	@Override
	public String toString() {
		return "JunctionBox [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
	
	
}
