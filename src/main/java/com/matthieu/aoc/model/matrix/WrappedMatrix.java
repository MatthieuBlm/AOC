package com.matthieu.aoc.model.matrix;

import java.util.List;
import java.util.function.Supplier;

import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.service.parser.Parser;

public class WrappedMatrix<T> extends Matrix<T> {

	
	public WrappedMatrix(List<String> values, Parser<T> parser) {
		super(values, parser);
	}

	public WrappedMatrix(int width, int height, Supplier<T> defaultValueGetter) {
		super(width, height, defaultValueGetter);
	}

	public WrappedMatrix(List<String> values, Parser<T> parser, String separator) {
		super(values, parser, separator);
	}
	
	
	@Override
	public Duo<Integer, Integer> controlParams(int x, int y) {
		if(x < 0) {
			x = super.getWidth() + x;
		} else if(y < 0) {
			y = super.getHeight() + y;
		} else if(y >= super.datas.size()) {
			y = y - super.getHeight();
		} else if(x >= super.datas.get(y).size()) {
			x = x - super.getWidth();
		}
		
		return new Duo<>(x, y);
	}
}
