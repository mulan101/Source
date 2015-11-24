package com.tipple.tutorial.xml;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Sax {
	
	public static void main (String argv []) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            InputStream    xmlInput  =
                new FileInputStream("sample.xml");

            SAXParser      saxParser = factory.newSAXParser();
            SaxEventHandler handler   = new SaxEventHandler();
            saxParser.parse(xmlInput, handler);

           
        } catch (Throwable err) {
            err.printStackTrace ();
        }
    }

	
}
