package com.matthieu.aoc.resolver.year_2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver2p1 implements Resolver {

	protected List<Duo<Long, Long>> ranges;
	protected List<Long> invalidIds;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		invalidIds = new ArrayList<>();
		ranges = Arrays.asList(values.get(0).split(",")).stream()
			.map(l -> l.split("-"))
			.map(d -> new Duo<>(Long.parseLong(d[0]), Long.parseLong(d[1])))
			.toList();
	}

	@Override
	public boolean solve() throws Exception {
		
		for (Duo<Long, Long> range : ranges) {
			System.out.println("Start analyzing "+ range);
			for (long i = range.a(); i <= range.b(); i++) {

				if(repeatTwice(i)) {
					invalidIds.add(i);
				}
				
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		return invalidIds.stream().mapToLong(Long::longValue).sum() + "";
	}
	
	protected boolean repeatTwice(long i) {
		if(i < 10) {
			return false;
		}
		
		String stringId = i + "";
		
		if(stringId.length() % 2 != 0) {
			return false;
		}
		
		return stringId.substring(0, stringId.length() / 2).equals(stringId.substring(stringId.length() / 2));
	}
	
}
