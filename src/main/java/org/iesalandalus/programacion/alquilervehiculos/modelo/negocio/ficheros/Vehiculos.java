package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;


import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Vehiculos implements IVehiculos {

	private static final File FICHERO_VEHICULOS = new File(
			String.format("%s%s%s", "datos", File.separator, "vehiculos.xml"));
	private static final String RAIZ = "vehiculos";
	private static final String VEHICULO = "vehiculo";
	private static final String MARCA = "marca";
	private static final String MODELO = "modelo";
	private static final String MATRICULA = "matricula";
	private static final String CILINDRADA = "cilindrada";
	private static final String PLAZAS = "plazas";
	private static final String PMA = "pma";
	private static final String TIPO = "tipo";
	private static final String TURISMO = "turismo";
	private static final String AUTOBUS = "autobus";
	private static final String FURGONETA = "furgoneta";

	private List<Vehiculo> coleccionVehiculos;
	private static Vehiculos instancia;

	private Vehiculos() {
		this.coleccionVehiculos = new ArrayList<>();
	}

	static Vehiculos getInstancia() {
		if (instancia == null) {
			instancia = new Vehiculos();
		}

		return instancia;

	}

	public void comenzar() {
		try {
			leerDom(UtilidadesXml.leerXmlDeFichero(FICHERO_VEHICULOS));
		} catch (OperationNotSupportedException | ParserConfigurationException | SAXException | IOException e) {
			System.out.println(e.getMessage());
		}

	}

	private void leerDom(Document documentoXml) throws OperationNotSupportedException {
		if (documentoXml == null) {
			throw new NullPointerException("El documento no puede ser nulo");
		}

		NodeList listaNodos = documentoXml.getElementsByTagName(VEHICULO);

		for (int i = 0; i < listaNodos.getLength(); i++) {
			Node nodo = listaNodos.item(i);
			if (nodo.getNodeType() == Node.ELEMENT_NODE) {
				Element vehiculo = (Element) nodo;
				insertar(getVehiculo(vehiculo));

			}
		}

	}

	private Vehiculo getVehiculo(Element elemento) {
		if (elemento == null) {
			throw new NullPointerException("El elemento no puede ser nulo");
		}

		Vehiculo vehiculo = null;
		String marca = elemento.getAttribute(MARCA);
		String modelo = elemento.getAttribute(MODELO);
		String matricula = elemento.getAttribute(MATRICULA);

		switch (elemento.getAttribute(TIPO)) {
		case AUTOBUS:
			vehiculo = new Autobus(marca, modelo, Integer.parseInt(elemento.getAttribute(PLAZAS)), matricula);
			break;
		case FURGONETA:
			vehiculo = new Furgoneta(marca, modelo, Integer.parseInt(elemento.getAttribute(PMA)),
					Integer.parseInt(elemento.getAttribute(PLAZAS)), matricula);
			break;
		case TURISMO:
			vehiculo = new Turismo(marca, modelo, Integer.parseInt(elemento.getAttribute(CILINDRADA)), matricula);
			break;
		default:
			System.out.println("No hay mas casos");
			break;
		}
		return vehiculo;
	}

	public void terminar() {

		try {
			UtilidadesXml.escribirXmlAFichero(crearDom(), FICHERO_VEHICULOS);
		} catch (TransformerFactoryConfigurationError | TransformerException | ParserConfigurationException e) {
			System.out.println(e.getMessage());
		}

	}

	private Document crearDom() throws ParserConfigurationException {

		DocumentBuilder docBuilder = UtilidadesXml.crearConstructorDocumentoXml();
		Document doc = docBuilder.newDocument();

		Element rootElement = doc.createElement(RAIZ);
		doc.appendChild(rootElement);

		for (int i = 0; i < coleccionVehiculos.size(); i++) {
			rootElement.appendChild(getElemento(doc, coleccionVehiculos.get(i)));
		}

		return doc;

	}

	private Element getElemento(Document documentoXml, Vehiculo vehiculo) {
		if (documentoXml == null) {
			throw new NullPointerException("El documento no puede ser nulo");
		}
		if(vehiculo == null) {
			throw new NullPointerException("El vehiculo no puede ser nulo");
		}
		
		Element elemento = documentoXml.createElement(VEHICULO);
		elemento.setAttribute(MARCA, vehiculo.getMarca());
		elemento.setAttribute(MATRICULA, vehiculo.getMatricula());
		elemento.setAttribute(MODELO, vehiculo.getModelo());

		if (vehiculo instanceof Autobus autobus) {
			elemento.setAttribute(PLAZAS, String.valueOf(autobus.getPlazas()));
			elemento.setAttribute(TIPO, AUTOBUS);

		} else if (vehiculo instanceof Turismo turismo) {
			elemento.setAttribute(CILINDRADA, String.valueOf(turismo.getCilindrada()));
			elemento.setAttribute(TIPO, TURISMO);

		} else if (vehiculo instanceof Furgoneta furgoneta) {

			elemento.setAttribute(PMA, String.valueOf(furgoneta.getPma()));
			elemento.setAttribute(PLAZAS, String.valueOf(furgoneta.getPlazas()));
			elemento.setAttribute(TIPO, FURGONETA);

		}

		return elemento;

	}

	@Override
	public List<Vehiculo> get() {
		return coleccionVehiculos.stream().toList();
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un vehículo nulo.");
		}
		if (coleccionVehiculos.contains(vehiculo)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un vehículo con esa matrícula.");
		}
		coleccionVehiculos.add(vehiculo);

	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		Vehiculo copia = null;

		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un vehículo nulo.");
		}
		int busqueda = coleccionVehiculos.indexOf(vehiculo);
		if (busqueda != -1) {
			copia = coleccionVehiculos.get(busqueda);
		}
		return copia;

	}

	@Override
	public void borrar(Vehiculo turismo) throws OperationNotSupportedException {
		if (turismo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un vehículo nulo.");
		}
		if (!coleccionVehiculos.contains(turismo)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún vehículo con esa matrícula.");
		}

		coleccionVehiculos.remove(turismo);

	}

}
