package com.matthieu.aoc.model;

public enum Direction {
	NORTH(0, new Point(0, -1)),
	EAST(90, new Point(1, 0)),
	SOUTH(180, new Point(0, 1)),
	WEST(270, new Point(-1, 0));
	
	private int degree;
	private Point vector;
	
	private Direction(int degree, Point vector) {
		this.degree = degree;
		this.vector = vector;
	}
	
	public static Direction fromDegree(int degree) {
		degree = degree % 360;
		degree = degree % -360;
		
		if(degree == 0) {
			return Direction.NORTH;
		} else if(degree == 90 || degree == -270) {
			return Direction.EAST;
		} else if(degree == 180 || degree == -180) {
			return Direction.SOUTH;
		} else if(degree == 270 || degree == -90) {
			return Direction.WEST;
		}

        throw new IllegalArgumentException("Illegal given degree " + degree);
    }

    public static Direction fromVector(int x, int y) {
        if (x == 0 && y < 0) {
	        return Direction.NORTH;
        } else if (x > 0 && y == 0) {
	        return Direction.EAST;
        } else if (x == 0 && y > 0) {
	        return Direction.SOUTH;
        } else if (x < 0 && y == 0) {
	        return Direction.WEST;
	    }
	    
        throw new IllegalArgumentException("Illegal given vector " + x + " " + y);
	}
    
    public static Direction fromCharacter(char c) {
    	if(c == '>') {
    		return Direction.EAST;
    	} else if(c == 'v') {
    		return Direction.SOUTH;
    	} else if(c == '<') {
    		return Direction.WEST;
    	} else if(c == '^') {
    		return Direction.NORTH;
    	}
    	
    	throw new IllegalArgumentException("Illegal given character " + c);
    }
	
    public static Direction fromVector(Point vector) {
        return fromVector(vector.x(), vector.y());
	}
	
	public int getDegree() {
		return this.degree;
	}
	
	public Point getVector() {
		return this.vector;
	}
}
