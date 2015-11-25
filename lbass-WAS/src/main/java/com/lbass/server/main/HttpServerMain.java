package com.lbass.server.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lbass.config.AppConfigContainer;
import com.lbass.config.ConfigDataBean;
import com.lbass.config.ConfigReader;
import com.lbass.server.handler.RequestHandler;

public class HttpServerMain {
	private static final Logger log = LoggerFactory.getLogger(HttpServerMain.class);
    private static final int MAX_THREADS_COUNT = 50;

    public static void main(String[] args) throws IOException {
    	//config 설정 읽기
    	//socket 서버 열기
    	ConfigReader reader = new ConfigReader();
    	ConfigDataBean configData = null;
		try {
			configData = reader.getConfigData("web.json");
			AppConfigContainer.setConfigData(configData);
		} catch (IOException e) {
			log.error("web.json 파일을 찾을 수 없습니다. jar 파일과 같은 디렉토리에 배치하시기 바랍니다.", e);
			throw e;
		}

		int port = configData.getPort();
		if(args != null && args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = configData.getPort();	
		}
    	
    	ExecutorService pool = Executors.newFixedThreadPool(MAX_THREADS_COUNT);
    		//log.info("Web Application Server started {} port.", port);

        // 클라이언트가 연결될때까지 대기한다.
    	try (ServerSocket server = new ServerSocket(port)) {
	        while (true) {
	        	try {
	        		Socket request = server.accept();	        	
	        		Runnable requestHandler = new RequestHandler(request);
	        		pool.submit(requestHandler);
	            } catch (Exception ex) {
	            	ex.printStackTrace();
	            }
	        }
    	}
    }
}