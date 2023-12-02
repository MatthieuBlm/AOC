package com.matthieu.aoc.resolver.year_2023;

public class Resolver2p2 extends Resolver2p1 {

	@Override
	public String get() {
		return this.games.stream().mapToInt(game -> game.blue() * game.red() * game.green()).sum() + "";
	}
	
}
