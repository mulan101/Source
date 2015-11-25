package com.lbass.server.filter.impl;

import com.lbass.exception.FilterException;
import com.lbass.http.HttpRequest;
import com.lbass.server.filter.LbassFilter;

public class ExtensionCheckFilter implements LbassFilter {

	@Override
	public void doFileter(HttpRequest request) throws FilterException {
		String path = request.getPath();
		if(path.indexOf(".exe")  != -1) {
			throw new FilterException("잘못된 파일 요청입니다.");
		}
	}

}
