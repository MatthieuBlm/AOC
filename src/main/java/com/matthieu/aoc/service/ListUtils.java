package com.matthieu.aoc.service;

import java.util.List;

public class ListUtils {

	public static <T> T getLast(List<T> list) {
		return list.get(list.size() - 1);
	}
	
}
