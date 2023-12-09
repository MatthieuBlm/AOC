package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.service.ListUtils;

public class Resolver9p2 extends Resolver9p1 {

	@Override
	public boolean solve() throws Exception {
		feedHistory();
		this.histories.forEach(this::calcPreviousGap);
		return true;
	}
	
	@Override
	public String get() {
		return this.histories.stream()
				.mapToLong(history -> history.get(0).get(0))
				.sum() + "";
	}
	
	protected void calcPreviousGap(ArrayList<List<Long>> history) {
		ListUtils.getLast(history).add(0l);
		
		for (int i = history.size() - 1; i > 0; i--) {
			List<Long> gapToFeed = history.get(i - 1);
			gapToFeed.add(0, gapToFeed.get(0) - history.get(i).get(0));
		}
	}
}
