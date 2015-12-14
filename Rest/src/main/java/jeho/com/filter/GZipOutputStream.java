package jeho.com.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;

public class GZipOutputStream extends ServletOutputStream {
	
	private GZIPOutputStream gzipOutputStream = null;

	public GZipOutputStream(OutputStream output) throws IOException {
		this.gzipOutputStream = new GZIPOutputStream(output);
	}

	@Override
	public void close() throws IOException {
		if(gzipOutputStream != null) {
			this.gzipOutputStream.close();
		}
	}

	@Override
	public void flush() throws IOException {
		if(gzipOutputStream != null) {
			this.gzipOutputStream.flush();
		}
	}

	@Override
	public void write(byte b[]) throws IOException {
		if(gzipOutputStream != null) {
			this.gzipOutputStream.write(b);
		}
	}

	@Override
	public void write(byte b[], int off, int len) throws IOException {
		if(gzipOutputStream != null) {
			this.gzipOutputStream.write(b, off, len);
		}
	}

	@Override
	public void write(int b) throws IOException {
		if(gzipOutputStream != null) {
			this.gzipOutputStream.write(b);
		}
	}
}