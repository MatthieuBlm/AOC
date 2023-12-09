package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.Extractor;
import com.matthieu.aoc.service.ListUtils;

public class Resolver9p1 implements Resolver {
	
	protected List<ArrayList<List<Long>>> histories;

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.histories = values.stream()
				.map(Extractor::extractNumbers)
				.map(baseHistory -> new ArrayList<>(Arrays.asList(baseHistory)))
				.toList();
	}

	@Override
	public boolean solve() throws Exception {
		feedHistory();
		this.histories.forEach(this::calcNextGap);
		return true;
	}

	@Override
	public String get() {
		return this.histories.stream()
				.mapToLong(history -> ListUtils.getLast(history.get(0)))
				.sum() + "";
	}
	
	protected void feedHistory() {
		for (ArrayList<List<Long>> history : histories) {
			List<Long> last;
					
			while(!(last = ListUtils.getLast(history)).stream().allMatch(n -> n == 0l)) {
				List<Long> gaps = new ArrayList<>();
				
				for (int i = 0; i < last.size() - 1; i++) {
					gaps.add(last.get(i + 1) - last.get(i));
				}
				
				history.add(gaps);
			}
		}
	}

	protected void printHistories() {
		for (ArrayList<List<Long>> history : histories) {
			for (List<Long> gaps : history) {
				System.out.println(gaps.stream().map(l -> l.toString()).collect(Collectors.joining(" ")));
			}
		}
	}
	
	protected void calcNextGap(ArrayList<List<Long>> history) {
		ListUtils.getLast(history).add(0l);
		
		for (int i = history.size() - 1; i > 0; i--) {
			List<Long> gapToFeed = history.get(i - 1);
			gapToFeed.add(ListUtils.getLast(gapToFeed) + ListUtils.getLast(history.get(i)));
		}
	}
}
