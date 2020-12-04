package com.matthieu.aoc_2020.model.tuple;

import java.util.Map.Entry;

public class Tuple<K, V> implements Entry<K, V> {

	private K key;
	private V value;
	
	
	public Tuple(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	
	@Override
	public K getKey() {
		return this.key;
	}

	@Override
	public V getValue() {
		return this.value;
	}

	@Override
	public V setValue(V value) {
		this.value = value;
		return value;
	}

}
