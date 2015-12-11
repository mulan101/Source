package jeho.com.filter;  
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream; 
 
public class GZipOutputStream extends ServletOutputStream {
	
	private OutputStream ouputStream;
	
	public GZipOutputStream(OutputStream ouputStream) {
		this.ouputStream = ouputStream;
	}

	@Override
	public void write(int b) throws IOException {	
		ouputStream.write(b);
	}	
	
}