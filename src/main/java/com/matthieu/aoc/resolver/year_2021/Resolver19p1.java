package com.matthieu.aoc.resolver.year_2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.matrix.Row;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver19p1 implements Resolver {

	protected List<Matrix<Integer>> scanners;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.scanners = new ArrayList<>();
		List<String> scannerData = null;

		for (String line : values) {
			
			if(line.contains("---")) {
				scannerData = new ArrayList<>();
				
			} else if(line.isBlank()) {
				this.scanners.add(new Matrix<>(scannerData, Integer::parseInt, ","));

			} else {
				scannerData.add(line);
			}
		}
		
		this.scanners.add(new Matrix<>(scannerData, Integer::parseInt, ","));
	}

	@Override
	public boolean solve() throws SolveException {
		List<Map<Double, Duo<Row<Integer>, Row<Integer>>>> distances = new ArrayList<>(scanners.size());
		
		// Determine every distances
		for (Matrix<Integer> scanner : scanners) {
			List<Row<Integer>> rows = scanner.getRows();
			Map<Double, Duo<Row<Integer>, Row<Integer>>> scannerDistance = new HashMap<>();
			
			for (int y1 = 0; y1 < rows.size() - 1; y1++) {
				Row<Integer> a = scanner.getRow(y1);
				
				for (int y2 = y1 + 1; y2 < rows.size(); y2++) {
					Row<Integer> b = scanner.getRow(y2);
					
					scannerDistance.put(this.getDistance(a, b), new Duo<>(a, b));
				}
			}
			
			distances.add(scannerDistance);
		}


		Map<Integer, Set<Integer>> scannerWithCommonPoint = new HashMap<>();
		
		// Find common point
		for (int y1 = 0; y1 < distances.size() - 1; y1++) {
			for (int y2 = y1 + 1; y2 < distances.size(); y2++) {
				Set<Double> commonSquare = this.getCommon(distances.get(y1).keySet(), distances.get(y2).keySet());	// TODO need to be stored
				
				if(commonSquare.size() >= 66) {
					scannerWithCommonPoint.computeIfAbsent(y1, k -> new HashSet<>()).add(y2);
					scannerWithCommonPoint.computeIfAbsent(y2, k -> new HashSet<>()).add(y1);
					
					System.out.println(String.format("Scanner %s and scanner %s have %s squares in common", y1, y2, commonSquare.size()));
				}
			}
		}
		
		System.out.println(scannerWithCommonPoint);
		
		
		
		Matrix<Integer> scanner0 = this.scanners.get(0);
		Matrix<Integer> scannerToPair = this.scanners.get(scannerWithCommonPoint.get(0).iterator().next());
		
		
		
		return true;
	}

	@Override
	public String get() {
		return null;
	}
	
	public double getDistance(Row<Integer> a, Row<Integer> b) {
		return  Math.sqrt(
					Math.pow(b.get(0) - a.get(0), 2) + 
					Math.pow(b.get(1) - a.get(1), 2) + 
					Math.pow(b.get(2) - a.get(2), 2));
	}
	
	protected Set<Double> getCommon(Set<Double> a, Set<Double> b) {
		Set<Double> result = new HashSet<>();
		
		for (Double d : a) {
			if(b.contains(d))
				result.add(d);
		}
		
		return result;
	}
	
	public void rotateX(Matrix<Integer> scanner) {
		for (Row<Integer> row : scanner.getRows()) {
			int y = row.get(1);
			int z = row.get(2);
			
			row.set(1, -1 * z);
			row.set(2, y);
		}
	}
	
	public void rotateY(Matrix<Integer> scanner) {
		for (Row<Integer> row : scanner.getRows()) {
			int x = row.get(0);
			int z = row.get(2);
			
			row.set(0, -1 * z);
			row.set(2, x);
		}
	}
	
	public void rotateZ(Matrix<Integer> scanner) {
		for (Row<Integer> row : scanner.getRows()) {
			int x = row.get(0);
			int y = row.get(1);
			
			row.set(0, -1 * y);
			row.set(1, x);
		}
	}
}
