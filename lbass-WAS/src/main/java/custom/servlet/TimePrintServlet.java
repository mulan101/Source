package custom.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import com.lbass.http.HttpRequest;
import com.lbass.http.HttpResponse;
import com.lbass.servlet.SimpleServlet;

public class TimePrintServlet implements SimpleServlet {

	@Override
	public void service(HttpRequest req, HttpResponse res) {
			Writer out = res.getWriter();
	        Date now = new Date();
            try {
            	String body = new StringBuilder("<HTML>\r\n")
            			.append("<HEAD><TITLE>Hello</TITLE>\r\n").append("</HEAD>\r\n")
            			.append("<BODY>")
            			.append("<H1>HelloWorld</H1>\r\n")
            			.append("<H1>Date: " + now + "</H1>\r\n")
            			.append("</BODY></HTML>\r\n").toString();
            	out.write(body);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
