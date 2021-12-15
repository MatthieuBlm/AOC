package com.matthieu.aoc.model.graph;

import com.matthieu.aoc.model.matrix.Matrix;

public class DPSolver {

	private DPSolver() {}
	
	public static long calculateMinimumPath(Matrix<Long> cost) {
		int width = cost.getWidth();
		int height = cost.getHeight();
		
        Matrix<Long> temporaryCost = new Matrix<>(width, height, () -> 0l);
		
        temporaryCost.set(0, 0, cost.get(0, 0));
		
        // Initialize first
        for (int x = 1; x < width; x++) {
        	temporaryCost.set(x, 0, temporaryCost.get(x - 1, 0) + cost.get(x, 0));
        }
        
        // Initialize first row
        for (int y = 1; y < height; y++) {
        	temporaryCost.set(0, y, temporaryCost.get(0, y - 1) + cost.get(0, y));
        }
        
        // Construct rest of the temporaryCost array
        for (int y = 1; y < height; y++) {
        	for (int x = 1; x < width; x++) {
                temporaryCost.set(x, y, Math.min(temporaryCost.get(x-1, y), temporaryCost.get(x, y-1)) + cost.get(x, y));
            }
        }
        
		return temporaryCost.get(width - 1, height - 1);
	}
}
