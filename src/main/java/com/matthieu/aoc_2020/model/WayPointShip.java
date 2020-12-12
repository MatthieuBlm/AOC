package com.matthieu.aoc_2020.model;

public class WayPointShip extends Ship {

	private int wayPointX;
	private int wayPointY;
	
	public WayPointShip(Direction orientation) {
		super(orientation);
		
		this.wayPointX = 10;
		this.wayPointY = -1;
	}

	@Override
	public void moveForward(int n) {
		for (int i = 0; i < n; i++) {
			this.x += this.wayPointX;
			this.y += this.wayPointY;
		}
	}

	@Override
	public void move(Direction direction, int n) {
		switch(direction) {
		case NORTH:
			this.wayPointY -= n;
			break;
		case SOUTH:
			this.wayPointY += n;
			break;
		case EAST:
			this.wayPointX += n;
			break;
		case WEST:
			this.wayPointX -= n;
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void turnLeft(int degrees) {
		this.turnWayPoint(-degrees * (Math.PI/180));
	}

	@Override
	public void turnRight(int degrees) {
		this.turnWayPoint(degrees * (Math.PI/180));
	}

	private void turnWayPoint(double angle) {
		long rotatedX = Math.round(Math.cos(angle) * this.wayPointX - Math.sin(angle) * wayPointY);
		long rotatedY = Math.round(Math.sin(angle) * this.wayPointX + Math.cos(angle) * wayPointY);
		
		this.wayPointX = (int) rotatedX;
		this.wayPointY = (int) rotatedY;
	}
	
	@Override
	public String toString() {
		return "WayPointShip [wayPointX=" + wayPointX + ", wayPointY=" + wayPointY + ", x=" + x + ", y=" + y + "]";
	}

}
