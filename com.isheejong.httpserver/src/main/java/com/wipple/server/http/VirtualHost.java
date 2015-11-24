package com.wipple.server.http;

import java.util.HashMap;
import java.util.Map;

import com.wipple.server.servlet.DispatcherServlet;


public enum VirtualHost {
	INSTANCE;
	
	public Map<String, DispatcherServlet> contextMap = new HashMap<String, DispatcherServlet>(); //(key=domainName, value=ApplicationContext)
	 
	
	static {
		
	}

	public DispatcherServlet findAppContext(String domainName){
		return contextMap.get(domainName);
	}
}
