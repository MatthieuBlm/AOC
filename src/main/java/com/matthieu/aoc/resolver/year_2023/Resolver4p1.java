package com.matthieu.aoc.resolver.year_2023;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver4p1 implements Resolver {

	protected List<Card> cards;
	protected long points;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		cards = values.stream().map(line -> {
			String[] splitedLine = line.split(": ");
			
			int id = Integer.parseInt(splitedLine[0].replace("Card ", "").strip());
			
			String[] splitedNumbers = splitedLine[1].split(" \\| ");
			
			List<Integer> winningNumbers = Stream.of(splitedNumbers[0].split(" "))
											.filter(Predicate.not(String::isBlank))
											.map(String::strip)
											.map(Integer::parseInt)
											.toList();
			List<Integer> numbers = Stream.of(splitedNumbers[1].split(" "))
											.filter(Predicate.not(String::isBlank))
											.map(String::strip)
											.map(Integer::parseInt)
											.toList();
			
			return new Card(id, winningNumbers, numbers);
		}).toList();
	}
	
	@Override
	public boolean solve() throws SolveException {
		this.points = this.calcPoints(cards);
		return true;
	}

	@Override
	public String get() {
		return points + "";
	}
	
	protected long calcPoints(List<Card> cardsToCalc) {
		return cardsToCalc.stream()
				.map(this::findWinningNumbres)
				.filter(Predicate.not(List::isEmpty))
				.mapToLong(wn -> wn.stream().reduce(1, (a, b) -> a * 2).longValue() / 2)
				.sum();
	}
	
	protected List<Integer> findWinningNumbres(Card c) {
		return c.getNumbers().stream().filter(n -> c.getWinningNumbers().contains(n)).toList();
	}

	protected class Card {
		protected int id;
		protected List<Integer> winningNumbers;
		protected List<Integer> numbers;
		
		public Card(int id, List<Integer> winningNumbers, List<Integer> numbers) {
			this.id = id;
			this.winningNumbers = winningNumbers;
			this.numbers = numbers;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public List<Integer> getNumbers() {
			return numbers;
		}

		public void setNumbers(List<Integer> numbers) {
			this.numbers = numbers;
		}

		public List<Integer> getWinningNumbers() {
			return winningNumbers;
		}

		public void setWinningNumbers(List<Integer> winningNumbers) {
			this.winningNumbers = winningNumbers;
		}
	}
}
