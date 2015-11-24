package com.wipple.server.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class Http implements Runnable{
	private String host = "";
	private String method = "";
	private String resource = "";
	
	private String queryString = "";
	private StringBuffer body = new StringBuffer("");
	
	private Map<String, String> reqHeader = new HashMap<String, String>();
	
	private static final String[] reqHeaderKey = 
		{"Accept",
		"Accept-Charset",
		"Accept-Encoding", 
		"Accept-Language",
		"Accept-Datetime",
		"Authorization",
		"Cache-Control",
		"Connection"
		,"Cookie",
		"Content-Length",
		"Content-MD5",
		"Content-Type",
		"Date",
		"Expect", 
		"Host",
		"From",
		"If-Match",
		"If-Modified-Since",
		"If-None-Match",
		"If-Range",
		"Max-Forwards",
		"Origin",
		"Pragma",
		"Proxy-Authorization",
		"Range",
		"Referer",
		"TE",
		"User-Agent",
		"Via",
		"Warning"
		};
	
	private static final String[] resHeader = 
		{"Access-Control-Allow-Origin",
		"Accept-Patch",
		"Accept-Ranges",
		"Age",
		"Allow",
		"Cache-Control",
		"Connection",
		"Content-Language",
		"Content-Length",
		"Content-Location",
		"Content-MD5",
		"Content-Range",
		"Content-Type",
		"Date",
		"ETag",
		"Expires",
		"Last-Modified",
		"Link",
		"Location",
		"P3P",
		"Pragma",
		"Proxy-Authenticate",
		"Public-Key-Pins",
		"Refresh",
		"Retry-After",
		"Server",
		"Set-Cookie",
		"Status",
		"Strict-Transport-Security",
		"Trailer",
		"Transfer-Encoding",
		"Upgrade",
		"Vary",
		"Via",
		"Warning",
		"WWW-Authenticate",
		"X-Frame-Options"
		
		};
	
	private Socket socket = null;
	
	public Http(Socket socket){
		this.socket = socket;
	}
	
	public String getBody(){
		return this.body.toString();
	}
	
	public Map<String, String> getHeader(){
		return this.reqHeader;
	}
	
	public void preProcess(){
		try {
			long start = System.currentTimeMillis();
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			StringBuilder builder = new StringBuilder();
			
			while( reader.ready()){
				char ch = (char) reader.read();
				builder.append(ch);
			}
			
			String[] lines = builder.toString().split("\r\n"); 
			
			if( lines == null || lines.length < 1 ){
				return ;
			}
			
			if( lines[0].split("\\s+").length < 3){
				return;
			}
			
			String method   = lines[0].split("\\s")[0].trim();
			String resource = lines[0].split("\\s")[1].trim();
			int index       = 0;
			
			// 아래 로직 개선 필요. 
			while(index < lines.length && ! "".equals(lines[index] ) ) {
				for( String key : this.reqHeaderKey){
					if (lines[index].indexOf(key) >= 0 ){
						String value = lines[index].replaceAll(key + ":", "").trim();
						reqHeader.put(key, value);
						if ("Host".equals(key)) {
							this.host = value;
						}
					}
				}
				index ++;
			}

			this.method = method;
			this.resource = resource;
			
			if( "POST".equals(method) ){
				index ++;
				while(index < lines.length && ! "".equals(lines[index] ) ) {
					body.append(lines[index]);
					index ++;
				}
			}else if( "GET".equals(method)){
				String[] url = resource.split("\\?");
				
				if( url.length > 1 ){
					this.queryString = url[1];
				}
				
			}
			
			long end = System.currentTimeMillis();
			
			System.out.println(end - start + "(ms)");
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public void run() {
		preProcess();
		process();
		postProcess();
	}
	
	public abstract void process();
	
	public String getHost(){
		return this.host;
	}
	
	public String getResource(){
		return this.resource;
	}
	
	public String getMethod(){
		return this.method;
	}
	
	public String getQueryString(){
		return this.queryString;
	}
	
	public void postProcess(){
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			out.write("HTTP/1.1 200 OK" + "\r\n");
		        Date now = new Date();
		        out.write("Date: " + now + "\r\n");
		        out.write("Server: JHTTP 2.0\r\n");
		        out.write("Content-length: " + 120 + "\r\n");
		        out.write("Content-type: " + "application/html" + "\r\n\r\n");
		        out.flush();
		       
		        out.close();
		        
		        socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	

}
