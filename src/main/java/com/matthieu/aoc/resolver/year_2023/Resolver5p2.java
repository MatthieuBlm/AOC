package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Resolver5p2 extends Resolver5p1 {

	@Override
	public boolean solve() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Callable<Long>> tasks = new ArrayList<>(10);
		
		for (int i = 0; i < this.seeds.size() - 1; i += 2) {
			final long seedBase = this.seeds.get(i);
			final long range = this.seeds.get(i + 1);
			
			tasks.add(() -> findLowestLocation(seedBase, range));
		}

		List<Future<Long>> locations = executor.invokeAll(tasks);

		this.lowestLocation = locations.stream()
				.map(t -> { try { return t.get();} catch (InterruptedException | ExecutionException e) { return null; }})
				.mapToLong(Long::longValue)
				.sorted()
				.findFirst()
				.orElseThrow();

		return true;
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
