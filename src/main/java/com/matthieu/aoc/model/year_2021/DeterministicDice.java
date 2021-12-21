package com.matthieu.aoc.model.year_2021;

public class DeterministicDice {

	private int value;
	private int nbRoll;
	
	public DeterministicDice() {
		this.value = 1;
	}
	
	public int roll() {
		this.nbRoll++;
		
		if(value > 100)
			value = 1;
		
		return value++;
	}
	
	public int getNbRoll() {
		return this.nbRoll;
	}
	
	public static void main(String[] args) {
		DeterministicDice dice = new DeterministicDice();
		
		for (int i = 0; i < 600; i++) {
			System.out.println(dice.roll());
		}
	}
}
