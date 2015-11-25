package com.lbass.server.handler;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lbass.config.AppConfigContainer;
import com.lbass.config.HostDataBean;
import com.lbass.config.VirtualHostDatBean;
import com.lbass.exception.DispacherException;
import com.lbass.exception.FaviconException;
import com.lbass.http.HttpRequest;
import com.lbass.http.HttpResponse;
import com.lbass.server.dispatcher.Dispatcher;
import com.lbass.server.dispatcher.impl.RequestDispatcher;
import com.lbass.servlet.SimpleServlet;

public class RequestHandler implements Runnable {
	
	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);
	
	private Socket connection;

	public RequestHandler(Socket connectionSocket) {
		this.connection = connectionSocket;
	}

	public void run() {
		try {
			
			HttpRequest request = new HttpRequest(connection);
			request.init();
			String clientHost = request.getHostPath();
			HostDataBean hostData = AppConfigContainer.getConfigData().getHostMap(clientHost);
			
			String hostName = connection.getLocalAddress().getHostName();
			log.debug("host name : " + hostName);
			log.debug("client ip : " + connection.getRemoteSocketAddress());
			
			if(request.getPath().indexOf("favicon.ico") != -1) {
				throw new FaviconException("PASS");
			}
			try {
				Dispatcher dispacher = getDispatcher(hostName, hostData,"UTF-8", connection);
				if (dispacher != null) {
					dispacher.execute(request);
				}
			} catch(Exception e) {
				throw new DispacherException(e.getMessage(), e);
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} catch (DispacherException e) {
			log.error("Dispatcher Exception", e);
		} catch (FaviconException e){
			log.trace(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
    }

	private Dispatcher getDispatcher(String hostName, HostDataBean hostData, String string, Socket connection) throws Exception {
		VirtualHostDatBean virtualHostData = AppConfigContainer.getConfigData().getVirtualHostsMap().get(hostName);
		if(virtualHostData == null) {
			virtualHostData = AppConfigContainer.getConfigData().getVirtualHostsMap().get("default");
		}
		Class<?> clas = Class.forName(virtualHostData.getClassName());
		Constructor<?> constructor = clas.getConstructor(HostDataBean.class, String.class, Socket.class);
		Dispatcher dispacher = (Dispatcher)constructor.newInstance(hostData, "UTF-8", connection);
		return dispacher;
	}
	

}