package com.matthieu.aoc_2020.model;

import java.util.List;
import java.util.stream.Collectors;

public class Matrix {

	private List<String[]> datas;
	
	
	public Matrix(List<String> values) {
		this.initDatas(values, " ");
	}
	
	public Matrix(List<String> values, String separator) {
		this.initDatas(values, separator);
	}
	
	
	public String get(int x, int y) {
		this.controlParams(x, y);
		
		return this.datas.get(x)[y];
	}
	
	public void set(int x, int y, String value) {
		this.controlParams(x, y);

		this.datas.get(x)[y] = value;
	}
	
	public int getRowSize(int x) {
		return this.datas.get(x).length;
	}
	
	private void initDatas(List<String> values, String separator) {
		this.datas = values.stream()
				.map(line -> line.split(separator))
				.collect(Collectors.toList());
	}
	
	private void controlParams(int x, int y) {
		if(x < 0) {
			throw new IllegalArgumentException("Given parameter x ("+ x +") is lower than 0");
		} else if(y < 0) {
			throw new IllegalArgumentException("Given parameter y ("+ y +") is lower than 0");
		} else if(x >= datas.size()) {
			throw new IllegalArgumentException("Given parameter x ("+ x +") is greater than " + (datas.size() - 1));
		} else if(y >= datas.get(x).length) {
			throw new IllegalArgumentException("Given parameter y ("+ y +") is greater than " + (datas.get(x).length - 1));
		}
	}
}
