package com.lbass.server.dispatcher;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.List;

import com.lbass.config.HostDataBean;
import com.lbass.exception.FilterException;
import com.lbass.exception.ServiceException;
import com.lbass.http.HttpRequest;
import com.lbass.http.HttpResponse;
import com.lbass.server.filter.LbassFilter;
import com.lbass.servlet.SimpleServlet;

public abstract class Dispatcher {

	protected HostDataBean hostData;
	protected String encoding;
	protected Socket connection;
	protected OutputStream raw;
	protected Writer out;
	
	protected Dispatcher (HostDataBean hostData, String encoding, Socket connection) throws IOException {
		this.hostData = hostData;
		this.encoding = encoding;
		this.connection = connection;
        raw = new BufferedOutputStream(connection.getOutputStream());
        out = new OutputStreamWriter(raw);
	}
	
	protected void runChainFilter(List filters, HttpRequest request) throws FilterException {
		for(int i = 0 ; filters !=null && i < filters.size() ; i++) {
			String filter = (String)filters.get(i);
			try {
				invokeFilter(filter, "doFileter", request);				
			} catch (Exception e) {
				throw new FilterException(e.getMessage());
			}
		}
	}
	
	protected void invokeFilter(String className, String methodName, Object... obj) throws Exception {
		Class<?> clas = Class.forName(className);
		Constructor<?> constructor = clas.getConstructor();
		LbassFilter filter = (LbassFilter)constructor.newInstance();
    	
		Method[] methods = filter.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(methodName)) {
				try {
					if (methods[i].getReturnType().getName().equals("void")) {
						methods[i].invoke(filter, obj);
					}
				} catch (IllegalAccessException lae) {
					throw new FilterException(lae.getMessage());
				} catch (InvocationTargetException ite) {
					throw new FilterException(ite.getTargetException().getMessage());
				}
			}
		}
    }
    
	protected void invokeService(String className, String methodName, HttpRequest req, HttpResponse res) throws Exception {
    	try {
    		Class<?> clas = Class.forName(className);
    		Constructor<?> constructor = clas.getConstructor();
    		SimpleServlet servlet = (SimpleServlet)constructor.newInstance();
    		servlet.service(req, res);    		
    	} catch (Exception e) {
    		throw new ServiceException("can not execute service", e);
    	}
    }
	
	public abstract void execute(HttpRequest request) throws Exception;
}
