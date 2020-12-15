package com.matthieu.aoc.model.matrix;

import java.util.List;

import com.matthieu.aoc.service.parser.Parser;

public class Row<T> {

	private List<T> value;
	
	public Row(List<T> value) {
		this.value = value;
	}
	
	public <K> K get(int x, Parser<K> parser) {
		return parser.parse(String.valueOf(this.get(x)));
	}
	
	public int size() {
		return this.value.size();
	}
	
	public List<T> get() {
		return this.value;
	}
	
	public T get(int x) {
		return this.value.get(x);
	}
	
	public void set(int x, T value) {
		this.value.set(x, value);
	}
}
