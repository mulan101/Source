package com.wipple.server.http;

import java.net.Socket;
import java.util.Iterator;
import java.util.Map;

import com.wipple.server.servlet.DispatcherServlet;

public class HttpTask extends Http {

	public HttpTask(Socket socket) {
		super(socket);
	}

	@Override
	public void process() {
		
		System.out.println(getHost());
		System.out.println(getMethod());
		System.out.println(getResource());
		
		Map<String, String> header = getHeader();
		
		Iterator<String> it = header.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			String value = header.get(key);
			System.out.println(key + " " + value );
		}
		
		System.out.println(getBody());
		String host = getHost();
		
		DispatcherServlet appContext = VirtualHost.INSTANCE.findAppContext(host);
		
		// http parameter
		
		// http response parameter 
		
		// view resolver
	}
	
	
}
