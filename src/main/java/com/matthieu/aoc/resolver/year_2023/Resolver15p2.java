package com.matthieu.aoc.resolver.year_2023;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import com.matthieu.aoc.exception.PrepareDataException;

public class Resolver15p2 extends Resolver15p1 {

	private List<ArrayList<Lens>> boxes;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		super.prepareData(values);
		this.boxes = IntStream.range(0, this.maxResult).mapToObj(i -> new ArrayList<Lens>()).toList();
	}
	
	@Override
	public boolean solve() throws Exception {
		
		for (String step : this.steps) {
			
			if(step.contains("-")) {
				int boxIndex = this.hash(step.replace("-", ""));
				this.removeLensFromBox(step, this.boxes.get(boxIndex));
				
			} else if(step.contains("=")) {
				Lens lens = new Lens(step);
				int boxIndex = this.hash(lens.getLabel());
				this.addLensInBox(lens, this.boxes.get(boxIndex));
				
			} else {
				throw new IllegalStateException("Unknown step " + step);
			}
		}
		
		return true;
	}
	
	@Override
	public String get() {
		return LongStream.range(0, this.maxResult).map(this::getBoxFocusingPower).sum() + "";
	}
	
	private void removeLensFromBox(String lens, ArrayList<Lens> box) {
		String lensLable = lens.replace("-", "");

		box.remove(new Lens(lensLable + "=0"));
	}
	
	private void addLensInBox(Lens lens, ArrayList<Lens> arrayList) {
		int lensIndex = -1;
		
		if((lensIndex = arrayList.indexOf(lens)) >= 0) {
			arrayList.set(lensIndex, lens);
		} else {
			arrayList.add(lens);
		}
	}
	
	private long getBoxFocusingPower(long boxIndex) {
		ArrayList<Lens> box = this.boxes.get((int) boxIndex);
		
		if(box.isEmpty()) {
			return 0;
		}
		
		return (boxIndex + 1) * LongStream.range(0, box.size()).map(i -> (i + 1) * box.get((int) i).getFocalLength()).sum();
	}
	
	public static void main(String[] args) {
		System.out.println("rn=1".split("=")[1]);
	}
	
	private class Lens {
		private String label;
		private int focalLength;
		
		protected Lens(String description) {
			this.label = description.split("=")[0];
			this.focalLength = Integer.parseInt(description.split("=")[1]);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			return result;
		}

		public String getLabel() {
			return label;
		}

		public int getFocalLength() {
			return focalLength;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Lens other = (Lens) obj;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			return true;
		}
		
	}
}
