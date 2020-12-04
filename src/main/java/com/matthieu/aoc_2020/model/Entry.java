package com.matthieu.aoc_2020.model;

public class Entry<K, V> implements java.util.Map.Entry<K, V> {

	private K key;
	private V value;
	
	public Entry(K key, V value) {
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
