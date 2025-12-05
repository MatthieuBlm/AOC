package com.matthieu.aoc.resolver.year_2025;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver5p1 implements Resolver {

	protected List<Duo<Long, Long>> ranges;
	protected List<Long> ids;
	protected List<Long> freshIds;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		ranges = new ArrayList<>();
		
		String line;
		
		while(!(line = values.remove(0)).equals("")) {
			String[] splitted = line.split("-");
			ranges.add(new Duo<>(Long.parseLong(splitted[0]), Long.parseLong(splitted[1])));
		}
		
		ids = values.stream().map(Long::parseLong).toList();
	}

	@Override
	public boolean solve() throws Exception {
		
		this.freshIds = this.ids.stream().filter(this::isFresh).toList();
		
		return true;
	}

	@Override
	public String get() {
		return this.freshIds.size() + "";
	}
	
	protected boolean isFresh(Long id) {
		return this.ranges.stream().anyMatch(range -> range.a() <= id && range.b() >= id);
	}

}
