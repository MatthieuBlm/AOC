package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver23p2 extends Resolver23p1 {

	private List<Matrix<Long>> allDistances;
	
	 @Override
	 public void prepareData(List<String> values) throws PrepareDataException {
		 super.prepareData(values);
		 this.allDistances = new ArrayList<>();
	 }
	 
	@Override
	public boolean solve() throws Exception {
		calcMaxDistanceOfNeighbourgs(destination.x(), destination.y() - 1, destination.x(), destination.y(), super.distances);
	    return true;
	}
	
	protected void calcMaxDistanceOfNeighbourgs(int x, int y, int fromX, int fromY, Matrix<Long> distances) {
    	long distance = distances.get(x, y);
    	
    	
    	if(x == start.x() && y == start.y()) {
    		return;
    	}
    	
    	List<Duo<Integer, Integer>> neightbours = Matrix.getNeigthboursCrossCoords(x, y).stream()
    			.filter(coords -> !(coords.x() == fromX && coords.y() == fromY))
    			.toList();
    	
    	
    	for (Duo<Integer, Integer> neightbour : neightbours) {
    		char cell = map.get(neightbour.x(), neightbour.y());
    		
    		Matrix<Long> distanceToUse;
    		
    		if(neightbours.size() == 1) {
    			distanceToUse = distances;
    		} else {
    			distanceToUse = clone(distances);
    			allDistances.remove(distances);
    			allDistances.add(distanceToUse);
    		}
			
    		Long neightbourDistance = distanceToUse.get(neightbour.x(), neightbour.y());
    		
    		if(cell != '#' && neightbourDistance == 0) {
    			distanceToUse.set(neightbour.x(), neightbour.y(), Math.max(neightbourDistance, distance + 1));
    			this.calcMaxDistanceOfNeighbourgs(neightbour.x(), neightbour.y(), x, y, distanceToUse);
    		}
		}
    	
    }
	
    @Override
    public String get() {
    	return this.allDistances.stream().mapToLong(d -> d.get(1, 0)).max().getAsLong() + "";
    }
	
	private Matrix<Long> clone(Matrix<Long> distances) {
		return new Matrix<>(distances.getWidth(), distances.getHeight(), distances::get);
	}
}
