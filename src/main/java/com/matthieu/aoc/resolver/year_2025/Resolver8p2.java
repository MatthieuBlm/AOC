package com.matthieu.aoc.resolver.year_2025;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.model.year_2025.JunctionBox;

public class Resolver8p2 extends Resolver8p1 {
	
	private List<Set<JunctionBox>> circuitsList;
	private long distanceFromWall;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		this.circuitsList = new ArrayList<>();
	}
	
	@Override
	public boolean solve() throws Exception {

		this.linkBoxes();
		
		return true;
	}
	
	@Override
	public String get() {
		return distanceFromWall + "";
	}
	
	@Override
	protected void linkBoxes() {
		JunctionBox boxA = null;
		JunctionBox boxB = null;
		
		while (sortedCouples.size() > 0) {
			Duo<Duo<JunctionBox, JunctionBox>, Double> couple = sortedCouples.remove(0);
			
			boxA = couple.a().a();
			boxB = couple.a().b();

			// Both are in circuits
			if(boxA.getCircuit() != null && boxB.getCircuit() != null) {
				if(boxA.getCircuit() == boxB.getCircuit()) {
					// Do nothing, already on same circuit
				} else {
					// Both are on different circuit, merge
					Set<JunctionBox> circuitA = boxA.getCircuit();
					Set<JunctionBox> circuitB = boxB.getCircuit();
					
					circuitA.addAll(circuitB);
					circuitB.forEach(box -> box.setCircuit(circuitA));
					
					circuitsList.remove(circuitB);
				}
			} else if(boxA.getCircuit() == null && boxB.getCircuit() == null) {
				// Both are not in circuits
				Set<JunctionBox> circuit = new HashSet<>();

				circuitsList.add(circuit);

				circuit.add(boxA);
				circuit.add(boxB);
				boxA.setCircuit(circuit);
				boxB.setCircuit(circuit);
				
			} else {
				// One is not in a circuit
				Set<JunctionBox> circuit = boxA.getCircuit() != null ? boxA.getCircuit() : boxB.getCircuit();
				
				circuit.add(boxA);
				circuit.add(boxB);
				boxA.setCircuit(circuit);
				boxB.setCircuit(circuit);
			}
			
			if(circuitsList.size() == 1 && circuitsList.get(0).size() == boxes.size()) {
				break;
			}
		}
		
		distanceFromWall = ((long)boxA.getX()) * ((long) boxB.getX());
	}
}
