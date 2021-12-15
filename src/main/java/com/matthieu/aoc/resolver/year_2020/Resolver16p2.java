package com.matthieu.aoc.resolver.year_2020;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.matrix.Row;
import com.matthieu.aoc.model.year_2020.TicketField;

public class Resolver16p2 extends Resolver16p1 {

	private long result;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		
		this.result = 1;
		
		// Filter invalid ticket
		for (int y = this.nearbyTickets.getYSize() - 1; y >= 0 ; y--) {
			
			if(this.isValidTicket(this.nearbyTickets.getRow(y))) {
				this.nearbyTickets.removeRow(y);
			}
		}
		
		// Add our ticket
		this.nearbyTickets.addRow(new Row<>(this.ticket));
	}

	@Override
	public boolean solve() throws SolveException {
		List<Row<Integer>> columns = this.nearbyTickets.getColumns();
		
		
		for (int x = 0; x < this.nearbyTickets.getXSize(); x++) {
			Row<Integer> column  = columns.get(x);
			
			for (TicketField field : this.ticketFields) {
				
				if(this.allMatch(column, field) && field.getName().startsWith("departure")) {
					this.result *= this.ticket.get(x);
					break;
				}
				
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.result);
	}

	private boolean isValidTicket(Row<Integer> t) {
		for (Integer i : t.get()) {
			if(this.ticketFields.stream().noneMatch(f -> f.isPossibleValue(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean allMatch(Row<Integer> values, TicketField field) {
		return values.get().stream().allMatch(field::isPossibleValue);
	}
	
}
