package com.matthieu.aoc.model.year_2020;

import com.matthieu.aoc.model.Direction;

public class Ship {

	protected int x;
	protected int y;
	protected Direction orientation;
	
	public Ship(Direction orientation) {
		this.orientation = orientation;
		this.x = 0;
		this.y = 0;
	}
	
	public void moveForward(int n) {
		this.move(this.orientation, n);
	}
	
	public void move(Direction direction, int n) {
		switch(direction) {
		case NORTH:
			this.x -= n;
			break;
		case SOUTH:
			this.x += n;
			break;
		case EAST:
			this.y += n;
			break;
		case WEST:
			this.y -= n;
			break;
		default:
			throw new IllegalArgumentException();
		}
	}
	

	public void turnLeft(int degrees) {
		this.orientation = Direction.fromDegree(this.orientation.getDegree() - degrees);
	}

	public void turnRight(int degrees) {
		this.orientation = Direction.fromDegree(this.orientation.getDegree() + degrees);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Direction getOrientation() {
		return orientation;
	}

	public void setOrientation(Direction orientation) {
		this.orientation = orientation;
	}
	
	@Override
	public String toString() {
		return "Ship [x=" + x + ", y=" + y + ", orientation=" + orientation + "]";
	}

}
