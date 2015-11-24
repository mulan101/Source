package com.wipple.server.servlet;

import java.util.HashMap;
import java.util.Map;

public class HttpSimpleServletRequest {

	Map<String, String> paramMap = new HashMap<String, String>();
	
	// HttpSession.. to be continue
	
	// Cookie to b continue
	
	public void setParseQueryString(String queryString){
		
	}
	
	public HttpSimpleServletRequest(String url){
		this.url = url;
	}
	
	Map<String, String> attribute = new HashMap<String, String>();
	
	private String url = "";

	public String getParameter(String key){
		return paramMap.get(key);
	}
	
	public Map<String, String> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}

	public Map<String, String> getAttribute() {
		return attribute;
	}

	public void setAttribute(Map<String, String> attribute) {
		this.attribute = attribute;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
