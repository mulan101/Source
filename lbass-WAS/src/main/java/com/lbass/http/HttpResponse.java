package com.lbass.http;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.Socket;

//header & body의 컨테이너
public class HttpResponse {
	
	private Socket connection;
	private Writer out;
	private BufferedOutputStream raw;
	private Header header;
	private byte[] body;
	
	public HttpResponse(Header header, byte[] body, Socket connection) throws IOException {
		this.header = header;
		this.body = body;
		this.connection = connection;
        raw = new BufferedOutputStream(connection.getOutputStream());
        out = new OutputStreamWriter(raw);
	}
	
	public HttpResponse(Socket connection) throws IOException {
		this.connection = connection;
        raw = new BufferedOutputStream(connection.getOutputStream());
        out = new OutputStreamWriter(raw);
	}

	public byte[] getHeader() throws UnsupportedEncodingException {
		return header.getBytes();
	}

	public byte[] getBody() {
		if(body == null)
			return "".getBytes();
		
		return body;
	}
	
	public OutputStream getOutputStream() {
		return raw;
	}
		
	public Writer getWriter() {
		return out;
	}
}
