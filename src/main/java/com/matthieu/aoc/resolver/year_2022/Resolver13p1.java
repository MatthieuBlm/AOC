package com.matthieu.aoc.resolver.year_2022;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.model.year_2022.packet.Bix;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver13p1 implements Resolver {
	
	List<Duo<Bix, Bix>> packets;

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.packets = new ArrayList<>();
	}

	@Override
	public boolean solve() throws SolveException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String get() {
		// TODO Auto-generated method stub
		return null;
	}

}
