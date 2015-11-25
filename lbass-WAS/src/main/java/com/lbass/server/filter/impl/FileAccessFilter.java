package com.lbass.server.filter.impl;

import com.lbass.exception.FilterException;
import com.lbass.http.HttpRequest;
import com.lbass.server.filter.LbassFilter;

public class FileAccessFilter implements LbassFilter {

	@Override
	public void doFileter(HttpRequest request) throws FilterException {
		String path = request.getPath();
		if(path.indexOf("..")  != -1) {
			throw new FilterException("잘못된 경로입니다.");
		}
	}

}
