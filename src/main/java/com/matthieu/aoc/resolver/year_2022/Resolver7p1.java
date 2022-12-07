package com.matthieu.aoc.resolver.year_2022;

import java.util.ArrayList;
import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.modelyear_2022.filesystem.Filex;
import com.matthieu.aoc.resolver.Resolver;

public class Resolver7p1 implements Resolver {
	
	protected List<String> prompts;
	protected Filex currentDirectory;
	protected Filex root;
	protected List<Filex> files;
	protected long maxSizeToTake;

	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		this.files = new ArrayList<>();
		this.prompts = values;
		this.root = new Filex("/", null);
		this.currentDirectory = this.root;
		
		this.prompts.remove(0);
		this.maxSizeToTake = 100000;
	}

	@Override
	public boolean solve() throws SolveException {
		int i = 0;
		
		while (i < prompts.size()) {
			if(prompts.get(i).contains("cd")) {
				this.cd(i);
				i++;
			} else if(prompts.get(i).contains("ls")) {
				i += this.ls(i) + 1;
			}
		}
		
		return true;
	}

	@Override
	public String get() {
		return String.valueOf(this.files.stream().filter(Filex::isDirectory).mapToLong(Filex::getSize).filter(l -> l <= maxSizeToTake).sum());
	}
	
	
	protected void cd(int promptIndex) {
		String[] splittedPrompt = this.prompts.get(promptIndex).split(" ");
		
		if(splittedPrompt[2].equals("..")) {
			this.currentDirectory = currentDirectory.getParentDir();
		} else {
			this.currentDirectory = currentDirectory.findFile(splittedPrompt[2]);
		}
	}
	
	protected int ls(int promptIndex) {
		currentDirectory.getContent().clear();
		
		String currentPrompt;
		int concernedLineNumber = 0;
		
		for (int i = promptIndex + 1; i < prompts.size() && !(currentPrompt = prompts.get(i)).contains("$"); i++) {
			String filexName = currentPrompt.split(" ")[1];
			concernedLineNumber++;

			if(!currentDirectory.contains(filexName)) {
				if(currentPrompt.contains("dir")) {
					Filex newFile = new Filex(filexName, currentDirectory);
					
					currentDirectory.addFilex(newFile);
					files.add(newFile);
				} else {
					long size = Long.parseLong(currentPrompt.split(" ")[0]);
					Filex newFile = new Filex(filexName, currentDirectory, size);
					
					currentDirectory.addFilex(newFile);
					files.add(newFile);
				}
			}
		}
		
		return concernedLineNumber;
		
	}

}
