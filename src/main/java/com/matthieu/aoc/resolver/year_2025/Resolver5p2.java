package com.matthieu.aoc.resolver.year_2025;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver5p2 extends Resolver5p1 {

	private Long freshIdsCount = 0l;
	private List<Duo<Long, Long>> filteredRanges;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		
		freshIdsCount = 0l;
		boolean merged = true;
		
		while(merged) {
			filteredRanges = new ArrayList<>();
			filteredRanges.add(ranges.remove(0));
		
			for (Duo<Long, Long> range : ranges) {
				merged = false;
				
				for(Duo<Long, Long> filteredRange: filteredRanges) {
					if(this.includedIn(range.a(), filteredRange)) {
						filteredRange.b(Math.max(range.b(), filteredRange.b()));
						merged = true;
					} else if(this.includedIn(range.b(), filteredRange)) {
						filteredRange.a(Math.min(range.a(), filteredRange.a()));
						merged = true;
					}
					
					if(merged) {
						break;
					}
					
				}
				
				if(!merged) {
					filteredRanges.add(range);
				}
			}
			
			ranges = filteredRanges;
		}
		
		filteredRanges = new ArrayList<>();
		filteredRanges.addAll(ranges);
		
		for (Duo<Long, Long> range : ranges) {
			for (Duo<Long, Long> rangeToTest : ranges) {
				if(range != rangeToTest) {
					if(includedIn(range.a(), rangeToTest) && includedIn(range.b(), rangeToTest)) {
						filteredRanges.remove(range);
						break;
					}
				}
			}
		}
	}
	
	@Override
	public boolean solve() throws Exception {
		
		for (Duo<Long, Long> range : filteredRanges) {
			freshIdsCount += range.b() - range.a() + 1;
		}
		
		return true;
	}
	
	@Override
	public String get() {
		return this.freshIdsCount + "";
	}
	
	private boolean includedIn(Long l, Duo<Long, Long> range) {
		return range.a() <= l && range.b() >= l;
	}
}
