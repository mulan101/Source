package jeho.com.filter;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class GZipRequestWrapper extends HttpServletRequestWrapper {
	private byte[] bytes;
	private GZIPInputStream gzipInputStream;
	private ByteArrayOutputStream outStream;
	
	public GZipRequestWrapper(HttpServletRequest httpRequest) throws IOException{
		super(httpRequest);
		
		byte[] requestBytes = new byte[1024];
		try {
			httpRequest.getInputStream().read(requestBytes);
			if (requestBytes != null && requestBytes.length > -1) {
				gzipInputStream = new GZIPInputStream(
						new BufferedInputStream(new ByteArrayInputStream(requestBytes)));
				outStream = new ByteArrayOutputStream();

				int size = 0;
				byte[] buffer = new byte[1024];
				while ((size = gzipInputStream.read(buffer)) > 0) {
					outStream.write(buffer, 0, size);
				}
				this.bytes = outStream.toByteArray();
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if(gzipInputStream != null) {
				gzipInputStream.close();
			}			
			if(outStream != null) {
				outStream.close();
			}
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