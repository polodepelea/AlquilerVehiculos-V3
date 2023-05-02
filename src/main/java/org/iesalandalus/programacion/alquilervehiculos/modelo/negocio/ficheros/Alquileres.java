package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Alquileres implements IAlquileres {

	private static final File FICHERO_ALQUILERES = new File(
			String.format("%s%s%s", "datos", File.separator, "alquileres.xml"));
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final String RAIZ = "alquileres";
	private static final String ALQUILER = "alquiler";
	private static final String CLIENTE = "cliente";
	private static final String VEHICULO = "vehiculo";
	private static final String FECHA_ALQUILER = "fechaAlquiler";
	private static final String FECHA_DEVOLUCION = "fechaDevolucion";

	private List<Alquiler> coleccionAlquileres;
	private static Alquileres instancia;

	private Alquileres() {
		this.coleccionAlquileres = new ArrayList<>();
	}

	static Alquileres getInstancia() {
		if (instancia == null) {
			instancia = new Alquileres();
		}

		return instancia;

	}

	public void comenzar() {

		try {
			leerDom(UtilidadesXml.leerXmlDeFichero(FICHERO_ALQUILERES));
		} catch (OperationNotSupportedException | ParserConfigurationException | SAXException | IOException e) {
			System.out.println(e.getMessage());
		}

	}

	private void leerDom(Document documentoXml) throws OperationNotSupportedException {
		if (documentoXml == null) {
			throw new NullPointerException("El documento no puede ser nulo");
		}

		NodeList listaNodos = documentoXml.getElementsByTagName(ALQUILER);

		for (int i = 0; i < listaNodos.getLength(); i++) {

			Node nodo = listaNodos.item(i);
			if (nodo.getNodeType() == Node.ELEMENT_NODE) {
				Element alquiler = (Element) nodo;
				insertar(getAlquiler(alquiler));

			}
		}

	}

	private Alquiler getAlquiler(Element elemento) throws OperationNotSupportedException {
		if (elemento == null) {
			throw new NullPointerException("El elemento no puede ser nulo");

		}
		Cliente cliente = Cliente.getClienteConDni(elemento.getAttribute(CLIENTE));
		Vehiculo vehiculo = Vehiculo.getVehiculoConMatricula(elemento.getAttribute(VEHICULO));
		LocalDate fecha = LocalDate.parse(elemento.getAttribute(FECHA_ALQUILER), FORMATO_FECHA);

		cliente = Clientes.getInstancia().buscar(cliente);

		if (cliente == null) {
			throw new NullPointerException("La instacia del cliente es nula");
		}

		vehiculo = Vehiculos.getInstancia().buscar(vehiculo);

		if (vehiculo == null) {
			throw new NullPointerException("La instacia del vehiculo es nula");

		}

		Alquiler alquiler = new Alquiler(cliente, vehiculo, fecha);

		if (!elemento.getAttribute(FECHA_DEVOLUCION).isBlank()) {
			alquiler.devolver(LocalDate.parse(elemento.getAttribute(FECHA_DEVOLUCION), FORMATO_FECHA));
		}

		return alquiler;

	}

	public void terminar() {

		try {
			UtilidadesXml.escribirXmlAFichero(crearDom(), FICHERO_ALQUILERES);
		} catch (TransformerFactoryConfigurationError | TransformerException | ParserConfigurationException e) {
			System.out.println(e.getMessage());
		}

	}

	private Document crearDom() throws ParserConfigurationException {

		DocumentBuilder docBuilder = UtilidadesXml.crearConstructorDocumentoXml();
		Document doc = docBuilder.newDocument();

		Element rootElement = doc.createElement(RAIZ);
		doc.appendChild(rootElement);

		for (int i = 0; i < coleccionAlquileres.size(); i++) {
			rootElement.appendChild(getElemento(doc, coleccionAlquileres.get(i)));
		}

		return doc;

	}

	private Element getElemento(Document documentoXml, Alquiler alquiler) {
		if (documentoXml == null) {
			throw new NullPointerException("El documento no puede ser nulo");
		}
		if(alquiler == null) {
			throw new NullPointerException("El alquiler no puede ser nulo");
		}
		
		Element elemento = documentoXml.createElement(ALQUILER);

		elemento.setAttribute(CLIENTE, String.valueOf(alquiler.getCliente().getDni()));
		elemento.setAttribute(FECHA_ALQUILER, String.valueOf(alquiler.getFechaAlquiler().format(FORMATO_FECHA)));
		elemento.setAttribute(VEHICULO, String.valueOf(alquiler.getVehiculo().getMatricula()));

		if (alquiler.getFechaDevolucion() != null) {
			elemento.setAttribute(FECHA_DEVOLUCION,
					String.valueOf(alquiler.getFechaDevolucion().format(FORMATO_FECHA)));

		}

		return elemento;

	}

	@Override
	public List<Alquiler> get() {
		return coleccionAlquileres.stream().toList();
	}

	@Override
	public List<Alquiler> get(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("El cliente no puede ser nulo ");
		}
		List<Alquiler> alquilerCliente = new ArrayList<>();

		for (int i = 0; i < coleccionAlquileres.size(); i++) {

			if (coleccionAlquileres.get(i).getCliente().equals(cliente)) {
				alquilerCliente.add(coleccionAlquileres.get(i));

			}

		}

		return alquilerCliente;

	}

	@Override
	public List<Alquiler> get(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new NullPointerException("El turismo no puede ser nulo ");
		}
		List<Alquiler> alquilerTurismo = new ArrayList<>();

		for (int i = 0; i < coleccionAlquileres.size(); i++) {

			if (coleccionAlquileres.get(i).getVehiculo().equals(vehiculo)) {
				alquilerTurismo.add(coleccionAlquileres.get(i));

			}

		}

		return alquilerTurismo;

	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler());
		coleccionAlquileres.add(alquiler);

	}

	private void comprobarAlquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {

		for (Alquiler alquiler : get()) {
			if (alquiler.getCliente().equals(cliente)) {
				if (alquiler.getFechaDevolucion() == null) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
				} else if (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
						|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
				}
			} else if (alquiler.getVehiculo().equals(vehiculo)) {
				if (alquiler.getFechaDevolucion() == null) {
					throw new OperationNotSupportedException("ERROR: El vehículo está actualmente alquilado.");
				} else if (alquiler.getFechaDevolucion().isAfter(fechaAlquiler)
						|| alquiler.getFechaDevolucion().isEqual(fechaAlquiler)) {
					throw new OperationNotSupportedException("ERROR: El vehículo tiene un alquiler posterior.");
				}
			}
		}
	}

	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un cliente nulo.");
		}

		Alquiler alquiler = getAlquilerAbierto(cliente);
		if (alquiler == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler abierto para ese cliente.");
		}

		alquiler.devolver(fechaDevolucion);

	}

	private Alquiler getAlquilerAbierto(Cliente cliente) {
		Iterator<Alquiler> alquiler = get(cliente).iterator();
		Alquiler temp = null;

		while (alquiler.hasNext() && temp == null) {
			Alquiler temp2;
			temp2 = alquiler.next();
			if (temp2.getFechaDevolucion() == null) {
				temp = temp2;
			}
		}

		return temp;
	}

	@Override
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {

		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un vehículo nulo.");
		}

		Alquiler alquiler = getAlquilerAbierto(vehiculo);
		if (alquiler == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler abierto para ese vehículo.");
		}

		alquiler.devolver(fechaDevolucion);
	}

	private Alquiler getAlquilerAbierto(Vehiculo vehiculo) {
		Iterator<Alquiler> alquiler = get(vehiculo).iterator();
		Alquiler temp = null;

		while (alquiler.hasNext() && temp == null) {
			Alquiler temp2;
			temp2 = alquiler.next();
			if (temp2.getFechaDevolucion() == null) {
				temp = temp2;
			}
		}

		return temp;
	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {
		Alquiler copia = null;
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}
		int busqueda = coleccionAlquileres.indexOf(alquiler);
		if (busqueda != -1) {
			copia = coleccionAlquileres.get(busqueda);
		}
		return copia;

	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}
		if (!coleccionAlquileres.contains(alquiler)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}

		coleccionAlquileres.remove(alquiler);

	}

}
