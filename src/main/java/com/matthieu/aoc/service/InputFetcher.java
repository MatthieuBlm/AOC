package com.matthieu.aoc.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import okhttp3.Headers;

@Service
public class InputFetcher {

	@Autowired
	private HttpClient httpClient;
	
	public String fetchInput(int year, int day) throws IOException {
		String url = String.format("https://adventofcode.com/%s/day/%s/input", year, day);
		Path sessionIdPath = Paths.get("src/main/resources/sessionId");
		
		if(sessionIdPath.toFile().exists()) {
			String sessionId = Files.readString(sessionIdPath);
			
			return this.httpClient.get(url, Headers.of("Cookie", "session=" + sessionId));
		}
		
		throw new IllegalStateException("No session id found to fetch input");
	}
	
}
