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
	
	// Shoelace
	public long getArea() {
		long area = 0;
		
		for (int i = 0; i < points.size() - 1; i++) {
			area += ((long) points.get(i).x()) * points.get(i + 1).y() - ((long) points.get(i + 1).x()) * points.get(i).y();
		}
		
		return Math.abs(area / 2);
	}
	
	public double getPerimeter() {
		double perimeter = 0;
		
		for (int i = 0; i < points.size(); i++) {
			Point a = points.get(i);
			Point b = points.get((i + 1) % points.size()); // % to link last point with first one
			
			perimeter += Math.sqrt(Math.pow(((double) a.x()) - b.x(), 2) + Math.pow(((double) a.y()) - b.y(), 2));
		}
		
		return perimeter;
	}
	
	/**
	 * TODO May not work in every case (cf 2023, day 10 part 2)
	 */
	public boolean isInside(int x, int y) {
		return this.isInside(new Point(x, y));
	}
	
	/**
	 * TODO May not work in every case (cf 2023, day 10 part 2)
	 */
	public boolean isInside(Point p) {
		final int INF = 1_000_000;
		
        if (this.points.size() < 3) {
        	return false;
        }
 
        Point extreme = new Point(INF, p.y());
 
        int count = 0;
        int i = 0;
        
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
		int val = (q.y() - p.y()) * (r.x() - q.x()) - (q.x() - p.x()) * (r.y() - q.y());
		 
        if (val == 0) {
        	return 0;
        }
        
        return (val > 0) ? 1 : 2;
	}
	
	private boolean onSegment(Point p, Point q, Point r) {
		return q.x() <= Math.max(p.x(), r.x()) && q.x() >= Math.min(p.x(), r.x())
                && q.y() <= Math.max(p.y(), r.y()) && q.y() >= Math.min(p.y(), r.y());
	}
	
}
