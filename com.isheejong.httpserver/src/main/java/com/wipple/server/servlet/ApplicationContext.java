package com.wipple.server.servlet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;

// bean manager
public enum ApplicationContext {
	INSTANCE;
	
	private String domainName = ""; 
	 
	Map<String, DispatcherServlet> dispatcherServlet = new HashMap<String, DispatcherServlet>();
	
	public String getDomainName(){
		return this.domainName;
	}
	
	public void initialize(Object object){
		JSONObject url = (JSONObject) object.get("urls");
		
		this.domainName = (String) object.get("hostname");
		
		System.out.println(object.get("root"));
		
		Iterator<?> it = url.keySet().iterator();
		while(it.hasNext()){
			String key = (String) it.next();
			JSONObject servletInfo = (JSONObject) url.get(key);
			
			System.out.println(servletInfo.get("servlet"));
			System.out.println(servletInfo.get("view"));
		}
		
		System.out.println(url.get("root"));
	}
	
	DispatcherServlet getDispatcherServlet(String contextRoot){
		return this.dispatcherServlet.get(contextRoot);
	}
}
