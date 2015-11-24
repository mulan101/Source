package com.tipple.tutorial.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxEventHandler extends DefaultHandler {

	public void startElement(String uri, String localName,
	        String qName, Attributes attributes) throws SAXException {
		
//		System.out.println(uri);
    }
	
	 public void endElement(String uri, String localName,
		        String qName) throws SAXException {
//			System.out.println(uri);

	}

	public void characters(char ch[], int start, int length)
			throws SAXException {
//		System.out.println(new String(ch));
	}
}
