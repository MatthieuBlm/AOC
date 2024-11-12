package com.matthieu.aoc.model;

public class Point3D extends Point {

    private int z;

    public Point3D() {
        super();
        this.z = 0;
    }

    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public void set(int x, int y, int z) {
        super.set(x, y);
        this.z = z;
    }

    public int z() {
        return this.getZ();
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Point3D [x=" + x() + ", y=" + y() + ", z=" + z() + "]";
    }
}
