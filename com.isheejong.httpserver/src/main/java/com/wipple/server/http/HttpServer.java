package com.wipple.server.http;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.simple.parser.ParseException;

import com.wipple.server.servlet.ApplicationContext;

public class HttpServer {
	static final Logger logger = LoggerFactory.getLogger(HttpServer.class);
	
	private ExecutorService pool = Executors.newFixedThreadPool(20);
	
	private int port = 80;
	
	private String hostname = "localhost";
	
	public String getHostname(){
		return this.hostname;
	}
	
	public int getPort(){
		return this.port;
	}
	public void init(String file, String hostname) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		Reader reader = new InputStreamReader(getClass().getResourceAsStream(file) );
		JSONObject obj = (JSONObject) parser.parse(reader);
		
		JSONArray hosts = (JSONArray) obj.get("host");
		
		
		for (int index = 0; index < hosts.size(); index ++ ){
			JSONObject object = (JSONObject) hosts.get(index);
			
			if( hostname.equals(object.get("hostname"))){
				this.hostname = hostname;
				this.port = Integer.valueOf((String) object.get("port"));
				ApplicationContext.INSTANCE.initialize(object);
			}			
		}
		
		reader.close();
	}
	
	public void start(){
		logger.debug("* starting server");
		try {
			ServerSocket server = new ServerSocket(80);
			
			
			while(true){
				Socket client = server.accept();
				HttpTask task = new HttpTask(client);
				pool.submit(task);
				 
			}
		}catch(Exception e){
			
		}
	}
	public static void main(String[] args) {
		HttpServer httpServer = new HttpServer();
		
		
		try {
			httpServer.init("/web.json", "localhost");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpServer.start();
		
	}
}
