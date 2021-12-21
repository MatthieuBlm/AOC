package com.matthieu.aoc.resolver.year_2021;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.model.year_2021.DeterministicDice;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver21p1 implements Resolver {

	protected Duo<Integer, Integer> player1;	// Position, Score
	protected Duo<Integer, Integer> player2;
	protected int scoreToWin;
	protected DeterministicDice dice;
	protected int [] scoresBySpaces;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		String [] player1Data = values.get(0).split(" ");
		String [] player2Data = values.get(1).split(" ");
		
		this.player1 = new Duo<>(Integer.parseInt(player1Data[4]), 0);
		this.player2 = new Duo<>(Integer.parseInt(player2Data[4]), 0);
		
		this.scoreToWin = 1000;
		this.dice = new DeterministicDice();
		this.scoresBySpaces = new int[]{10, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	}

	@Override
	public boolean solve() throws SolveException {
		
		while(!hasWinningPlayer()) {
			
			int diceRoll = this.dice.roll() + this.dice.roll() + this.dice.roll();
			this.setPosition(player1, diceRoll);
			
			if(hasWinningPlayer())
				break;
			
			diceRoll = this.dice.roll() + this.dice.roll() + this.dice.roll();
			this.setPosition(player2, diceRoll);
		}
		
		return true;
	}

	@Override
	public String get() {
		int minScore = Math.min(player1.b(), player2.b());
		
		return String.valueOf(minScore * dice.getNbRoll());
	}
	
	protected boolean hasWinningPlayer() {
		return player1.b() >= scoreToWin || player2.b() >= scoreToWin;
	}

	protected void setPosition(Duo<Integer, Integer> player, int diceRoll) {
		int newPosition = player.a() + diceRoll;

		newPosition %= 10;
		
		player.a(newPosition);
		player.b(player.b() + this.scoresBySpaces[player.a()]);
	}
}
