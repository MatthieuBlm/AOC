package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.matrix.Cell;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver11p1 implements Resolver {

	protected Matrix<Character> space;
	protected Matrix<Character> enlargedSpace;
	protected List<Cell<Character>> galaxies;
	protected List<Long> distances;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.space = new Matrix<>(values, s -> s.charAt(0));
		this.enlargedSpace = new Matrix<>(values, s -> s.charAt(0));
		this.distances = new ArrayList<>();
	}

	@Override
	public boolean solve() throws Exception {
		this.expandOnce();
		this.galaxies = enlargedSpace.cellStream().filter(cell -> cell.value() == '#').toList();

		Set<Duo<Cell<Character>, Cell<Character>>> paires = this.makePaires(this.galaxies);
		
		this.distances = paires.stream().map(pair -> this.shortestDistance(pair.a(), pair.b())).toList();
		
		return true;
	}

	@Override
	public String get() {
		return this.distances.stream().mapToLong(Long::longValue).sum() + "";
	}

	protected void expandOnce() {
		int enlargedCount = 0;
		
		for (int y = space.getHeight() - 1; y >= 0; y--) {
			if(space.getRow(y).stream().allMatch(c -> c == '.')) {
				enlargedSpace.insertRow(y, IntStream.range(0, space.getWidth()).mapToObj(i -> '.').toList());
				enlargedCount++;
			}
		}

		for (int x = space.getWidth() - 1; x >= 0; x--) {
			if(space.getColumn(x).stream().allMatch(c -> c == '.')) {
				enlargedSpace.insertColumn(x, IntStream.range(0, space.getHeight() + enlargedCount).mapToObj(i -> '.').toList());
			}
		}
	}
	
	protected Set<Duo<Cell<Character>, Cell<Character>>> makePaires(List<Cell<Character>> galaxies) {
		Set<Duo<Cell<Character>, Cell<Character>>> pairs = new HashSet<>();
		
		for (int i = 0; i < galaxies.size(); i++) {
			for (int j = 0; j < galaxies.size(); j++) {
				if(i != j) {
					Cell<Character> a;
					Cell<Character> b;
					
					// Ordered to make HashSet guarantee unique pairs of Cell
					if(galaxies.get(i).x() < galaxies.get(j).x()) {
						a = galaxies.get(i);
						b = galaxies.get(j);
					} else if(galaxies.get(i).x() > galaxies.get(j).x()) {
						a = galaxies.get(j);
						b = galaxies.get(i);
					} else {
						if(galaxies.get(i).y() < galaxies.get(j).y()) {
							a = galaxies.get(i);
							b = galaxies.get(j);
						} else {
							a = galaxies.get(j);
							b = galaxies.get(i);
						}
					}
					
					pairs.add(new Duo<>(a, b));
				}
			}
		}
		
		return pairs;
	}
	
	protected long shortestDistance(Cell<Character> a, Cell<Character> b) {
		return Math.max(a.x(), b.x()) - Math.min(a.x(), b.x()) + Math.max(a.y(), b.y()) - Math.min(a.y(), b.y());
	}
}
