package com.matthieu.aoc.service;

import java.util.List;

public class ListUtils {

	public static <T> T getLast(List<T> list) {
		return list.get(list.size() - 1);
	}
	
    public static boolean isSame(List<?> a, List<?> b) {
        if (a.size() != b.size()) {
            return false;
        }

        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i))) {
                return false;
            }
        }

        return true;
    }

}
