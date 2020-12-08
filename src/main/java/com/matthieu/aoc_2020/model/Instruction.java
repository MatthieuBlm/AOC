package com.matthieu.aoc_2020.model;

public class Instruction {
	
	private String name;
	private int arg;
	
	
	public Instruction(String name, int arg) {
		this.name = name;
		this.arg = arg;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getArg() {
		return arg;
	}
	public void setArg(int arg) {
		this.arg = arg;
	}
	
	
}
