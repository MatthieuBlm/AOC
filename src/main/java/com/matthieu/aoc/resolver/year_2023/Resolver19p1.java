package com.matthieu.aoc.resolver.year_2023;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.Extractor;

public class Resolver19p1 implements Resolver {
	
	protected Map<String, Workflow> workflows;
	protected List<Part> parts;
	protected List<Part> acceptedParts;

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		String line;
		List<String> workflowsLine = new ArrayList<>();
		acceptedParts = new ArrayList<>();
		
		while(!(line = values.remove(0)).equals("")) {
			workflowsLine.add(line);
		}
		
		this.workflows = workflowsLine.stream().map(Workflow::new).collect(Collectors.toMap(w -> w.name, Function.identity()));
		this.parts = values.stream().map(Part::new).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public boolean solve() throws Exception {
		
		while(!parts.isEmpty()) {
			Part part = parts.remove(0);
			
			for(WCond condition: workflows.get(part.getWorkflow()).steps) {
				String result;
				
				if((result = condition.accept(part)) != null) {
					if(result.equals("A")) {
						this.acceptedParts.add(part);
					} else if(!result.equals("R")) {
						part.setWorkflow(result);
						this.parts.add(part);
					}
				}
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		return this.acceptedParts.stream()
				.map(p -> Stream.of(p.x, p.m, p.a, p.s))
				.flatMap(Function.identity())
				.mapToInt(Integer::intValue)
				.sum() + "";
	}
	
	
	protected class Workflow {
		protected String name;
		protected List<WCond> steps;
		
		protected Workflow(String description) {
			this.steps = new ArrayList<>();
			this.name = description.substring(0, description.indexOf('{'));
			String[] stepsArray = description.substring(description.indexOf('{') + 1, description.indexOf('}')).split(",");
			
			for (String step : stepsArray) {
				if(step.contains(">") || step.contains("<")) {
					this.steps.add(new WCond(step.substring(0, 1), step.charAt(1), step.substring(step.indexOf(':') + 1), Extractor.extractNumbers(step).get(0).intValue()));
				} else {
					this.steps.add(new WCond(null, '0', step, 0));
				}
			}
		}
	}
	
	protected class WCond {
		protected String property;
		protected char chevron;
		protected String acceptValue;
		protected int conditionValue;
		
		public WCond(String property, char chevron, String acceptValue, int conditionValue) {
			this.property = property;
			this.chevron = chevron;
			this.acceptValue = acceptValue;
			this.conditionValue = conditionValue;
		}
		
		public String accept(Part part) {
			if(property == null) {
				return acceptValue;
			}
			
			int partValue = invokeGetter(part, property);
			
			if(this.chevron == '>' && partValue > conditionValue) {
				return acceptValue;
			} else if(this.chevron == '<' && partValue < conditionValue) {
				return acceptValue;
			}
			
			return null;
		}
		
		public int invokeGetter(Object obj, String variableName) {
	        try {
	            PropertyDescriptor pd = new PropertyDescriptor(variableName, obj.getClass());
	            Method getter = pd.getReadMethod();
	            Object f = getter.invoke(obj);
	            return (int) f;
	        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException e) {
	            e.printStackTrace();
	            return 0;
	        }
	    }

		@Override
		public String toString() {
			return property != null ? property + chevron + conditionValue + ":" + acceptValue : acceptValue;
		}
		
	}
	
	protected class Part {
		protected int x;
		protected int m;
		protected int a;
		protected int s;
		protected String workflow;
		
		public Part(String description) {
			this.workflow = "in";
			String[] properties = description.substring(1, description.indexOf('}')).split(",");
			
			this.x = Integer.parseInt(properties[0].split("=")[1]);
			this.m = Integer.parseInt(properties[1].split("=")[1]);
			this.a = Integer.parseInt(properties[2].split("=")[1]);
			this.s = Integer.parseInt(properties[3].split("=")[1]);
		}
		
		

		public String getWorkflow() {
			return workflow;
		}

		public void setWorkflow(String workflow) {
			this.workflow = workflow;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getM() {
			return m;
		}

		public void setM(int m) {
			this.m = m;
		}

		public int getA() {
			return a;
		}

		public void setA(int a) {
			this.a = a;
		}

		public int getS() {
			return s;
		}

		public void setS(int s) {
			this.s = s;
		}

		@Override
		public String toString() {
			return "Part [x=" + x + ", m=" + m + ", a=" + a + ", s=" + s + ", workflow=" + workflow + "]";
		}
		
	}

}
