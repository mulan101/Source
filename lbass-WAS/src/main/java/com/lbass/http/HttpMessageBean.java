package com.lbass.http;

import java.net.URLConnection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lbass.util.RequestUtils;

public class HttpMessageBean {

	private int contentLength;
	private String urlHeader;
	private String url;
	private String method;
	private String path;
	private String hostPath;
	private String fileName;
	private String query;
	private String cookieString;
	private String contentType;
	private String body;
	
	public String getHostPath() {
		return hostPath;
	}
	private Map<String, String> params;
	
	private String headerMessage;
	private String urlMessage;
	
	public int getContentLength() {
		return contentLength;
	}
	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}
	public String getUrlHeader() {
		return urlHeader;
	}
	public void setUrlHeader(String urlHeader) {
		this.urlHeader = urlHeader;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getPath() {
		return path;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getCookieString() {
		return cookieString;
	}
	public void setCookieString(String cookieString) {
		this.cookieString = cookieString;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public void setHeaderMessage(String headerMessage) {
		this.headerMessage = headerMessage;
	}
	public void setUrlMessage(String urlMessage) {
		this.urlMessage = urlMessage;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public void parseUrl() {
		if(urlMessage != null) {
			String version = "";
			String[] token = urlMessage.split("\\s+");
			if(token.length < 2) {
				//@@toDo:
			}
			
			method = token[0];
			url = token[1];
            if (token.length > 2) {
            	version = token[2];
            }
            contentType = URLConnection.getFileNameMap().getContentTypeFor(url);
			
			String tempPath = null;
			int queryIndex = url.indexOf("?");
			if(queryIndex != -1) {
				query = url.substring(queryIndex, url.length());
				tempPath = url.substring(0, queryIndex);
			} else {
				query = "";
				tempPath = url;
			}
			
			int index = tempPath.lastIndexOf("/");
			fileName = tempPath.substring(index, tempPath.length());
			
			if(tempPath != null && !"".equals(tempPath)) {
				String[] paths = tempPath.split("/");
				if(paths.length > 2) {
					hostPath = paths[1];
					path = tempPath.substring(1 + hostPath.length(), tempPath.length());				
				} else {
					hostPath = "";
					path = tempPath;
				}
			}
			//@@toDo: 에러 핸들
		}	
	}

	public void parseParameter() {
		if(method.equals("GET")){
			if(query.length() > 0) {
				params = RequestUtils.parseQueryString(query);				
			}
		} else if(method.equals("POST")) {
			params = RequestUtils.parseQueryString(body);
		}
		
	}
}
