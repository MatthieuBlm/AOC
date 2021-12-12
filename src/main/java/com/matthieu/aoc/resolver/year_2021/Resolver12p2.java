package com.matthieu.aoc.resolver.year_2021;

import java.util.List;

public class Resolver12p2 extends Resolver12p1 {

	@Override
	protected void addThisCave(String cave, List<String> path) {
		if((this.isSmallCave(cave) && path.stream().filter(s -> s.equals(cave)).count() >= 2)) {
			if(!path.get(path.size() - 1).equals("end")) {
				this.paths.remove(path);
			}
			
			return;
		}

		path.add(cave);
		
		this.visit(cave, path);
	}
	
	@Override
	public String get() {
//		paths.stream()
//				.map(p -> p.stream().collect(Collectors.joining(",")))
//				.sorted()
//				.forEach(System.out::println);
		
		return String.valueOf(this.paths.size());
	}
	
	@Override
	protected void visit(String from, List<String> currentPath) {
		if(this.haveTooSmallCave(currentPath)) {
			this.paths.remove(currentPath);
			return;
		}
		
		super.visit(from, currentPath);
	}
	
	protected boolean haveTooSmallCave(List<String> path) {
		
		return path.stream()
					.filter(cave -> cave.equals(cave.toLowerCase()))
					.map(caveA -> path.stream().filter(caveA::equals)
					.count())
					.filter(l -> l >= 2)
					.count() > 2;
	}
	
}
