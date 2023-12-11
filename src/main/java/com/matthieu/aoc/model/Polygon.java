package com.matthieu.aoc.model;

import java.util.ArrayList;
import java.util.List;

public class Polygon {

	private List<Point> points;
	
	public Polygon() {
		this.points = new ArrayList<>();
	}
	
	public void addPoint(int x, int y) {
		this.addPoint(new Point(x, y));
	}
	
	public void addPoint(Point p) {
		this.points.add(p);
	}
	
	public List<Point> getPoints() {
		return this.points;
	}
	
	public boolean isInside(int x, int y) {
		return this.isInside(new Point(x, y));
	}
	
	public boolean isInside(Point p) {
		final int INF = 10000;
		
        if (this.points.size() < 3) {
        	return false;
        }
 
        Point extreme = new Point(INF, p.getY());
 
        int count = 0, i = 0;
        do {
            int next = (i + 1) % this.points.size();
            if (doIntersect(points.get(i), points.get(next), p, extreme)) {
                if (orientation(points.get(i), p, points.get(next)) == 0) {
                	return onSegment(points.get(i), p, points.get(next));
                }
 
                count++;
            }
            i = next;
        } while (i != 0);
 
        return (count & 1) == 1;
	}
	
	private boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
		int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);
 
        if (o1 != o2 && o3 != o4)
            return true;
 
        if (o1 == 0 && onSegment(p1, p2, q1))
            return true;
 
        if (o2 == 0 && onSegment(p1, q2, q1))
            return true;
 
        if (o3 == 0 && onSegment(p2, p1, q2))
            return true;
 
        if (o4 == 0 && onSegment(p2, q1, q2))
            return true;
 
        return false;
	}
	
	private int orientation(Point p, Point q, Point r) {
		int val = (q.getY() - p.getY()) * (r.getX() - q.getX()) - (q.getX() - p.getX()) * (r.getY() - q.getY());
		 
        if (val == 0) {
        	return 0;
        }
        
        return (val > 0) ? 1 : 2;
	}
	
	private boolean onSegment(Point p, Point q, Point r) {
		return q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX())
                && q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY());
	}
	
	public static void main(String[] args) {
		Polygon p = new Polygon();
		
		p.addPoint(0, 0);
		p.addPoint(0, 10);
		p.addPoint(2, 10);
		p.addPoint(2, 2);
		p.addPoint(4, 2);
		p.addPoint(4, 10);
		p.addPoint(10, 10);
		p.addPoint(10, 0);
		
		System.out.println(p.isInside(1, 1));
		System.out.println(p.isInside(1, 9));
		System.out.println(p.isInside(3, 8));
	}
}