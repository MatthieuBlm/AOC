package com.matthieu.aoc.resolver.year_2020;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver13p1 implements Resolver {

	protected long timestamp;
	protected List<Integer> buses;
	
	protected int earlyestBus;
	protected int earlyestTime;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.timestamp = Long.valueOf(values.get(0));
		this.buses = Arrays.asList(values.get(1).replace("x,", "").split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());

		this.earlyestBus = 0;
		this.earlyestTime = Integer.MAX_VALUE;
	}

	@Override
	public boolean solve() throws SolveException {
		for (Integer bus : buses) {
			int busTimestamp = 0;
			
			while(busTimestamp < this.timestamp) {
				busTimestamp += bus;
			}
			
			if(earlyestTime > busTimestamp) {
				earlyestBus = bus;
				earlyestTime = busTimestamp;
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.earlyestBus * (this.earlyestTime - this.timestamp));
	}

}
