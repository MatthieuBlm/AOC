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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Duo other = (Duo) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "[" + a + ", " + b + "]";
	}
}
