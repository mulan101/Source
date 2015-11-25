package com.tipple.tutorial.xml;
import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAXSampleParser
 *  
 *  
 * @author hee-jong.lee
 *
 */
public class SAXSParser extends DefaultHandler {

	public SAXSParser() {
	}

	public void startDocument() throws SAXException {
		super.startDocument();

		System.out.println("Start Document");
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("Start Element: " + qName);

		for (int i = 0; i < attributes.getLength(); i++) {
			System.out.println("Attribute: " + attributes.getQName(i) + "=" + attributes.getValue(i));
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("End Element: " + qName);
	}

	public void characters(char ch[], int start, int length) throws SAXException {
		System.out.println("Character: " + new String(ch, start, length));
	}

	public void endDocument() throws SAXException {
		super.endDocument();

		System.out.println("End Document");
	}

	public static void main(String[] args) {
		try {
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();

			File xmlFile = new File("sample.xml");
			parser.parse(xmlFile, new SAXSParser());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
