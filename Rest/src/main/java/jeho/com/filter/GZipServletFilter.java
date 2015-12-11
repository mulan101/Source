package jeho.com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GZipServletFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		GZipRequestWrapper gzipServletRequestWrapper = null;
		
		if (acceptEncoding(httpRequest) && contentEncoding(httpRequest)) {
			gzipServletRequestWrapper = new GZipRequestWrapper(httpRequest);
			GZipResponseWrapper gzipResponseWrapper = new GZipResponseWrapper(httpResponse);			
			chain.doFilter(gzipServletRequestWrapper, gzipResponseWrapper);			
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean contentEncoding(HttpServletRequest httpRequest) {
		String contentEncoding = httpRequest.getHeader("Content-Encoding");
		return contentEncoding != null && contentEncoding.contains("gzip");
	}
	
	private boolean acceptEncoding(HttpServletRequest httpRequest) {
		String acceptEncoding = httpRequest.getHeader("Content-Encoding");
		return acceptEncoding != null && acceptEncoding.contains("gzip");
	}
}