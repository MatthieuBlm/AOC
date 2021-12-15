package com.matthieu.aoc.resolver.year_2021;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver15p1 implements Resolver {

	protected Matrix<Long> risks;
	protected long minRisk;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.risks = new Matrix<>(values, Long::parseLong);
	}

	@Override
	public boolean solve() throws SolveException {
		
		this.minRisk = this.calcMinRisk(risks, this.risks.getMaxX(), this.risks.getMaxY());
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.minRisk);
	}

	protected long calcMinRisk(Matrix<Long> risk, int m, int n) {
        Matrix<Long> temporaryCost = new Matrix<>(m + 1, n + 1, () -> 0l);
 
        temporaryCost.set(0, 0, risk.get(0, 0));
        
        // Initialize first column of total cost(temporaryCost) array
        for (int i = 1; i <= m; i++)
            temporaryCost.set(i, 0, temporaryCost.get(i-1, 0) + risk.get(i, 0));

        // Initialize first row of temporaryCost array
        for (int j = 1; j <= n; j++)
            temporaryCost.set(0, j, temporaryCost.get(0, j-1) + risk.get(0, j));
 
        // Construct rest of the temporaryCost array
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                temporaryCost.set(i, j, Math.min(temporaryCost.get(i-1, j), temporaryCost.get(i, j-1)) + risk.get(i, j));
            }
        }
        
        return temporaryCost.get(m, n) - risk.get(0, 0);
	}
	
}
