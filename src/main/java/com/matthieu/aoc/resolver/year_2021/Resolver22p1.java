package com.matthieu.aoc.resolver.year_2021;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.DimensionalGameOfLife;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver22p1 implements Resolver {

	protected List<Duo<String, Integer[]>> instructions;
	protected DimensionalGameOfLife reactor;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.reactor = new DimensionalGameOfLife(3, 100);
		this.instructions = new ArrayList<>();
		
		for (String line : values) {
			String[] splittedLine = line.split(" ");
			String type = splittedLine[0];
			
			String[] brutCoords = splittedLine[1].split(",");

			for (int i = 0; i < brutCoords.length; i++) {
				brutCoords[i] = brutCoords[i].replace("x=", "").replace("y=", "").replace("z=", "");
			}
			
			Integer[] coords = new Integer[6]; 
			coords[0] = Integer.parseInt(brutCoords[0].split("\\.\\.")[0]);
			coords[1] = Integer.parseInt(brutCoords[0].split("\\.\\.")[1]);
			coords[2] = Integer.parseInt(brutCoords[1].split("\\.\\.")[0]);
			coords[3] = Integer.parseInt(brutCoords[1].split("\\.\\.")[1]);
			coords[4] = Integer.parseInt(brutCoords[2].split("\\.\\.")[0]);
			coords[5] = Integer.parseInt(brutCoords[2].split("\\.\\.")[1]);
			
			this.instructions.add(new Duo<>(type, coords));
		}
	}

	@Override
	public boolean solve() throws SolveException {

		for (Duo<String, Integer[]> instrucion : instructions) {
			Integer[] c = instrucion.b();

			if(c[0] >= -50 && c[0] <= 50 && c[2] >= -50 && c[2] <= 50 && c[3] >= -50 && c[3] <= 50) {
				
				if(instrucion.a().equals("on")) {
					
					for (int x = c[0]; x <= c[1]; x++) {
						for (int y = c[2]; y <= c[3]; y++) {
							for (int z = c[4]; z <= c[5]; z++) {
								this.reactor.makeAlive(x + 50, y + 50, z + 50);
							}
						}
					}
					
				} else {
					
					for (int x = c[0]; x <= c[1]; x++) {
						for (int y = c[2]; y <= c[3]; y++) {
							for (int z = c[4]; z <= c[5]; z++) {
								this.reactor.makeDead(x + 50, y + 50, z + 50);
							}
						}
					}
				}
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.reactor.getNumberOfAliveCell());
	}

}
