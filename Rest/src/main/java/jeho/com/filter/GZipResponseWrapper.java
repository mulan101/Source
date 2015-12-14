package jeho.com.filter;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GZipResponseWrapper extends HttpServletResponseWrapper {

	private GZipOutputStream gzipOutputStream = null;

	public GZipResponseWrapper(HttpServletResponse response) throws IOException {
		super(response);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if (this.gzipOutputStream == null) {
			this.gzipOutputStream = new GZipOutputStream(getResponse().getOutputStream());
		}
		return this.gzipOutputStream;
	}
	
	public void close() throws IOException {
		if (this.gzipOutputStream != null) {
			this.gzipOutputStream.close();
		}
	}
}