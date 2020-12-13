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
	private int greaterBusIndex;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		
		List<String> splittedBus = Arrays.asList(values.get(1).split(","));
		
		// [Key: Bus id, Value: Bus's line index] 
		this.buses = IntStream.range(0, splittedBus.size())
								.mapToObj(i -> splittedBus.get(i).equals("x") ? null : new Tuple<>(Long.parseLong(splittedBus.get(i)), Long.valueOf(i)))
								.filter(Objects::nonNull)
								.collect(Collectors.toList());
		
		this.earliestTimestamp = 0L;
		
		// Search for greater bus ID
		int greaterBusID = 0;
		for (int j = 0; j < buses.size(); j++) {
			if(buses.get(j).getKey() > greaterBusID) {
				greaterBusID = buses.get(j).getKey().intValue();
				this.greaterBusIndex = j;
			}
		}
	}

	@Override
	public boolean solve() throws SolveException {
		long [] currentTime = new long[buses.size()];
		long greaterBusId = buses.get(greaterBusIndex).getKey();
		
		while (earliestTimestamp == 0L) {
			
			for (int i = greaterBusIndex; i < currentTime.length; i++) {
				
				// While current time is lower/equals than previous time + bus's line index
				while(currentTime[i] < (currentTime[i - 1] + (buses.get(i).getValue() - buses.get(i - 1).getValue()))) {
					currentTime[i] += buses.get(i).getKey();
				}
			}
			
			boolean leftBusesOk = true;
			for (int i = greaterBusIndex; i < currentTime.length - 1; i++) {
				if(currentTime[i] != currentTime[i + 1] - (buses.get(i + 1).getValue() - buses.get(i).getValue())) {
					leftBusesOk = false;
					break;
				}
			}
			
			if(leftBusesOk) {
				// Setup 'right buses'
				for (int i = greaterBusIndex; i > 0; i--) {
					while(currentTime[i - 1] < (currentTime[i] - (buses.get(i).getValue() - buses.get(i - 1).getValue()))) {
						currentTime[i - 1] += buses.get(i - 1).getKey();
					}
				}
				
				boolean allBusesOk = true;
				for (int i = 0; i < currentTime.length - 1; i++) {
					if(currentTime[i] != currentTime[i + 1] - (buses.get(i + 1).getValue() - buses.get(i).getValue())) {
						allBusesOk = false;
						break;
					}
				}
				
				if(allBusesOk) {
					earliestTimestamp = currentTime[0];
				}
			}

			currentTime[greaterBusIndex] += greaterBusId;
			
			if(currentTime[this.greaterBusIndex] % 1000000000000L == 0L) {
				System.out.println(System.currentTimeMillis() + ": " + currentTime[this.greaterBusIndex]);
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.earliestTimestamp);
	}
}
