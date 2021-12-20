package com.matthieu.aoc_2021;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.matrix.Row;
import com.matthieu.aoc.resolver.year_2021.Resolver19p1;

public class Day19Test {

	private Matrix<Integer> m;
	private Resolver19p1 resolver;
	
	@Before
	public void initialize() {
		List<String> data = Arrays.asList("1,10,100", 
										  "2,20,200", 
										  "3,30,300");

		this.resolver = new Resolver19p1();
		this.m = new Matrix<Integer>(data, Integer::parseInt, ",");
	}
	
	@Test
	public void rotateXTest() {
		resolver.rotateX(m);
		
		assertEquals(-200, m.get(1, 1));// y
		assertEquals(20, m.get(2, 1));	// z
	}
	
	@Test
	public void rotateYTest() {
		resolver.rotateY(m);
		
		assertEquals(1, m.get(2, 0));	// z
		assertEquals(-100, m.get(0, 0));// x
	}

	@Test
	public void rotateZTest() {
		resolver.rotateZ(m);
		
		assertEquals(-30, m.get(0, 2)); // x
		assertEquals(3, m.get(1, 2));	// y
	}
	
	@Test
	public void getDistanceTest() {
		Row<Integer> a = new Row<>(Arrays.asList(2, 0, 2));
		Row<Integer> b = new Row<>(Arrays.asList(1, 2, 0));
		
		assertEquals(3, resolver.getDistance(a, b));
	}
}
