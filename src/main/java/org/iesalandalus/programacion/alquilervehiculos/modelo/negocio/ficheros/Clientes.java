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

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Clientes implements IClientes {

	private static final File FICHERO_CLIENTES = new File(
			String.format("%s%s%s", "datos", File.separator, "clientes.xml"));
	private static final String RAIZ = "clientes";
	private static final String CLIENTE = "cliente";
	private static final String NOMBRE = "nombre";
	private static final String DNI = "dni";
	private static final String TELEFONO = "telefono";

	private List<Cliente> coleccionClientes;
	private static Clientes instancia;

	private Clientes() {
		this.coleccionClientes = new ArrayList<>();

	}

	static Clientes getInstancia() {

		if (instancia == null) {
			instancia = new Clientes();
		}

		return instancia;

	}

	public void comenzar() {

		try {
			leerDom(UtilidadesXml.leerXmlDeFichero(FICHERO_CLIENTES));
		} catch (OperationNotSupportedException | ParserConfigurationException | SAXException | IOException e) {
			System.out.println(e.getMessage());
		}

	}

	private void leerDom(Document documentoXml) throws OperationNotSupportedException {
		if (documentoXml == null) {
			throw new NullPointerException("El documento no puede ser nulo");
		}

		NodeList listaNodos = documentoXml.getElementsByTagName(CLIENTE);

		for (int i = 0; i < listaNodos.getLength(); i++) {

			Node nodo = listaNodos.item(i);
			if (nodo.getNodeType() == Node.ELEMENT_NODE) {
				Element cliente = (Element) nodo;
				insertar(getCliente(cliente));

			}
		}

	}

	private Cliente getCliente(Element elemento) {
		if (elemento == null) {
			throw new NullPointerException("El elemento no puede ser nulo");
		}

		return new Cliente(elemento.getAttribute(NOMBRE), elemento.getAttribute(DNI), elemento.getAttribute(TELEFONO));

	}

	public void terminar() {

		try {
			UtilidadesXml.escribirXmlAFichero(crearDom(), FICHERO_CLIENTES);
		} catch (TransformerFactoryConfigurationError | TransformerException | ParserConfigurationException e) {
			System.out.println(e.getMessage());
		}

	}

	private Document crearDom() throws ParserConfigurationException {

		DocumentBuilder docBuilder = UtilidadesXml.crearConstructorDocumentoXml();
		Document doc = docBuilder.newDocument();

		Element rootElement = doc.createElement(RAIZ);
		doc.appendChild(rootElement);

		for (int i = 0; i < coleccionClientes.size(); i++) {
			rootElement.appendChild(getElemento(doc, coleccionClientes.get(i)));
		}

		return doc;

	}

	private Element getElemento(Document documentoXml, Cliente cliente) {
		if (documentoXml == null) {
			throw new NullPointerException("El documento no puede ser nulo");
		}
		if (cliente == null) {
			throw new NullPointerException("El cliente no puede ser nulo");
		}

		Element elemento = documentoXml.createElement(CLIENTE);
		elemento.setAttribute(DNI, cliente.getDni());
		elemento.setAttribute(NOMBRE, cliente.getNombre());
		elemento.setAttribute(TELEFONO, cliente.getTelefono());

		return elemento;

	}

	@Override
	public List<Cliente> get() {
		return coleccionClientes.stream().toList();
	}

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		}
		if (coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");
		}
		coleccionClientes.add(cliente);

	}

	@Override
	public Cliente buscar(Cliente cliente) {
		Cliente copia = null;

		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		}
		int busqueda = coleccionClientes.indexOf(cliente);
		if (busqueda != -1) {
			copia = coleccionClientes.get(busqueda);
		}
		return copia;

	}

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}
		if (!coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}

		coleccionClientes.remove(cliente);

	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");
		}

		if (!coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}

		Cliente clienteEncontrado = buscar(cliente);

		if (nombre != null && telefono != null) {
			clienteEncontrado.setNombre(nombre);
			clienteEncontrado.setTelefono(telefono);
		} else {

			if (telefono != null) {
				clienteEncontrado.setTelefono(telefono);
			}

			if (nombre != null) {
				clienteEncontrado.setNombre(nombre);
			}

		}

	}

}
