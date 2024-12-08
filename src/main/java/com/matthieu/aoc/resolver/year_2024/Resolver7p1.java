package com.matthieu.aoc.resolver.year_2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver7p1 implements Resolver {

	protected List<Duo<Long, List<Long>>> equations;
	protected long sum;
	
	protected enum Operator {
		ADD, MULT, CONCAT;
	}
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		equations = values.stream().map(v -> {
			String[] splitedEquation = v.split("\\: ");
			
			long n = Long.parseLong(splitedEquation[0]);
			List<Long> operands = Arrays.asList(splitedEquation[1].split(" ")).stream().map(Long::parseLong).toList();
			
			return new Duo<>(n, operands);
		}).toList();
	}

	@Override
	public boolean solve() throws Exception {
		
		this.sum = equations.stream().mapToLong(equation -> {
			List<Operator> operators = newOperators(equation.b().size() - 1);
			boolean canBeTrue = false;
			long n;
			
			while(true) {
				n = calc(equation.b(), operators);
				
				if(n == equation.a()) {
					canBeTrue = true;
					break;
				} else if((operators = getNextOperatorCombination(operators)) == null) {
					break;
				}
			}
			
			if(canBeTrue) {
				return n;
			}
			
			return 0;
		}).sum();
		
		return true;
	}

	@Override
	public String get() {
		return sum + "";
	}
	
	protected long calc(List<Long> numbers, List<Operator> operators) {
		if(numbers.size() - 1 != operators.size()) {
			throw new IllegalStateException(numbers.size() + " " + operators.size());
		}
		
		List<Operator> ops = new ArrayList<>(operators);
		
		return numbers.stream().reduce((acc, n) -> calc(acc, n, ops.remove(0))).orElseThrow();
	}
	

	protected long calc(long a, long b, Operator operator) {
		return operator == Operator.ADD ? a + b : a * b;
	}
	
	protected List<Operator> getNextOperatorCombination(List<Operator> operators) {
		List<Operator> next = new ArrayList<>(operators);
		
		if(operators.stream().allMatch(o -> o == Operator.MULT)) {
			return null;
		}

		for (int i = 0; i < operators.size(); i++) {
			if(operators.get(i) == Operator.ADD) {
				next.set(i, Operator.MULT);
				break;
			} else {
				next.set(i, Operator.ADD);
			}
		}
		
		return next;
	}
	
	protected List<Operator> newOperators(int size) {
		return IntStream.range(0, size).mapToObj(i -> Operator.ADD).collect(Collectors.toCollection(ArrayList::new));
	}
	
}
