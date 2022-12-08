package com.matthieu.aoc.model.year_2022.filesystem;

import java.util.ArrayList;
import java.util.List;

public class Filex implements Comparable<Filex> {

	private final String name;
	private final boolean isDirectory;
	private final List<Filex> content;
	private final long size;
	private final Filex parentDir;
	
	public Filex(String name, Filex parentDir, long size) {
		this.name = name;
		this.parentDir = parentDir;
		this.isDirectory = false;
		this.content = null;
		this.size = size;
	}

	public Filex(String name, Filex parentDir) {
		this.name = name;
		this.parentDir = parentDir;
		this.isDirectory = true;
		this.content = new ArrayList<>();
		this.size = 0;
	}
	
	
	public long getSize() {
		return isDirectory ? content.stream().mapToLong(Filex::getSize).sum() : this.size;
	}
	
	public long getSizeIfLowerThan(long max) {
		return isDirectory ? content.stream().mapToLong(f -> {
			long s = f.getSize();
			
			return s > max ? 0 : s;
		}).sum() : this.size;
	}
	
	public void addFilex(Filex filex) {
		this.content.add(filex);
	}
	
	public boolean contains(String filexName) {
		return this.content.stream().anyMatch(f -> f.getName().equals(filexName));
	}
	
	public Filex findFile(String filexName) {
		return this.content.stream().filter(f -> f.getName().equals(filexName)).findAny().orElseThrow(IllegalStateException::new);
	}


	public String getName() {
		return name;
	}
	public boolean isDirectory() {
		return isDirectory;
	}
	public List<Filex> getContent() {
		return content;
	}
	public Filex getParentDir() {
		return parentDir;
	}

	@Override
	public String toString() {
		return "Filex [name=" + name + ", isDirectory=" + isDirectory + ", size=" + size + "]";
	}

	@Override
	public int compareTo(Filex f) {
		if(this.getSize() >= f.getSize()) {
			return 1;
		} else if(this.getSize() < f.getSize()) {
			return -1;
		}
		
		return 0;
	}

	
}
