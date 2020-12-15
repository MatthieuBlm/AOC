package com.matthieu.aoc.model.tuple;

public class Duo<A, B> {

	private A a;
	private B b;
	
	
	public Duo(A a, B b) {
		this.a = a;
		this.b = b;
	}
	
	
	public A a() {
		return this.a;
	}
	
	public Duo<A, B> a(A value) {
		this.a = value;
		return this;
	}

	public B b() {
		return this.b;
	}

	public Duo<A, B> b(B value) {
		this.b = value;
		return this;
	}

	@Override
	public String toString() {
		return "[" + a + ", " + b + "]";
	}
}
