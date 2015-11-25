package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

import com.lbass.server.main.HttpServerMain;

import test.common.AbstractTest;

public class RequestTest extends AbstractTest {
	
	@Test
	public void request() throws Exception {
		try {
			startServer();
			Thread.sleep(3000);
			
			URL url = new URL("http://localhost:8080/root/test/test.html");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/xml;");

			assertTrue(conn.getResponseCode() == 200);
			   
			conn.disconnect();

			url = new URL("http://localhost:8080/root/test.exe");
			conn = (HttpURLConnection) url.openConnection();
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/xml;");
			
			String output;
			StringBuffer sb = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			String htmlText = sb.toString();
			if(htmlText.indexOf("ERROR 403") != -1) {
				assertTrue(true);				
			} else {
				assertTrue(false);
			}
			conn.disconnect();
			
			url = new URL("http://localhost:8080/root/notFound.html");
			conn = (HttpURLConnection) url.openConnection();
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/xml;");
			
			sb = new StringBuffer();
			br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			htmlText = sb.toString();
			if(htmlText.indexOf("ERROR 404") != -1) {
				assertTrue(true);				
			} else {
				assertTrue(false);
			}
			conn.disconnect();
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
