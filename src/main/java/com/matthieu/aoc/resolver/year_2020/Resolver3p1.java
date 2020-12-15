package com.matthieu.aoc.resolver.year_2020;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver3p1 implements Resolver {

	private Matrix<String> m;
	private int a;
	private int b;
	private Point pos;
	protected int treesEnconters;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.m = new Matrix<>(values, s -> s);
		this.a = 3;
		this.b = 1;
	}

	@Override
	public boolean solve() throws SolveException {
		this.pos = new Point();
		this.treesEnconters = 0;
		
		while(pos.getY() <= m.getMaxY()) {
			if(m.get(pos.getX(), pos.getY()).equals("#")) {
				treesEnconters++;
			}
			
			int newX = (this.pos.getX() + a) % (m.getMaxX() + 1);
			int newY = this.pos.getY() + b;
			
			this.pos.set(newX, newY);
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.treesEnconters);
	}

	protected void setA(int a) {
		this.a = a;
	}
	
	protected void setB(int b) {
		this.b = b;
	}
	
}
