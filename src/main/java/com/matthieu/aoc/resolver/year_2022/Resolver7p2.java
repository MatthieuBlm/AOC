package com.matthieu.aoc.resolver.year_2022;

import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.modelyear_2022.filesystem.Filex;

public class Resolver7p2 extends Resolver7p1 {

	@Override
	public String get() {
		long totalSpace = 70000000;
		long usedSpace = this.root.getSize();
		long remainingSpace = totalSpace - usedSpace;
		long spaceToFree = 30000000 - remainingSpace;
		
		
		List<Filex> candidate = this.files.stream()
										.filter(Filex::isDirectory)
										.filter(f -> f.getSize() >= spaceToFree)
										.sorted()
										.collect(Collectors.toList());
		
		
		return String.valueOf(candidate.get(0).getSize());
	}

}
