package com.matthieu.aoc.resolver.year_2020;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.IntStream;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver15p2 extends Resolver15p1 {

	private TreeMap<Integer, Duo<Integer, Integer[]>> datas;	// [Number, [frequency, 2 lastTurnNumber]]
	private int lastNumber;
	private int currentTurnIndex;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		
		this.datas = new TreeMap<>();
		this.lastNumber = super.numbers.get(numbers.size() - 1);
		this.currentTurnIndex = super.numbers.size() + 1;
		this.resultTurn = 30000000;
		
		IntStream.range(0, super.numbers.size())
			.forEach(n -> datas.put(numbers.get(n), new Duo<>(1, new Integer[]{n+1, n+1})));
	}

	@Override
	public boolean solve() throws SolveException {
		
		for (int i = datas.size(); i < resultTurn; i++) {
			this.makeTurn();
		}
		
		return true;
	}
	
	@Override
	public String get() {
		return String.valueOf(lastNumber);
	}

	@Override
	protected void makeTurn() {
		Duo<Integer, Integer[]> lastNumberInfo = this.datas.get(this.lastNumber);
		
		if(lastNumberInfo.a() == 1) {
			this.addNumberOrIncrementFrequency(0);
		} else {
			Integer newNumber = lastNumberInfo.b()[0] - lastNumberInfo.b()[1];
			
			this.addNumberOrIncrementFrequency(newNumber);
		}
		
		currentTurnIndex++;
	}
	
	private void addNumberOrIncrementFrequency(int number) {
		Optional<Duo<Integer, Integer[]>> newNumberInfo = Optional.ofNullable(this.datas.get(number));
		
		if(newNumberInfo.isPresent()) {
			Duo<Integer, Integer[]> info = newNumberInfo.get();
			
			info.a(info.a() + 1);
			shift(currentTurnIndex, info.b());
		} else {
			datas.put(number, new Duo<>(1, new Integer[]{currentTurnIndex, 0}));
		}

		this.lastNumber = number;
	}
	
	private static void shift(Integer newNumber, Integer[] toAlter) {
		toAlter[1] = toAlter[0];
		toAlter[0] = newNumber;
	}
	
	
	
	
	private String datasToString() {
		StringBuilder sb = new StringBuilder();
		
		for (Map.Entry<Integer, Duo<Integer, Integer[]>> entry : datas.entrySet()) {
			sb.append(entry.getKey());
			sb.append("=[");
			sb.append(entry.getValue().a());
			sb.append(", [");
			
			for (Integer i : entry.getValue().b()) {
				sb.append(i);
				sb.append(", ");
			}
			
			sb.setLength(sb.length() - 2);
			sb.append("]], ");
		}
		
		return sb.toString();
	}
}


