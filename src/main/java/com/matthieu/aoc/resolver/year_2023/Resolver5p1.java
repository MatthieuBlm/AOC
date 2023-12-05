package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.Extractor;

public class Resolver5p1 implements Resolver {
	
	protected List<Long> seeds;
	protected List<AlmanacMapping> mapping;
	protected long lowestLocation;

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.mapping = new ArrayList<>();
		this.seeds = Extractor.extractNumbers(values.remove(0));
		
		// Remove blank line
		values.remove(0);
		// Add blank line at the end
		values.add("");
		
		String line;
		
		while(!values.isEmpty()) {
			AlmanacMapping newMapping = new AlmanacMapping();
			
			while(!(line = values.remove(0)).equals("")) {
				List<Long> mapNumbers = Extractor.extractNumbers(line);
				
				if(!mapNumbers.isEmpty()) {
					newMapping.addMapping(mapNumbers);
				}
			}
			
			mapping.add(newMapping);
		}
	}

	@Override
	public boolean solve() throws Exception {
		
		this.lowestLocation = this.seeds.stream()
				.map(this::transformSeed)
				.sorted()
				.findFirst()
				.orElseThrow()
				.longValue();
		
		return true;
	}

	@Override
	public String get() {
		return lowestLocation + "";
	}
	
	protected Long transformSeed(Long seed) {
		Long updatedSeed = seed;
		
		for (AlmanacMapping almanacMapping : mapping) {
			updatedSeed = almanacMapping.transform(updatedSeed);
		}
		
		return updatedSeed;
	}

	
	protected class AlmanacMapping {
		private List<AlmanacTransform> mapping;
		
		public AlmanacMapping() {
			mapping = new ArrayList<>();
		}
		
		public void addMapping(List<Long> data) {
			long shift = data.get(0) - data.get(1);
			long lowerBound = data.get(1);
			long upperBound = data.get(1) + data.get(2) - 1;
			
			mapping.add(new AlmanacTransform(shift, lowerBound, upperBound));
		}
		
		public long transform(long n) {
			for (AlmanacTransform transform : this.mapping) {
				if(transform.lowerBound() <= n && transform.upperBound() >= n) {
					return n + transform.shift();
				}
			}
			
			return n;
		}
	}
	
	protected record AlmanacTransform(long shift, long lowerBound, long upperBound) {}
	
}
