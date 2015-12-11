package jeho.com.util;

import java.io.*;
import java.util.zip.*;

public class GZip {
	private static final int BUFFER_SIZE = 1024;
	
	public static byte[] compress(String value) throws IOException {
		byte[] bReturn = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		GZIPOutputStream gzipOutStream = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			gzipOutStream = new GZIPOutputStream(new BufferedOutputStream(byteArrayOutputStream));
			gzipOutStream.write(value.getBytes());
			bReturn = byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			throw e;
		} finally {
			if (gzipOutStream != null) {
				gzipOutStream.close();
			}
			if (byteArrayOutputStream != null) {
				byteArrayOutputStream.close();
			}
		}
		return bReturn;
	}

	public static byte[] decompress(byte[] value) throws IOException {
		byte[] bReturn = null;
		ByteArrayOutputStream outStream = null;
		GZIPInputStream gzipInStream = null;
		try {
			outStream = new ByteArrayOutputStream();
			gzipInStream = new GZIPInputStream(new BufferedInputStream(new ByteArrayInputStream(value)));
			int size = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((size = gzipInStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, size);
			}
			bReturn = outStream.toByteArray();
		} catch (IOException e) {
			throw e;
		} finally {
			if (gzipInStream != null) {
				gzipInStream.close();
			}
			if (outStream != null) {
				outStream.close();
			}
		}
		return bReturn;
	}

}