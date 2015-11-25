package com.lbass.server.filter;

import com.lbass.exception.FilterException;
import com.lbass.http.HttpRequest;

public interface LbassFilter {

	public void doFileter(HttpRequest request) throws FilterException;
}
