package com.tipple.tutorial.regex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  URLFilter.java
 * 
 * @author hee-jong.lee
 *
 */
public class URLFilter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String[] urls = new String[]
				{
				"http://www.google.com?www.olleh.com",
				 "http://www.olleh.com/",
				 "http://internet.olleh.com",
				 "http://www.olleh.computer",
				 "https://internet.olleh.com?urlcd=aaa&mRt=11",
				 "https://www.olleh.com1",
				 "https://www.olleh.com",
				 "https://www.olleh.computer",
				 "http://my.olleh.com:444/"};
		
		String cache = ".olleh.com";
		
		for(String url : urls ){
			url = url.replaceAll("https://|http://", "");
			String[] temp = url.split(":|\\?|\\/");
			
			String domain = "";
			if( temp != null && temp.length > 0 ){
				domain = temp[0];
			}else {
				System.out.println(domain + " = not allow");
			}
			
			Pattern p = Pattern.compile(cache+"$");
			Matcher m = p.matcher(domain);
			
			if( m.find() ){
				System.out.println(domain + " = allow");
			}else{
				System.out.println(domain + " = not allow");
			}
		}
	}
}
