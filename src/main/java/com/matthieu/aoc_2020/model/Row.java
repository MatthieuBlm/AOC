package com.matthieu.aoc_2020.model;

import java.util.List;

import com.matthieu.aoc_2020.service.parser.Parser;

public class Row {

	private List<String> value;
	
	public Row(List<String> value) {
		this.value = value;
	}
	
	public <T> T get(int x, Parser<T> parser) {
		return parser.parse(this.value.get(x));
	}
	
	public int size() {
		return this.value.size();
	}
	
	public List<String> get() {
		return this.value;
	}
	
	public String get(int x) {
		return this.value.get(x);
	}
	
	public void set(int x, String value) {
		this.value.set(x, value);
	}
}
