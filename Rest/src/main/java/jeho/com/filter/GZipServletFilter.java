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
		GZipRequestWrapper gzipRequestWrapper = null;
		GZipResponseWrapper gzipResponseWrapper = null;

		try {
			// request 및 response 모두 압축/해제 필요
			if (acceptEncoding(httpRequest) && contentEncoding(httpRequest)) {
				gzipRequestWrapper = new GZipRequestWrapper(httpRequest);
				gzipResponseWrapper = new GZipResponseWrapper(httpResponse);
				httpResponse.addHeader("Content-Encoding", "gzip");
				chain.doFilter(gzipRequestWrapper, gzipResponseWrapper);
			// response 압축 필요
			} else if (acceptEncoding(httpRequest)) {
				gzipResponseWrapper = new GZipResponseWrapper(httpResponse);
				httpResponse.addHeader("Content-Encoding", "gzip");
				chain.doFilter(request, gzipResponseWrapper);
			// request 압축해제 필요
			} else if (contentEncoding(httpRequest)) {
				gzipRequestWrapper = new GZipRequestWrapper(httpRequest);
				chain.doFilter(gzipRequestWrapper, response);
			} else {
				chain.doFilter(request, response);
			}
		} catch (IOException | ServletException e) {
			throw e;
		} finally {
			if (gzipRequestWrapper != null) {
				gzipRequestWrapper.close();
			}
			if (gzipResponseWrapper != null) {
				gzipResponseWrapper.close();
			}
		}
	}

	private boolean contentEncoding(HttpServletRequest httpRequest) {
		String contentEncoding = httpRequest.getHeader("Content-Encoding");
		return contentEncoding != null && contentEncoding.contains("gzip");
	}

	private boolean acceptEncoding(HttpServletRequest httpRequest) {
		String acceptEncoding = httpRequest.getHeader("Accept-Encoding");
		return acceptEncoding != null && acceptEncoding.contains("gzip");
	}
}