package com.matthieu.aoc.resolver.year_2020;

import java.util.List;
import java.util.TreeMap;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;

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
			Duo<Integer, Integer> row = new Duo<>(0, 127);
			
			for (int i = 0; i < 7; i++) {
				row = this.getPart(seat.charAt(i), row);
			}
			
			Duo<Integer, Integer> column = new Duo<>(0, 8);
			for (int i = 7; i < 10; i++) {
				column = this.getPart(seat.charAt(i), column);
			}
			
			this.seats.put(row.a() * 8 + column.a(), seat);
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.seats.descendingMap().entrySet().iterator().next().getKey());
	}

	protected Duo<Integer, Integer> getPart(char letter, Duo<Integer, Integer> intervalle) {
		Duo<Integer, Integer> res;
		
		int range = intervalle.b() - intervalle.a();
		int increment = (int) Math.ceil(range / 2d);
		
		if(letter == 'F' || letter == 'L') {
			res = new Duo<>(intervalle.a(), intervalle.b() - increment);
		} else if(letter == 'B' || letter == 'R') {
			res = new Duo<>(intervalle.a() + increment, intervalle.b());
		} else {
			throw new IllegalArgumentException("Unknown letter " + letter);
		}
		
		return res;
	}
	
}
