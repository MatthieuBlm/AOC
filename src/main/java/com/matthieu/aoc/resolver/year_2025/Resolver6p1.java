package com.matthieu.aoc.resolver.year_2025;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver6p1 implements Resolver {

	protected Matrix<String> problems;
	protected List<Long> results;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		values = values.stream()
				.map(line -> line.trim().replaceAll(" +", " "))
				.toList();
		
		this.problems = new Matrix<>(values, s -> s);
	}

	@Override
	public boolean solve() throws Exception {
		
		this.results = problems.getColumns().stream().map(column -> {
			List<Long> operands = column.stream()
					.filter(o -> !o.equals("*") && !o.equals("+"))
					.map(Long::parseLong)
					.toList();
			
			return makeOperation(operands, column.get(column.size() - 1));
			
		}).toList();
		
		return true;
	}

	@Override
	public String get() {
		return results.stream().mapToLong(Long::longValue).sum() + "";
	}
	
	protected Long makeOperation(List<Long> operands, String operator) {
		if(operator.equals("*")) {
			return operands.stream().mapToLong(Long::longValue).reduce(1, (a, b) -> a * b);
		} else {
			return operands.stream().mapToLong(Long::longValue).sum();
		}
	}
	
}
