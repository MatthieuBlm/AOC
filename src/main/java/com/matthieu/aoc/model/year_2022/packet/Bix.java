package com.matthieu.aoc.model.year_2022.packet;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Bix implements Comparable<Bix>{
	
	private final static ObjectMapper mapper = new ObjectMapper();

	private Integer value;
	private List<Bix> values;
	
	
	public Bix(int value) {
		this.value = value;
	}
	
	public Bix(List<Bix> values) {
		this.values = values;
	}
	
	
	public static Bix from(String s) throws JsonProcessingException {
		if(s.indexOf('[') == 0 && s.indexOf(']') == s.length() - 1) {
			return fromIntegerArray(s);
		}
		
		return null;
	}
	
	public static Bix fromIntegerArray(String s) throws JsonProcessingException {
		return new Bix(mapper.readValue(s, new TypeReference<List<Integer>>() {}).stream().map(Bix::new).collect(Collectors.toList()));
	}
	
	public List<Bix> values() {
		return this.values;
	}
	
	public boolean isValue() {
		return value != null;
	}
	
	public boolean isArray() {
		return this.values != null;
	}
	
	public int length() {
		return this.isArray() ? this.values.size() : 1;
	}

	@Override
	public int compareTo(Bix other) {
		if(this.isValue() && other.isValue()) {
			return Integer.compare(this.value, other.value);
		} else if(this.isArray() && other.isArray()) {
			int i = 0;
			
			while(i < this.length() && i < other.length()) {
				int comparaison = this.values.get(i).compareTo(other.values().get(i));
				
				if(comparaison != 0) return comparaison;
			}
			
			return Integer.compare(this.length(), other.length());
			
		} else if(this.isValue()) {
			return new Bix(Arrays.asList(this)).compareTo(other);
			
		} else if(other.isValue()) {
			return this.compareTo(new Bix(Arrays.asList(other)));
		}
		
		return 0;
	}
	
}
