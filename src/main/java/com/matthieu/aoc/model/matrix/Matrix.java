package com.matthieu.aoc.model.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.matthieu.aoc.model.Point;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.service.parser.Parser;

public class Matrix<T> {

	protected List<Row<T>> datas;
	
	public Matrix(List<String> values, Parser<T> parser) {
		this(values, parser, " ");
	}
	
	public Matrix(List<String> values, Parser<T> parser, String separator) {
		if(values.get(0).contains(separator)) {
			this.datas = values.stream()
					.map(line -> Arrays.asList(line.split(separator)))
					.map(list -> list.stream().map(parser::parse).collect(Collectors.toCollection(ArrayList::new)))
					.map(Row<T>::new)
					.collect(Collectors.toCollection(ArrayList::new));
			
		} else {
			this.datas = values.stream()
					.map(line -> line.chars().mapToObj(c -> (char) c)
												.map(String::valueOf)
												.map(parser::parse)
												.collect(Collectors.toCollection(ArrayList::new)))
					.map(Row<T>::new)
					.collect(Collectors.toCollection(ArrayList::new));
		}
	}
	
	public Matrix(int width, int height, Supplier<T> defaultValueGetter) {
		this.datas = new ArrayList<>(height);
		
		for (int i = 0; i < height; i++) {
			this.datas.add(new Row<>(width, defaultValueGetter));
		}
	}

	public Matrix(int width, int height, MatrixFunction<T> defaultValueGetter) {
		this.datas = new ArrayList<>(height);
		
		for (int y = 0; y < height; y++) {
			this.datas.add(new Row<>(width, y, defaultValueGetter));
		}
	}
	

	public T get(int x, int y) {
		Duo<Integer, Integer> c = this.controlParams(x, y);
		
		return this.datas.get(c.y()).get(c.x());
	}
	
    public T get(Point position) {
        return this.get(position.x(), position.getY());
    }

	public T getRight(int x, int y) {
		return this.get(x + 1, y);
	}
	
	public T getBottom(int x, int y) {
		return this.get(x, y + 1);
	}
	
    public void set(Point position, T value) {
        this.set(position.x(), position.y(), value);
    }

    public void set(int x, int y, T value) {
        Duo<Integer, Integer> c = this.controlParams(x, y);

        this.datas.get(c.y()).set(c.x(), value);
	}
	
	public void setRight(int x, int y, T value) {
		this.set(x + 1, y, value);
	}
	
