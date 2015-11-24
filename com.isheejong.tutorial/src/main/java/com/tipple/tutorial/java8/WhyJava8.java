package com.tipple.tutorial.java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class WhyJava8 {

	
	public static void main(String[] args) {

		try {
			
			Files.lines(
					Paths.get("C:\\Users\\heejong\\workspace\\Java8\\HelloWorld.txt"))
					.map(line -> line.split("[\\s]+"))
					.flatMap(Arrays::stream)
					.distinct().sorted().forEach( line -> System.out.println(line));
							
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
}
