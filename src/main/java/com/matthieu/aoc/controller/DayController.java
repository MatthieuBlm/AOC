package com.matthieu.aoc.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.resolver.Resolver;
import com.matthieu.aoc.service.InputFetcher;
import com.matthieu.aoc.service.InputReader;

@RestController
@RequestMapping("day")
public class DayController {

	private static final Logger logger = LoggerFactory.getLogger(DayController.class);

	@Autowired
	private InputReader inputReader;
	
	@Autowired
	private InputFetcher inputFetcher;
	
	
	@GetMapping("{year}/{dayNumber}/{partNumber}")
	public ResponseEntity<String> resolve(@PathVariable int year, @PathVariable int dayNumber, @PathVariable int partNumber, @RequestBody(required = false) String body) throws IOException, PrepareDataException, SolveException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		String resolverName = String.format("com.matthieu.aoc.resolver.year_%s.Resolver%sp%s", year, dayNumber, partNumber);
		
		// Find resolver class
		Class<?> resolverClass = Class.forName(resolverName);
		
		// Create resolver instance
		Resolver resolver = (Resolver) resolverClass.getConstructor().newInstance();
		
		// Prepare data
		long startTime = System.currentTimeMillis();
		this.prepareData(body, resolver, year, dayNumber);
		long endTime = System.currentTimeMillis();
		
		logger.info("Data prepared for day {} part {} in {}ms", dayNumber, partNumber, (endTime - startTime));
		
		// Solve problem
		startTime = System.currentTimeMillis();
		resolver.solve();
		endTime = System.currentTimeMillis();
		
		logger.info("Problem solved for day {} part {} in {}ms", dayNumber, partNumber, (endTime - startTime));
		
		// Send response
		return ResponseEntity.ok(resolver.get());
	}
	
	private void prepareData(String body, Resolver resolver, int year, int dayNumber) throws PrepareDataException, IOException {
		List<String> input = null;
		
		if(body == null) {
			Path inputPath = Paths.get("src/main/resources/inputs").resolve(String.valueOf(year)).resolve("input_" + dayNumber);
			
			if(!inputPath.toFile().exists()) {
				String rawInput = inputFetcher.fetchInput(year, dayNumber);
				Files.write(inputPath, rawInput.getBytes(), StandardOpenOption.CREATE);
				
				logger.info("Input of day {} fetched", dayNumber);
			}

			input = this.inputReader.getInputAsStringArray(Files.readString(inputPath));
				
		} else {
			input = this.inputReader.getInputAsStringArray(body);
		}
		
		resolver.prepareData(input);
	}
}
