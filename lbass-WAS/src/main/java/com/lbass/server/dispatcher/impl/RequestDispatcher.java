package com.lbass.server.dispatcher.impl;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lbass.config.HostDataBean;
import com.lbass.exception.DispacherException;
import com.lbass.exception.FilterException;
import com.lbass.exception.NotFoundPageException;
import com.lbass.exception.ServiceException;
import com.lbass.http.Header;
import com.lbass.http.HttpRequest;
import com.lbass.http.HttpResponse;
import com.lbass.server.dispatcher.Dispatcher;
import com.lbass.server.filter.LbassFilter;
import com.lbass.servlet.SimpleServlet;
import com.lbass.util.ErrorPageGenerator;

public class RequestDispatcher extends Dispatcher {
	
	private static final Logger log = LoggerFactory.getLogger(RequestDispatcher.class);

	public RequestDispatcher(HostDataBean hostData, String encoding,
			Socket connection) throws IOException {
		super(hostData, encoding, connection);
	}

	@Override
	public void execute(HttpRequest request) throws Exception {
		HttpResponse response = null;
		String contentType = request.getContentType();

		try {            
			//일치한 호스트 정보가 존재
			if(hostData != null) {
				//필터를 수행한다.
				List Filters = hostData.getFilters();
				try {
					runChainFilter(Filters, request);				
				} catch (FilterException e) {
					throw new FilterException(e.getMessage());
				}

				//servlet mapping 정보가 있는지 확인한다.
				List servlets = hostData.getServlets();
				String requestPath = request.getPath();
				String mappingClass = null;
				boolean mappingY = false;
				for(int i = 0 ; servlets != null && i < servlets.size() ; i ++) {
					Map servlet = (Map)servlets.get(i);
					//MAPPINGCLASS	
					String url= (String)servlet.get("URL");
					if(requestPath.equals(url)) {
						mappingClass = (String)servlet.get("MAPPINGCLASS");
						mappingY = true;
						break;
					}
				}
				if(mappingY) {
					response = new HttpResponse(connection);
					Header header = new Header("200", contentType, encoding);
					invokeService(mappingClass, "service", request, response);
				} else {
					try {
						byte[] body = Files.readAllBytes(Paths.get(hostData.getDocbase() + request.getPath()));
						Header header = new Header("200", contentType, body.length, encoding);
						raw.write(header.getBytes());
						raw.write(body);
						raw.flush();						
					} catch (NoSuchFileException e) {
						throw new NotFoundPageException("페이지가 없습니다.");
					}
				}
				
			} else {
				//일치하는 호스트가 없을 경우에는 404 에러
				throw new NotFoundPageException("일치하는 파일 경로가 없습니다.");
			}
		} catch (IOException | NotFoundPageException e) {
			log.error(e.getMessage(), e);
			//파일 없으면 404
			byte[] body = ErrorPageGenerator.createErrorResponse(hostData, "404", "UTF-8");
			Header header = new Header("200", contentType, body.length, encoding);
			raw.write(header.getBytes());
			raw.write(body);
			raw.flush();
		} catch (ServiceException e) {
			log.error(e.getMessage(), e);
			//필터 에러 발생 시500 에러
			byte[] body = ErrorPageGenerator.createErrorResponse(hostData, "500", "UTF-8");
			Header header = new Header("200", contentType, body.length, encoding);
			raw.write(header.getBytes());
			raw.write(body);
			raw.flush();
		}  catch (FilterException e) {
			log.error(e.getMessage(), e);
			//필터 에러 발생 시 403 에러
			byte[] body = ErrorPageGenerator.createErrorResponse(hostData, "403", "UTF-8");
			Header header = new Header("200", contentType, body.length, encoding);
			raw.write(header.getBytes());
			raw.write(body);
			raw.flush();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			byte[] body = ErrorPageGenerator.createErrorResponse(hostData, "404", "UTF-8");
			Header header = new Header("200", contentType, body.length, encoding);
			raw.write(header.getBytes());
			raw.write(body);
			raw.flush();
		}
	}
    	
}
