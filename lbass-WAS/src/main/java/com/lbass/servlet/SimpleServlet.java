package com.lbass.servlet;

import java.io.IOException;

import com.lbass.http.HttpRequest;
import com.lbass.http.HttpResponse;

public interface SimpleServlet {
	public void service(HttpRequest req, HttpResponse res);
}
