package jeho.com.filter;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class GZipRequestWrapper extends HttpServletRequestWrapper {
	private GZipInputStream gzipInputStream = null;
	
	public GZipRequestWrapper(HttpServletRequest httpRequest) throws IOException{
		super(httpRequest);	
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		if(this.gzipInputStream == null) {
			this.gzipInputStream = new GZipInputStream(getRequest().getInputStream());
		}
		return this.gzipInputStream;
	}
	
	public void close() throws IOException{
		if(gzipInputStream != null) {
			gzipInputStream.close();
		}
	}
	
}