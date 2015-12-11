package jeho.com.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import jeho.com.util.GZip;

public class GZipRequestWrapper extends HttpServletRequestWrapper {
	private byte[] bytes;	
	
	public GZipRequestWrapper(HttpServletRequest httpRequest) throws IOException{
		super(httpRequest);
		
		byte[] requestBytes = new byte[1024];
		httpRequest.getInputStream().read(requestBytes);
		if (requestBytes != null && requestBytes.length > -1) {
			bytes = GZip.decompress(requestBytes);
		}
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
		return new GZipInputStream(byteArrayInputStream);
	}
}