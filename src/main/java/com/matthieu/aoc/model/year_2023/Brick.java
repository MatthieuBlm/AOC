package com.matthieu.aoc.model.year_2023;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.model.Point3D;

public class Brick {

    private List<Point3D> blocks;
    private char name;
    
    public Brick(Point3D originA, Point3D originB, char name) {
    	this(originA, originB);
    	this.name = name;
    }

    public Brick(Point3D originA, Point3D originB) {
        blocks = new ArrayList<>();

        for (int x = originA.getX(); x <= originB.getX(); x++) {
            for (int y = originA.getY(); y <= originB.getY(); y++) {
                for (int z = originA.getZ(); z <= originB.getZ(); z++) {
                    blocks.add(new Point3D(x, y, z));
                }
            }
        }
    }

    public List<Point3D> getBlocks() {
        return blocks;
    }
    
    public char getName() {
    	return name;
    }

}
