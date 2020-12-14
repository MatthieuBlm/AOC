package com.matthieu.aoc_2020.service.resolver.year_2020;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.matthieu.aoc_2020.exception.PrepareDataException;
import com.matthieu.aoc_2020.exception.SolveException;
import com.matthieu.aoc_2020.model.tuple.Tuple;
import com.matthieu.aoc_2020.service.resolver.Resolver;

public class Resolver13p2 implements Resolver {

	protected List<Tuple<Long, Long>> buses;
	private long earliestTimestamp;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		
		List<String> splittedBus = Arrays.asList(values.get(1).split(","));
		
		// [Key: Bus id, Value: Bus's line index] 
		this.buses = IntStream.range(0, splittedBus.size())
								.mapToObj(i -> splittedBus.get(i).equals("x") ? null : new Tuple<>(Long.parseLong(splittedBus.get(i)), Long.valueOf(i)))
								.filter(Objects::nonNull)
								.collect(Collectors.toList());
		
		this.earliestTimestamp = 0L;
	}

	@Override
	public boolean solve() throws SolveException {
		long M = this.buses.stream().map(b -> b.getKey()).reduce(1L, (a, b) -> a * b);
		long[] ai = this.buses.stream().mapToLong(b -> (b.getKey() - b.getValue()) % b.getKey()).toArray();
		long[] Mi = this.buses.stream().mapToLong(b -> M / b.getKey()).toArray();
		long[] yi = IntStream.range(0, Mi.length).mapToLong(i -> invertEuclide(Mi[i], this.buses.get(i).getKey())).toArray();
		
		for (int j = 0; j < yi.length; j++) {
			earliestTimestamp += ai[j] * Mi[j] * yi[j];
		}
		
		earliestTimestamp %= M;
		
		return true;
	}
	
	private long invertEuclide(long b, long n) {

		while (b < 0) {
			b = b + n;
		}
		
		long n0 = n;
		long b0 = b;
		long t0 = 0;
		long t = 1;
		long q = (long) Math.floor(n0 / b0);
		long r = n0 - q * b0;
		
		while (r > 0) {
			long temp = t0 - q * t;
			if (temp >= 0) {
				temp=temp % n;
			} else {
				temp = n - (-temp % n);
			}
			t0 = t;
			t = temp;
			n0 = b0;
			b0 = r;
			q = (long) Math.floor(n0 / b0);
			r = n0 - q * b0;
		}
		
		if (b0 != 1) {
			System.err.println(b + " n'a pas d'inverse modulo " + n); 
		}
		
		return t;
	}
	
	@Override
	public String get() {
		return String.valueOf(this.earliestTimestamp);
	}
}
