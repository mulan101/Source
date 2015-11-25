package com.lbass.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.lbass.config.HostDataBean;
 
public class ErrorPageGenerator {
	
	public static byte[] createErrorResponse(HostDataBean hostData, String statusCode, String encoding) throws IOException{
		String errorPagePath = null;
		if(hostData != null) {
			//해당하는 호스트에 따라 에러 페이지를 생성한다.
			if("400".equals(statusCode)) {
				errorPagePath = hostData.getDocbase() + hostData.getErrorPage_403() + ".html";			
			} else if("403".equals(statusCode)) {
				errorPagePath = hostData.getDocbase() + hostData.getErrorPage_403() + ".html";
			} else if("404".equals(statusCode)) {
				errorPagePath = hostData.getDocbase() + hostData.getErrorPage_404() + ".html";
			} else if("500".equals(statusCode)) {
				errorPagePath = hostData.getDocbase() + hostData.getErrorPage_500() + ".html";
			}			
			byte[] body = Files.readAllBytes(Paths.get(errorPagePath));
			
			return body;
		} else {
			//해당하는 호스트가 없을 경우
            String bodyStr = new StringBuilder("<HTML>\r\n")
            			.append("<HEAD><TITLE>File Not Found</TITLE>\r\n")
            			.append("</HEAD>\r\n")
            			.append("<BODY>")
            			.append("<H1>HTTP Error 404: File Not Found</H1>\r\n")
            			.append("</BODY></HTML>\r\n")
            			.toString();
			
			byte[] body = bodyStr.toString().getBytes();
			return body;
		}

		
	}
}