package com.matthieu.aoc.resolver.year_2021;

import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.parser.Parser;

public class Resolver5p1 implements Resolver {

	protected List<Duo<Duo<Integer, Integer>, Duo<Integer, Integer>>> points;
	protected Matrix<Integer> matrix;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		Parser<Duo<Integer, Integer>> toPoint = s -> {
			String[] splited = s.split(",");
			return new Duo<Integer, Integer>(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]));
		};

		this.points = values.stream()
							.map(s -> s.split(" -> "))
							.map(coords -> new Duo<Duo<Integer, Integer>, Duo<Integer, Integer>>(toPoint.parse(coords[0]), toPoint.parse(coords[1])))
							.collect(Collectors.toList());
		
		this.matrix = new Matrix<>(1000, 1000, () -> 0);
	}

	@Override
	public boolean solve() throws SolveException {
		List<Duo<Duo<Integer, Integer>, Duo<Integer, Integer>>> lines = this.filterVerticalHorizontal();
		
		for (Duo<Duo<Integer, Integer>, Duo<Integer, Integer>> coords : lines) {
			this.trace(coords.a(), coords.b());
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.getOverlapAtLeast(2));
	}
	
	protected List<Duo<Duo<Integer, Integer>, Duo<Integer, Integer>>> filterVerticalHorizontal() {
		return this.points.stream()
							.filter(line -> line.a().a().equals(line.b().a()) || line.a().b().equals(line.b().b()))
							.collect(Collectors.toList());
	}
	
	protected void trace(Duo<Integer, Integer> from, Duo<Integer, Integer> to) {
		int xCoef = from.a() < to.a() ? 1 : (from.a() > to.a() ? -1 : 0);
		int yCoef = from.b() < to.b() ? 1 : (from.b() > to.b() ? -1 : 0);
		Duo<Integer, Integer> cursor = new Duo<>(from.a(), from.b());
		
		while(!cursor.equals(to)) {
			int x = cursor.a();
			int y = cursor.b();
			
			this.matrix.set(x, y, this.matrix.get(x, y) + 1);
			cursor.a(x + xCoef);
			cursor.b(y + yCoef);
		}
		
		this.matrix.set(to.a(), to.b(), this.matrix.get(to.a(), to.b()) + 1);
	}
	
	protected long getOverlapAtLeast(int min) {
		return this.matrix.stream().filter(i -> i >= min).count();
	}

}
