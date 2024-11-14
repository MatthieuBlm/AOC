package com.matthieu.aoc.model.year_2023;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.model.Point3D;

public class Brick {

    private List<Point3D> blocks;
    private String name;
    
    public Brick(Point3D originA, Point3D originB, String name) {
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
    
    public String getName() {
    	return name;
    }

    public int getLowerZ() {
        return blocks.stream().mapToInt(Point3D::getZ).min().getAsInt();
    }

    @Override
    public Brick clone() {
        Brick clone = new Brick(blocks.get(0).clone(), blocks.get(blocks.size() - 1).clone());
        clone.name = name;
        return clone;
    }

    @Override
    public String toString() {
        return "Brick [" + name + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((blocks == null) ? 0 : blocks.hashCode());
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
        Brick other = (Brick) obj;
        if (blocks == null) {
            if (other.blocks != null)
                return false;
        } else if (!blocks.equals(other.blocks))
            return false;
        return true;
    }

}
