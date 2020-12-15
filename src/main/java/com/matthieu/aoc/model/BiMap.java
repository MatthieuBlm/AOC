package com.matthieu.aoc.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.matthieu.aoc.model.tuple.Duo;

public class BiMap<K1, K2> {

	private Map<K1, K2> mapA;
	private Map<K2, K1> mapB;
	
	
	public BiMap() {
		this.mapA = new HashMap<>();
		this.mapB = new HashMap<>();
	}
	
	
	public int size() {
		return this.mapA.size();
	}

	public boolean isEmpty() {
		return mapA.isEmpty();
	}

	public boolean containsKey(Object key) {
		return this.mapA.containsKey(key) || this.mapB.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return this.mapA.containsValue(value) || this.mapB.containsValue(value);
	}

	public Duo<K1, K2> get(Object key) {
		K2 key2 = this.mapA.get(key);
		K1 key1 = null;
		
		if(key2 == null) {
			key1 = this.mapB.get(key);
			key2 = this.mapA.get(key1);
		} else {
			key1 = this.mapB.get(key2);
		}
		
		return new Duo<>(key1, key2);
	}
	
	public K1 getK1(Object key) {
		return this.mapB.get(key);
	}
	
	public K2 getK2(Object key) {
		return this.mapA.get(key);
	}
	
	public Duo<K1, K2> put(K1 key1, K2 key2) {
		this.mapA.put(key1, key2);
		this.mapB.put(key2, key1);
		
		return new Duo<>(key1, key2);
	}

	public Duo<K1, K2> remove(Object key) {
		K2 key2 = this.mapA.remove(key);
		K1 key1 = null;
		
		if(key2 == null) {
			key1 = this.mapB.remove(key);
			key2 = this.mapA.remove(key1);
		} else {
			key1 = this.mapB.remove(key2);
		}
		
		return new Duo<>(key1, key2);
	}

	public void clear() {
		this.mapA.clear();
		this.mapB.clear();
	}

	public Set<K1> key1Set() {
		return this.mapA.keySet();
	}

	public Set<K2> key2Set() {
		return this.mapB.keySet();
	}

	public Set<Duo<K1, K2>> duoSet() {
		return this.mapA.entrySet().stream()
							.map(entry -> new Duo<>(entry.getKey(), entry.getValue()))
							.collect(Collectors.toSet());
	}
}
