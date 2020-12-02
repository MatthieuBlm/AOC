package com.matthieu.aoc_2020.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc_2020.service.parser.Parser;

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
		
		return this.datas.get(y)[x];
	}
	
	public <T> T get(Parser<T> parser, int x, int y) {
		return parser.parse(this.get(x, y));
	}
	
	
	public void set(int x, int y, String value) {
		this.controlParams(x, y);

		this.datas.get(x)[y] = value;
	}
	
	public List<String[]> getDatas() {
		return this.datas;
	}

	public String[] getRow(int x) {
		return this.datas.get(x);
	}
	
	public int getMaxY() {
		return this.datas.get(0).length;
	}
	
	public int getMaxX() {
		return this.datas.size();
	}
	
	public <T> List<T> getColumn(Parser<T> parser, int x) {
		List<T> result = new ArrayList<>();
		
		for (int y = 0; y < this.getMaxY() - 1; y++) {
			result.add(parser.parse(this.get(x, y)));
		}
		
		return result;
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
		} else if(y >= datas.size()) {
			throw new IllegalArgumentException("Given parameter x ("+ y +") is greater than " + (datas.size() - 1));
		} else if(x >= datas.get(y).length) {
			throw new IllegalArgumentException("Given parameter y ("+ x +") is greater than " + (datas.get(y).length - 1));
		}
	}
}
