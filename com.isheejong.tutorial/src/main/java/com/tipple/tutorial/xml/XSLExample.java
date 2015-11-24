package com.tipple.tutorial.xml;

import java.io.FileOutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

public class XSLExample {

	public static void main(String[] args) {
		try {

			TransformerFactory tFactory = TransformerFactory.newInstance();

			Transformer transformer = tFactory
					.newTransformer(new javax.xml.transform.stream.StreamSource(
							"howto.xsl"));
			transformer.transform(new javax.xml.transform.stream.StreamSource(
					"howto.xml"), new javax.xml.transform.stream.StreamResult(
					new FileOutputStream("howto.html")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
