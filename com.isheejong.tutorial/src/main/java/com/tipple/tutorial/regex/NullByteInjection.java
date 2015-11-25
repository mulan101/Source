package com.tipple.tutorial.regex;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 
 *  %00 (url encoding) = \0 (String) = 0 (int) = '' (char) = 0 (ascii)
 *  
 *  @author hee-jong.lee
 *
 */
public class NullByteInjection {

	public static void main(String[] args) {
		String src = "<scri%00pt>";
		
		String temp = URLDecoder.decode(src);
		
		temp.chars().forEach((item) -> {
			System.out.println((char)item + " "  + item);	
		});

		System.out.println(temp);
		
		System.out.println(" null indexof :" + temp.indexOf("\0"));
		System.out.println(" null last indexof :" + temp.lastIndexOf("\0"));
		System.out.println(" replaceAll : " + temp.replaceAll("\0","" ));
	
		char ch = '\0';
		
		System.out.println(" \\0 (int) : " + " " + (int)ch);
		
		String temp1 = "\0";
		
		temp1.chars().forEach((item) -> {
			System.out.println((char)item + " "  + item);	
		});
		
		
		String temp2 = "isheejong\0lbass";
		
		System.out.println(temp2);
		
	}
}
