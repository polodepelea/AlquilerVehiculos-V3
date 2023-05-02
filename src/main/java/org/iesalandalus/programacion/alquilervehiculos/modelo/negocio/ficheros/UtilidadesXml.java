package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class UtilidadesXml {

	private UtilidadesXml() {
	}

	public static DocumentBuilder crearConstructorDocumentoXml() throws ParserConfigurationException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// to be compliant, completely disable DOCTYPE declaration:
		dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
		// or completely disable external entities declarations:
		dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
		dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
		// or prohibit the use of all protocols by external entities:
		dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
		dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
		// or disable entity expansion but keep in mind that this doesn't prevent
		// fetching external entities
		// and this solution is not correct for OpenJDK < 13 due to a bug:
		// https://bugs.openjdk.java.net/browse/JDK-8206132
		dbf.setExpandEntityReferences(false);

		return dbf.newDocumentBuilder();
	}

	public static void escribirXmlAFichero(Document documento, File salida)
			throws TransformerFactoryConfigurationError, TransformerException {

		TransformerFactory factory = TransformerFactory.newInstance();
		// to be compliant, prohibit the use of all protocols by external entities:
		factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
		factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");

		Transformer transformer = factory.newTransformer();

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		StreamResult result = new StreamResult(salida);

		DOMSource source = new DOMSource(documento);

		transformer.transform(source, result);

	}

	public static Document leerXmlDeFichero(File ficheroXml)
			throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilder db = crearConstructorDocumentoXml();
		return db.parse(ficheroXml);

	}

}
