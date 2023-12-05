package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.matthieu.aoc.exception.SolveException;

public class Resolver5p2 extends Resolver5p1 {

	@Override
	public boolean solve() throws SolveException {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Callable<Long>> tasks = new ArrayList<>(10);
		
		for (int i = 0; i < this.seeds.size() - 1; i += 2) {
			long seedBase = this.seeds.get(i);
			long range = this.seeds.get(i + 1);
			
			tasks.add(() -> findLowestLocation(seedBase, range));
		}

		try {
			List<Future<Long>> locations = executor.invokeAll(tasks);

			this.lowestLocation = locations.stream()
					.map(t -> {
							try {
								return t.get();
							} catch (InterruptedException | ExecutionException e) {
								e.printStackTrace();
							}
							return null;
						})
					.mapToLong(Long::longValue)
					.sorted()
					.findFirst()
					.orElseThrow();

			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private long findLowestLocation(long seedBase, long range) {
		System.out.println(String.format("Calc for seedBase %s and range %s starts ", seedBase, range));
		
		long lowest = Long.MAX_VALUE;
		
		for (long j = seedBase; j < seedBase + range - 1; j++) {
			lowest = Math.min(lowest, this.transformSeed(j)); 
		}
		
		return lowest;
	}
}
