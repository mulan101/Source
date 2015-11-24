package com.wipple.server.servlet;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * DispatcherServlet
 * 
 * @author hee-jong.lee
 *
 */
public class DispatcherServlet {
	static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	
	private String pattern = ".*\\.do";
	
	private Map<String, String> servletMapper = new HashMap<String, String>();
	
//	private ConcurrentHashMap<String, SimpleServlet> servlets = new  ConcurrentHashMap<String, SimpleServlet>();
	
	
	public void setUrlPattern(String pattern){
		this.pattern = pattern;
	}
	
	public void put(String url, String simpleServlet) throws  Exception {
	
		Pattern p = Pattern.compile(pattern);
		
		Matcher m = p.matcher(url);

		if( url == null ){
			throw new Exception("the url is null.");
		}
		if( simpleServlet == null ){
			throw new Exception("the simpleServlet is null.");
		}
		if(m.matches()){
			servletMapper.put(url, simpleServlet);
		}
	}
	
	public SimpleServlet getSimpleServlet(String url) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		String className = this.servletMapper.get(url);
		
		Class<?> clazz = Class.forName(className);
		
		
		// TODO : I will change to lazy loading
		
		SimpleServlet servlet = (SimpleServlet) clazz.newInstance();
		
		return servlet;
	}
	
	public void process(String url, String queryString) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		String className = servletMapper.get(url);
		Class<?> clazz = Class.forName(className);
		SimpleServlet servlet = (SimpleServlet) clazz.newInstance();
		
		HttpSimpleServletRequest   request = new HttpSimpleServletRequest(url);
		request.setParseQueryString(queryString);
		
		HttpSimpleServletResponse response = new HttpSimpleServletResponse();
		
		servlet.handle(request, response);
	}
	
	public Map<String, String> parseQueryString(String queryString){
		return null;
	}


	public static void main(String[] args) {
		
		DispatcherServlet servlet = new DispatcherServlet();
		try {
			servlet.put("Hello.do", null);
			servlet.put("aaa.d", null);
			servlet.put("dddd.do", null);
			servlet.put("bbbb.do", null);
			servlet.put("bbbb.dd", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
