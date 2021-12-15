package com.matthieu.aoc.resolver.year_2021;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.matrix.Matrix;

public class Resolver15p2 extends Resolver15p1 {

	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		Matrix<Long> r = new Matrix<>(values, Long::parseLong);
		
		this.risks = new Matrix<>(r.getXSize() * 5, r.getYSize() * 5, () -> 0l);
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				for (int y = 0; y < r.getYSize(); y++) {
					for (int x = 0; x < r.getXSize(); x++) {
						long newRisk = r.get(x, y) + i + j;
						
						newRisk = newRisk % 10 + Double.valueOf(Math.floor(newRisk / 10)).longValue();
						
						risks.set(x + j * r.getXSize(), y + i * r.getYSize(), newRisk);

					}
				}
			}
		}
	}
}
