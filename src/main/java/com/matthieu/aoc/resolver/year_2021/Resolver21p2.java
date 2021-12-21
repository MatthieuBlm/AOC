package com.matthieu.aoc.resolver.year_2021;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;

public class Resolver21p2 extends Resolver21p1 {

	private long[][][][][] universeStates;
	private long player1Wins;
	private long player2Wins;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		
		this.scoreToWin = 21;
		
		universeStates = new long[2][32][32][10][10];	// [player turn] [player1 score] [player2 score] [player1 position] [player2 position]
		
		universeStates[0][0][0][player1.a() - 1][player2.a() - 1] = 1;
	}
	
	
	@Override
	public boolean solve() throws SolveException {
		
		for(int score1 = 0; score1 < 21; score1++) {
			for (int score2 = 0; score2 < 21; score2++) {
				for (int position1 = 0; position1 < 10; position1++) {
					for (int position2 = 0; position2 < 10; position2++) {
						for (int roll1 = 1; roll1 <= 3; roll1++) {
							for (int roll2 = 1; roll2 <= 3; roll2++) {
								for (int roll3 = 1; roll3 <= 3; roll3++) {
									int rollSum = roll1 + roll2 + roll3;
									
									universeStates[1][score1 + (position1 + rollSum) % 10 + 1][score2][(position1 + rollSum) % 10][position2] += universeStates[0][score1][score2][position1][position2];
									universeStates[0][score1][score2 + (position2 + rollSum) % 10 + 1][position1][(position2 + rollSum) % 10] += universeStates[1][score1][score2][position1][position2];
								}
							}
						}
					}
				}
			}
		}
		
		for (int sc1 = 21; sc1 < 32; sc1++) {
			for (int sc2 = 0; sc2 < 21; sc2++) {
				for (int p1 = 0; p1 < 10; p1++) {
					for (int p2 = 0; p2 < 10; p2++) {
						this.player1Wins += universeStates[1][sc1][sc2][p1][p2];
					}
				}
			}
		}
		
		for(int sc1 = 0; sc1 < 21; sc1++) {
	        for(int sc2 = 21; sc2 < 32; sc2++) {
	            for(int p1 = 0; p1 < 10; p1++) {
	                for(int p2 = 0; p2 < 10; p2++) {
	                    this.player2Wins += universeStates[0][sc1][sc2][p1][p2];
	                }
	            }
	        }
	    }
		
		return true;
	}
	
	
	@Override
	public String get() {
		return String.valueOf(Math.max(this.player1Wins, player2Wins));
	}
}
