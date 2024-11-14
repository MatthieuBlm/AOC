package com.matthieu.aoc.resolver.year_2023;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.matrix.CharMatrix;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver23p1 implements Resolver {

    protected CharMatrix map;
    protected Matrix<Long> distances;
    protected Point start;
    protected Point destination;
    protected Map<Duo<Integer, Integer>, Character> crossableSlopes;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        this.map = new CharMatrix(values);
        this.distances = new Matrix<>(this.map.getWidth(), this.map.getHeight(), () -> 0l);
        
        this.start = new Point(1, 0);
        this.destination = new Point(this.map.getMaxX() - 1, this.map.getMaxY());
        
        this.distances.set(destination.x(), destination.y() - 1, 1l);

        this.crossableSlopes = new HashMap<>();
        this.crossableSlopes.put(new Duo<>(0, -1), 'v');
        this.crossableSlopes.put(new Duo<>(1, 0), '<');
        this.crossableSlopes.put(new Duo<>(0, 1), '^');
        this.crossableSlopes.put(new Duo<>(-1, 0), '>');
    }

    @Override
    public boolean solve() throws Exception {
    	calcMaxDistanceOfNeighbourgs(destination.x(), destination.y() - 1, destination.x(), destination.y());
        return true;
    }

    @Override
    public String get() {
    	return this.distances.get(1, 0) + "";
    }

    protected void calcMaxDistanceOfNeighbourgs(int x, int y, int fromX, int fromY) {
    	long distance = this.distances.get(x, y);
    	
    	
    	if(x == start.x() && y == start.y()) {
    		return;
    	}
    	
    	List<Duo<Integer, Integer>> neightbours = Matrix.getNeigthboursCrossCoords(x, y).stream()
    			.filter(coords -> !(coords.x() == fromX && coords.y() == fromY))
    			.toList();
    	
    	
    	for (Duo<Integer, Integer> neightbour : neightbours) {
    		char cell = map.get(neightbour.x(), neightbour.y());
			
    		if(cell == '.' || cell == this.crossableSlopes.get(new Duo<>(neightbour.x() - x, neightbour.y() - y))) {
    			this.distances.set(neightbour.x(), neightbour.y(), Math.max(this.distances.get(neightbour.x(), neightbour.y()), distance + 1));
    			calcMaxDistanceOfNeighbourgs(neightbour.x(), neightbour.y(), x, y);
    		}
		}
    	
    }
    
    protected void printMap() {
    	this.map.forEach((x, y, value) -> {
    		String toPrint = value == '#' ? "##" : pad(this.distances.get(x, y));
    		
    		if(x == this.map.getMaxX()) {
    			System.out.println(toPrint);
    		} else {
    			System.out.print(toPrint);
    		}
    	});
    }
    
    private static String pad(Long n) {
    	return String.format("%1$" + 2 + "s", n).replace(' ', '0');
    }

}
