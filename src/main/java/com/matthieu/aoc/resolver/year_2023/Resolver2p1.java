package com.matthieu.aoc.resolver.year_2023;

import java.util.List;
import java.util.stream.Stream;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver2p1 implements Resolver {
	
	protected List<Game> games;
	protected List<Game> possibleGame;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		games = values.stream().map(this::parse).toList();
	}

	@Override
	public boolean solve() throws SolveException {
		this.possibleGame = games.stream().filter(game -> game.red <= 12 && game.green <= 13 && game.blue <= 14).toList();
		return true;
	}

	@Override
	public String get() {
		return this.possibleGame.stream().mapToInt(Game::id).sum() + "";
	}
	
	protected Game parse(String line) {
		String[] splitedLine = line.split(":");
		
		int gameId = Integer.parseInt(splitedLine[0].replace("Game ", ""));
		int red = 0;
		int green = 0;
		int blue = 0;
		
		String[] handfuls = splitedLine[1].split(";");
		
		List<String[]> cubes = Stream.of(handfuls).map(h -> h.split(",")).flatMap(Stream::of).map(String::trim).map(h -> h.split(" ")).toList();
		
		for (String[] cube : cubes) {
			if(cube[1].equals("red")) {
				red = Math.max(red, Integer.parseInt(cube[0]));
				
			} else if(cube[1].equals("green")) {
				green = Math.max(green, Integer.parseInt(cube[0]));
				
			} else if(cube[1].equals("blue")) {
				blue = Math.max(blue, Integer.parseInt(cube[0]));
			}
		}
		
		return new Game(gameId, red, green, blue);
	}
	
	protected class Game {
		private int id;
		private int red;
		private int green;
		private int blue;
		
		protected Game(int id, int red, int green, int blue) {
			this.id = id;
			this.red = red;
			this.green = green;
			this.blue = blue;
		}
		
		public int id() {
			return this.id;
		}
		public int red() {
			return this.red;
		}
		public int green() {
			return this.green;
		}
		public int blue() {
			return this.blue;
		}
		
		@Override
		public String toString() {
			return String.format("Game %s: red %s, green %s, blue %s", id, red, green, blue);
		}
	}

}
