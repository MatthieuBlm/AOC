package com.matthieu.aoc.resolver.year_2021;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.parser.Parser;

public class Resolver2p1 implements Resolver {

	protected static final Logger logger = LoggerFactory.getLogger(Resolver2p1.class);
	
	protected List<Duo<String, Integer>> instructions;
	protected int horizontal;
	protected int depth;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		Parser<Duo<String, Integer>> toDuo = s -> {
			String [] split = s.split(" ");
			return new Duo<String, Integer>(split[0], Integer.parseInt(split[1]));
		};
		
		this.instructions = values.stream().map(toDuo::parse).collect(Collectors.toList());
	}

	@Override
	public boolean solve() throws SolveException {
		
		for (Duo<String, Integer> duo : instructions) {
			switch(duo.a()) {
			case "forward":
				this.horizontal += duo.b();
				break;
			case "down":
				this.depth += duo.b();
				break;
			case "up":
				this.depth -= duo.b();
				break;
			default:
				logger.error("Unknown command");
			}
			
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.horizontal * this.depth);
	}

}
