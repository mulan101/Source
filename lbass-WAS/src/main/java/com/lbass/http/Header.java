package com.lbass.http;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Header {
	
	private static final Map<String, String> status = new HashMap<String, String>();
	static{
		status.put("200", "Ok");
		status.put("302", "Found");
		status.put("400", "Bad Request");
		status.put("403", "Forbidden");
		status.put("404", "Not Found");
		status.put("500", "Internal Server Error");
	}

	private final String statusCode;
	private final String contentType;
	private final String encoding;
	private Integer contentLength;
	
	public Header(String statusCode, String contentType, int contentLength, String encoding){
		this.statusCode = statusCode;
		this.contentType = contentType;
		this.contentLength = contentLength;
		this.encoding = encoding;
	}
	
	public Header(String statusCode, String contentType, String encoding) {
		this.statusCode = statusCode;
		this.contentType = contentType;
		this.encoding = encoding;
	}

	public byte[] getBytes() throws UnsupportedEncodingException{
	
		String headerString = "";
		if(statusCode != null) {
			headerString += "HTTP/1.1 " + statusCode + " "+status.get(statusCode)+" \r\n";			
		}
		if(contentType != null) {
			headerString += "Content-Type: " + contentType + ";";			
		}
		if(encoding != null) {
			headerString += "charset="+ encoding +"\r\n";			
		}
		if(contentLength != null) {
			headerString += "Content-Length: " + contentLength + "\r\n";			
		}
		
		//header 마지막
		headerString += "\r\n";

		return headerString.getBytes(encoding);
	}
}
