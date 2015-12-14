package jeho.com.filter;  
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import javax.servlet.ServletInputStream; 
 
public class GZipInputStream extends ServletInputStream {
	
	private GZIPInputStream gzipInputStream = null;
	
	public GZipInputStream(InputStream inputStream) throws IOException{
		this.gzipInputStream = new GZIPInputStream(inputStream);
	}
	
	@Override
	public int read() throws IOException {
		return (gzipInputStream != null)? gzipInputStream.read() : null;
	}

	@Override
	public int read(byte[] b) throws IOException {
		return (gzipInputStream != null)? gzipInputStream.read(b) : null;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		return (gzipInputStream != null)? gzipInputStream.read(b, off, len) : null;
	}

	@Override
	public void close() throws IOException {
		if(gzipInputStream != null) {
			gzipInputStream.close();
		}
	}	
}