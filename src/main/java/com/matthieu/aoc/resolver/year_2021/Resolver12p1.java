package com.matthieu.aoc.resolver.year_2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver12p1 implements Resolver{

	protected Map<String, List<String>> map;
	protected List<List<String>> paths;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.paths = new ArrayList<>();
		this.map = new HashMap<>();
		
		for (String line : values) {
			String [] segment = line.split("-");
			
			this.addSegment(segment[0], segment[1]);
			this.addSegment(segment[1], segment[0]);
		}
	}

	@Override
	public boolean solve() throws SolveException {
		List<String> currentPath = new ArrayList<>();
		this.paths.add(currentPath);
		
		currentPath.add("start");
		
		this.visit("start", currentPath);
		
		return true;
	}

	@Override
	public String get() {
//		paths.stream()
//				.map(p -> p.stream().collect(Collectors.joining(",")))
//				.sorted()
//				.forEach(System.out::println);
		
		return String.valueOf(this.paths.size());
	}
	
	protected void addSegment(String from, String to) {
		if(from.equals("end") || to.equals("start")) {
			return;
		}
		
		List<String> destinations = this.map.computeIfAbsent(from, k ->  new ArrayList<>());
		
		destinations.add(to);
	}
	
	protected void visit(String from, List<String> currentPath) {
		List<String> options = this.map.get(from);
		
		if(from.equals("end")) {
			return;
			
		} else if(options.size() == 1) {
		
			this.addThisCave(options.get(0), currentPath);

		} else {
			this.paths.remove(currentPath);
			
			for (String option : options) {
				List<String> pathCopy = new ArrayList<>(currentPath);
				
				this.paths.add(pathCopy);
				
				this.addThisCave(option, pathCopy);
			}
		}
	}
	
	protected void addThisCave(String cave, List<String> path) {
		if((this.isSmallCave(cave) && path.stream().anyMatch(s -> s.equals(cave)))) {
			if(!path.get(path.size() - 1).equals("end")) {
				this.paths.remove(path);
			}
			return;
		}

		path.add(cave);
		
		this.visit(cave, path);
	}
	
	protected boolean isSmallCave(String cave) {
		return cave.equals(cave.toLowerCase());
	}
}
