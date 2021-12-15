package com.matthieu.aoc.model.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.service.parser.Parser;

public class Matrix<T> {

	private List<Row<T>> datas;
	
	
	public Matrix(List<String> values, Parser<T> parser) {
		this(values, parser, " ");
	}
	
	public Matrix(List<String> values, Parser<T> parser, String separator) {
		if(values.get(0).contains(separator)) {
			this.datas = values.stream()
					.map(line -> Arrays.asList(line.split(separator)))
					.map(list -> list.stream().map(parser::parse).collect(Collectors.toList()))
					.map(Row<T>::new)
					.collect(Collectors.toList());
			
		} else {
			this.datas = values.stream()
					.map(line -> line.chars().mapToObj(c -> (char) c)
												.map(String::valueOf)
												.map(parser::parse)
												.collect(Collectors.toList()))
					.map(Row<T>::new)
					.collect(Collectors.toList());
		}
	}
	
	public Matrix(int width, int height, Supplier<T> defaultValueGetter) {
		this.datas = new ArrayList<>(height);
		
		for (int i = 0; i < height; i++) {
			this.datas.add(new Row<>(width, defaultValueGetter));
		}
	}
	
	public void set(int x, int y, T value) {
		this.controlParams(x, y);

		this.datas.get(y).set(x, value);
	}

	public T get(int x, int y) {
		this.controlParams(x, y);
		
		return this.datas.get(y).get(x);
	}

	public int getMaxX() {
		return this.getWidth() - 1;
	}
	
	public int getMaxY() {
		return this.getHeight() - 1;
	}
	
	public int getWidth() {
		return this.datas.get(0).size();
	}
	
	public int getHeight() {
		return this.datas.size();
	}

	public Row<T> getRow(int y) {
		return this.datas.get(y);
	}

	public List<Row<T>> getRows() {
		return this.datas;
	}
	
	public List<Row<T>> getColumns() {
		List<Row<T>> columns = new ArrayList<>();
		
		for (int x = 0; x < this.getWidth(); x++) {
			List<T> column = new ArrayList<>();
			
			for (Row<T> t : this.getRows()) {
				column.add(t.get(x));
			}
			
			columns.add(new Row<>(column));
		}
		
		return columns;
	}
	
	public List<T> getNeightbours(int x, int y) {
		List<T> result = new ArrayList<>();
		
		result.add(this.getQuietly(x-1, y-1));
		result.add(this.getQuietly(x, y-1));
		result.add(this.getQuietly(x+1, y-1));
		result.add(this.getQuietly(x-1, y));
		result.add(this.getQuietly(x+1, y));
		result.add(this.getQuietly(x-1, y+1));
		result.add(this.getQuietly(x, y+1));
		result.add(this.getQuietly(x+1, y+1));
		
		return result.stream().filter(Objects::nonNull).collect(Collectors.toList());
	}

	public List<T> getNeightboursCross(int x, int y) {
		List<T> result = new ArrayList<>();
		
		result.add(this.getQuietly(x, y-1));
		result.add(this.getQuietly(x-1, y));
		result.add(this.getQuietly(x+1, y));
		result.add(this.getQuietly(x, y+1));
		
		return result.stream().filter(Objects::nonNull).collect(Collectors.toList());
	}
	
	public T getQuietly(int x, int y) {
		try {
			this.controlParams(x, y);
		} catch(IllegalArgumentException e) {
			return null;
		}
		
		return this.datas.get(y).get(x);
	}

	public void controlParams(int x, int y) {
		if(x < 0) {
			throw new IllegalArgumentException("Given parameter x ("+ x +") is lower than 0");
		} else if(y < 0) {
			throw new IllegalArgumentException("Given parameter y ("+ y +") is lower than 0");
		} else if(y >= datas.size()) {
			throw new IllegalArgumentException("Given parameter y ("+ y +") is greater than " + (datas.size() - 1));
		} else if(x >= datas.get(y).size()) {
			throw new IllegalArgumentException("Given parameter x ("+ x +") is greater than " + (datas.get(y).size() - 1));
		}
	}
	
	public Stream<T> stream() {
		return this.datas.stream()
				.map(Row::get)
				.flatMap(List::stream);
	}
	
	public void forEach(Consumer<T> consumer) {
		for (int y = 0; y < this.getHeight(); y++) {
			for (int x = 0; x < this.getWidth(); x++) {
				
				T value = this.getQuietly(x, y);
				
				if(value != null)
					consumer.accept(value);
			}
		}
	}
	
	public void forEach(MatrixIterator<T> iterator) {
		for (int y = 0; y < this.getHeight(); y++) {
			for (int x = 0; x < this.getWidth(); x++) {
				
				T value = this.getQuietly(x, y);
				
				if(value != null)
					iterator.iterate(x, y, value);
			}
		}
	}

	public void forEachNeigthbours(int x, int y, MatrixIterator<T> iterator) {
		for (int yi = y - 1; yi <= y + 1; yi++) {
			for (int xi = x - 1; xi <= x + 1; xi++) {
				T value = this.getQuietly(xi, yi);
				
				if(value != null  && !(x == xi && y == yi)) {
					iterator.iterate(xi, yi, value);
				}
			}
		}
	}
	
	public void forEachNeigthboursCross(int x, int y, MatrixIterator<T> iterator) {
		List<Duo<Integer, Integer>> coords = Arrays.asList(new Duo<>(x - 1, y), 
															new Duo<>(x + 1, y), 
															new Duo<>(x, y - 1), 
															new Duo<>(x, y + 1));
		for (Duo<Integer, Integer> coord : coords) {
			T value = this.getQuietly(coord.a(), coord.b());
			
			if(value != null)
				iterator.iterate(coord.a(), coord.b(), value);
		}
	}
	
	public Row<T> removeRow(int y) {
		return this.datas.remove(y);
	}
	
	public void addRow(Row<T> row) {
		this.datas.add(row);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i <= this.getMaxY(); i++) {
			for (int j = 0; j <= this.getMaxX(); j++) {
				builder.append(this.get(j, i));
			}
			builder.append("\n");
		}
		
		return builder.toString();
	}
}
