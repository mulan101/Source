package com.lbass.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

public class RequestUtils {

	public static Map<String, String> parseQueryString(String queryString) {
		Map<String, String> parameterMap = new HashMap();
		if (Strings.isNullOrEmpty(queryString)) {
			return Maps.newHashMap();
		}
		
		String[] tokens = queryString.split("&");
		for(int i = 0 ; tokens != null && i < tokens.length ; i++) {
			boolean put_flag = true;
			String keyValueStr = tokens[i];
			if (Strings.isNullOrEmpty(tokens[i])) {
				return null;
			}
			
			String[] keyValueSet = keyValueStr.split("=");
			if (keyValueSet.length == 2) {
				parameterMap.put(keyValueSet[0], keyValueSet[i]);				
			}
		}
		
		return parameterMap;
	}
	
	public static String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		return sd.format(date);
	}
}
