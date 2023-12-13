package com.matthieu.aoc.model.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.matthieu.aoc.service.parser.Parser;

public class Row<T> {

	private List<T> value;
	
	public Row(List<T> value) {
		this.value = value;
	}
	
	public Row(int lenght, Supplier<T> defaultValueGetter) {
		this.value = new ArrayList<>(lenght);
		
		for (int i = 0; i < lenght; i++) {
			this.value.add(defaultValueGetter.get());
		}
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
	
	public Stream<T> stream() {
		return this.value.stream();
	}

	@Override
	public String toString() {
		return  "[" + this.value.stream().map(Objects::toString).collect(Collectors.joining(","))+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Row other = (Row) obj;
		return Objects.equals(value, other.value);
	}
	
	
}
