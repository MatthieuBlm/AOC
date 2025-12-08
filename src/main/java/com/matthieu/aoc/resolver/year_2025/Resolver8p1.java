package com.matthieu.aoc.resolver.year_2025;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.tuple.Duo;
import com.matthieu.aoc.model.year_2025.JunctionBox;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver8p1 implements Resolver {
	
	protected List<JunctionBox> boxes;
	protected List<Duo<Duo<JunctionBox, JunctionBox>, Double>> sortedCouples;
	protected Set<JunctionBox> firstBoxOfCircuits;
	protected Set<Set<JunctionBox>> circuits;
	protected int coupleToJoin;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		coupleToJoin = 1000;
		firstBoxOfCircuits = new HashSet<>();
		boxes = values.stream()
				.map(l -> l.split(","))
				.map(l -> new JunctionBox(Integer.parseInt(l[0]), Integer.parseInt(l[1]), Integer.parseInt(l[2])))
				.toList();
		
		Set<Duo<JunctionBox, JunctionBox>> couples = new HashSet<>();

		this.boxes.forEach(boxA -> {
			this.boxes.forEach(boxB -> {
				
				if(boxA != boxB) {
					List<JunctionBox> couple = Stream.of(boxA, boxB).sorted().toList(); // To always have box in same order
					
					couples.add(new Duo<>(couple.get(0), couple.get(1)));
				}
				
			});
		});
		
		sortedCouples = couples.stream()
				.map(couple -> new Duo<Duo<JunctionBox, JunctionBox>, Double>(couple, getDistance(couple)))
				.sorted((a, b) -> (int) (a.b() - b.b()))
				.collect(Collectors.toCollection(() -> new ArrayList<>()));
	}

	@Override
	public boolean solve() throws Exception {

		this.linkBoxes();
		this.calcCircuitCount();
		
		return true;
	}

	@Override
	public String get() {
		List<Integer> sizes = circuits.stream().map(Set::size).sorted().toList();
		
		return (sizes.get(sizes.size() - 1).longValue() * sizes.get(sizes.size() - 2).longValue() * sizes.get(sizes.size() - 3).longValue()) + "";  
	}
	
	protected void linkBoxes() {
		for (int i = 0; i < Math.min(coupleToJoin, sortedCouples.size()); i++) {
			Duo<Duo<JunctionBox, JunctionBox>, Double> couple = sortedCouples.remove(0);
			JunctionBox boxA = couple.a().a();
			JunctionBox boxB = couple.a().b();

			// Both are in circuits
			if(boxA.getCircuit() != null && boxB.getCircuit() != null) {
				if(boxA.getCircuit() == boxB.getCircuit()) {
					// Nothing to do, should not happen
				} else {
					// Both are on different circuit, merge
					Set<JunctionBox> circuitA = boxA.getCircuit();
					Set<JunctionBox> circuitB = boxB.getCircuit();
					circuitA.addAll(circuitB);
					circuitB.forEach(box -> box.setCircuit(circuitA));
				}
			} else {
				// One (or both) is not in a circuit
				Set<JunctionBox> circuit = Optional.ofNullable(Optional.ofNullable(boxA.getCircuit()).orElse(boxB.getCircuit())).orElse(new HashSet<>());
				
				circuit.add(boxA);
				circuit.add(boxB);
				boxA.setCircuit(circuit);
				boxB.setCircuit(circuit);
			}
		}
	}
	
	protected void calcCircuitCount() {
		this.circuits = boxes.stream()
				.map(JunctionBox::getCircuit)
				.filter(Objects::nonNull)
				.collect(Collectors.toSet());
	}
	
	protected double getDistance(Duo<JunctionBox, JunctionBox> couple) {
		return this.getDistance(couple.a(), couple.b());
	}
	
	protected double getDistance(JunctionBox a, JunctionBox b) {
		return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2) + Math.pow(a.getZ() - b.getZ(), 2));
	}
	
}
