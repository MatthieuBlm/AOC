package com.matthieu.aoc_2020.service.resolver.year_2020;

import java.util.List;
import java.util.TreeMap;

import com.matthieu.aoc_2020.exception.PrepareDataException;
import com.matthieu.aoc_2020.exception.SolveException;
import com.matthieu.aoc_2020.model.tuple.Tuple;
import com.matthieu.aoc_2020.service.resolver.Resolver;

public class Resolver5p1 implements Resolver {

	protected TreeMap<Integer, String> seats;
	private List<String> datas;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.datas = values;
		this.seats = new TreeMap<>();
	}

	@Override
	public boolean solve() throws SolveException {
		
		for (String seat : datas) {
			Tuple<Integer, Integer> row = new Tuple<>(0, 127);
			
			for (int i = 0; i < 7; i++) {
				row = this.getPart(seat.charAt(i), row);
			}
			
			Tuple<Integer, Integer> column = new Tuple<>(0, 8);
			for (int i = 7; i < 10; i++) {
				column = this.getPart(seat.charAt(i), column);
			}
			
			this.seats.put(row.getKey() * 8 + column.getKey(), seat);
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.seats.descendingMap().entrySet().iterator().next().getKey());
	}

	protected Tuple<Integer, Integer> getPart(char letter, Tuple<Integer, Integer> intervalle) {
		Tuple<Integer, Integer> res;
		
		int range = intervalle.getValue() - intervalle.getKey();
		int increment = (int) Math.ceil(range / 2d);
		
		if(letter == 'F' || letter == 'L') {
			res = new Tuple<>(intervalle.getKey(), intervalle.getValue() - increment);
		} else if(letter == 'B' || letter == 'R') {
			res = new Tuple<>(intervalle.getKey() + increment, intervalle.getValue());
		} else {
			throw new IllegalArgumentException("Unknown letter " + letter);
		}
		
		return res;
	}
	
}
