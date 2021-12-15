package com.matthieu.aoc.resolver.year_2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.year_2020.TicketField;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver16p1 implements Resolver {

	protected static final int FIELDS = 0;
	protected static final int YOUR_TICKET = 1;
	protected static final int NEARBY_TICKET = 2;
	
	protected List<TicketField> ticketFields;
	protected Matrix<Integer> nearbyTickets;
	protected List<Integer> ticket;
	protected long errorRate;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		List<String> dataForNearbyTicketMatrix = new ArrayList<>();
		ticketFields = new ArrayList<>();

		int step = FIELDS;
		
		for(String line : values) {
			
			// Determine which is current step
			if(line.isBlank() && step == FIELDS) {
				step = YOUR_TICKET;
				continue;
			} else if(line.isBlank() && step == YOUR_TICKET) {
				step = NEARBY_TICKET;
				continue;
			}
			
			// Process line
			if(step == FIELDS) {
				String fieldName = line.split(": ")[0];
				String[] bornes = line.split(": ")[1].split(" or ");
				
				ticketFields.add(new TicketField(fieldName, bornes[0], bornes[1]));
				
				
			} else if(step == YOUR_TICKET && !line.contains("your ticket:")) {
				this.ticket = Arrays.asList(line.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
				
			} else if(step == NEARBY_TICKET && !line.contains("nearby tickets:")) {
				dataForNearbyTicketMatrix.add(line);
			}
		}
		
		this.nearbyTickets = new Matrix<>(dataForNearbyTicketMatrix, Integer::parseInt, ",");
	}

	@Override
	public boolean solve() throws SolveException {
		this.nearbyTickets.forEach(i -> {
			if(ticketFields.stream().noneMatch(t -> t.isPossibleValue(i))) {
				errorRate += i;
			}
		});
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.errorRate);
	}

}
