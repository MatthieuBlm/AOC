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
    public Point3D clone() {
        return new Point3D(x(), y(), z());
    }

    @Override
    public String toString() {
        return "Point3D [x=" + x() + ", y=" + y() + ", z=" + z() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + z;
        result = prime * result + x();
        result = prime * result + y();
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
        Point3D other = (Point3D) obj;
        if (z != other.z)
            return false;
        if (x() != other.x())
            return false;
        if (y() != other.y())
            return false;
        return true;
    }
}
