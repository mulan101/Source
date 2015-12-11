package jeho.com.filter;  
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream; 
 
public class GZipInputStream extends ServletInputStream {
	
	private InputStream inputStream;
	
	public GZipInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	@Override
	public int read() throws IOException {
		return inputStream.read();
	} 
}