	public void setBottom(int x, int y, T value) {
		this.set(x, y + 1, value);
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

	public Row<T> getColumn(int x) {
		List<T> column = new ArrayList<>();
		
		for (int y = 0; y < this.getHeight(); y++) {
			column.add(this.get(x, y));
		}
		
		return new Row<>(column);
	}

	public List<Row<T>> getRows() {
		return this.datas;
	}
	
	public List<Row<T>> getColumns() {
		List<Row<T>> columns = new ArrayList<>();
		
		for (int x = 0; x < this.getWidth(); x++) {
			columns.add(this.getColumn(x));
		}
		
		return columns;
	}
	
	public void insertRow(int y, List<T> row) {
		List<T> modifiableList = new ArrayList<>();
		modifiableList.addAll(row);
		
		this.insertRow(y, new Row<>(modifiableList));
	}
	
	public void insertRow(int y, Row<T> row) {
		if(row.size() != this.getWidth()) {
			throw new IllegalArgumentException(String.format("Row hasn't right size, expected %s but has %s.", this.getWidth(), row.size()));
		}
		
		this.datas.add(y, row);
	}
	
	public void insertColumn(int x, List<T> column) {
		List<T> modifiableList = new ArrayList<>();
		modifiableList.addAll(column);
		
		this.insertColumn(x, new Row<>(modifiableList));
	}
	
	public void insertColumn(int x, Row<T> column) {
		if(column.size() != this.getHeight()) {
			throw new IllegalArgumentException(String.format("Column hasn't right size, expected %s but has %s.", this.getHeight(), column.size()));
		}
		
		for (int i = 0; i < this.getHeight(); i++) {
			this.datas.get(i).get().add(x, column.get(i));
		}
	}
	
	public List<T> getNeightbours(int x, int y) {
		return this.getValues(getNeigthboursCoords(x, y));
	}

	public List<T> getRegion(int x, int y) {
		return this.getValues(getRegionCoords(x, y));
	}
	
	public List<T> getNeightboursCross(int x, int y) {
		return this.getValues(getNeigthboursCrossCoords(x, y));
	}
	
	public List<T> getValues(List<Duo<Integer, Integer>> coords) {
		return coords.stream()
						.map(c -> this.getQuietly(c.a(), c.b()))
						.filter(Objects::nonNull)
						.collect(Collectors.toList());
	}
	
	public List<Cell<T>> getCells() {
		List<Cell<T>> cells = new ArrayList<>();
		this.forEach((x, y, v) -> cells.add(new Cell<>(x, y, v)));
		return cells;
	}
	
	public T getQuietly(int x, int y) {
		try {
			this.controlParams(x, y);
		} catch(IllegalArgumentException e) {
			return null;
		}
		
		return this.datas.get(y).get(x);
	}

	public Duo<Integer, Integer> controlParams(int x, int y) {
		if(x < 0) {
			throw new IllegalArgumentException("Given parameter x ("+ x +") is lower than 0");
		} else if(y < 0) {
			throw new IllegalArgumentException("Given parameter y ("+ y +") is lower than 0");
		} else if(y >= datas.size()) {
			throw new IllegalArgumentException("Given parameter y ("+ y +") is greater than " + (datas.size() - 1));
		} else if(x >= datas.get(y).size()) {
			throw new IllegalArgumentException("Given parameter x ("+ x +") is greater than " + (datas.get(y).size() - 1));
		}
		
		return new Duo<>(x, y);
	}
	
	public Stream<T> stream() {
		return this.datas.stream()
				.map(Row::get)
				.flatMap(List::stream);
	}
	
	public Stream<Cell<T>> cellStream() {
		List<Cell<T>> cells = new ArrayList<>();
		
		for (int y = 0; y < this.getHeight(); y++) {
			for (int x = 0; x < this.getWidth(); x++) {
				
				T value = this.getQuietly(x, y);
				
				cells.add(new Cell<T>(x, y, value));
			}
		}
		
		return cells.stream();
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
		for (Duo<Integer, Integer> coord : getNeigthboursCrossCoords(x, y)) {
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
	
	public Matrix<T> submatrix(int x1, int x2, int y1, int y2) {
		int width = x2 - x1 + 1;
		int height = y2 - y1 + 1;
		
		Matrix<T> subMatrix = new Matrix<>(width, height, () -> null);
		
		for (int y = y1; y <= y2; y++) {
			for (int x = x1; x <= x2; x++) {
				subMatrix.set(x - x1, y - y1, this.get(x, y));
			}
		}
		
		return subMatrix;
	}
	
    public boolean isOut(int x, int y) {
        return x < 0 || y < 0 || x > this.getMaxX() || y > this.getMaxY();
    }

	public static List<Duo<Integer, Integer>> getNeigthboursCoords(int x, int y) {
		return Arrays.asList(new Duo<>(x-1, y-1), 
							new Duo<>(x, y-1), 
							new Duo<>(x+1, y-1), 
							new Duo<>(x-1, y), 
							new Duo<>(x+1, y), 
							new Duo<>(x-1, y+1), 
							new Duo<>(x, y+1), 
							new Duo<>(x+1, y+1));
	}

	public static List<Duo<Integer, Integer>> getRegionCoords(int x, int y) {
		return Arrays.asList(new Duo<>(x-1, y-1), 
								new Duo<>(x, y-1), 
								new Duo<>(x+1, y-1), 
								new Duo<>(x-1, y),
								new Duo<>(x, y),
								new Duo<>(x+1, y), 
								new Duo<>(x-1, y+1), 
								new Duo<>(x, y+1), 
								new Duo<>(x+1, y+1));
	}
	
	public static List<Duo<Integer, Integer>> getNeigthboursCrossCoords(int x, int y) {
		return Arrays.asList(new Duo<>(x-1, y), 
							new Duo<>(x+1, y), 
							new Duo<>(x, y-1), 
							new Duo<>(x, y+1));
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
