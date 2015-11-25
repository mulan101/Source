package com.lbass.http;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequest{
	private static final Logger log = LoggerFactory.getLogger(HttpRequest.class);
	
	private HttpMessageBean messageBean;
	private Socket connection;

	public HttpRequest(Socket connection) {
		this.connection = connection;
		messageBean = new HttpMessageBean();
	}

	public String getPath() {
		return messageBean.getPath();
	}	

	public String getHostPath() {
		return messageBean.getHostPath();
	}
	
	public String getContentType() {
		return messageBean.getContentType();
	}
	
	public String getParameter(String name) {
		String result = "null";
		if(messageBean.getParams() != null) {
			result = messageBean.getParams().get(name);
		}
		return result;
	}

	public void init() throws IOException {
		try {
			InputStreamReader in = new InputStreamReader(new BufferedInputStream(connection.getInputStream()), "UTF-8");
			BufferedReader inputReader = new BufferedReader(in);
			String readMessage = inputReader.readLine();
			log.debug("readMessage : " + readMessage);
			
			if(readMessage != null && !"".equals(readMessage)) {
				messageBean.setUrlMessage(readMessage);
				readMessage = inputReader.readLine();
				messageBean.setHeaderMessage(readMessage);
				messageBean.parseUrl();
				
				
				char[] body = new char[messageBean.getContentLength()];
				inputReader.read(body, 0, messageBean.getContentLength());
				String bodyText = String.copyValueOf(body);
				messageBean.setBody(bodyText);
				
				messageBean.parseParameter();				
			} else {
				throw new IOException("can not read message");
			}
			
		} catch (IOException e) {
			log.error("wrong header!", e);
			throw e;
		}
		
	}
}