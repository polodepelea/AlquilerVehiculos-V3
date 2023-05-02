package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class VistaTexto extends Vista {
	private static final String LISTAS = "%d. %s%n";

	public void comenzar() {
		Accion accion;
		Accion.setVista(this);
		do {
			Consola.mostrarMenuAcciones();
			accion = Consola.elegirAccion();
			try {
				Consola.mostrarCabecera(accion.toString());
				accion.ejecutar();
			} catch (IllegalArgumentException | OperationNotSupportedException e) {
				System.out.printf("%n%s%n%n", e.getMessage());
			}
		} while (!(accion.equals(Accion.SALIR)));
		getControlador().terminar();

	}

	public void terminar() {
		System.out.printf("usale kahle");
	}

	public void insertarCliente() throws OperationNotSupportedException {

		getControlador().insertar(Consola.leerCliente());
		System.out.printf("Cliente insertado correctamente%n");

	}

	public void insertarVehiculo() throws OperationNotSupportedException {

		getControlador().insertar(Consola.leerVehiculo());
		System.out.printf("Vehiculo insertado correctamente%n");

	}

	public void insertarAlquiler() throws OperationNotSupportedException {
		getControlador().insertar(Consola.leerAlquiler());
		System.out.printf("Alquiler insertado correctamente%n");

	}

	public void buscarCliente() {
		Cliente cliente = Consola.leerClienteDni();
		try {
			System.out.println(getControlador().buscar(cliente));
		} catch (IllegalArgumentException e) {
			System.out.printf("Cliente no encontrado%n");
		}
		System.out.printf("Cliente encontrado correctamente%n");
	}

	public void buscarAlquiler() {
		Alquiler alquiler = Consola.leerAlquiler();
		try {
			System.out.println(getControlador().buscar(alquiler));
		} catch (IllegalArgumentException e) {
			System.out.println("Alquiler no encontrado");
		}
		System.out.printf("Alquiler encontrado correctamente%n");
	}

	public void buscarVehiculo() {
		Vehiculo vehiculo = Consola.leerVehiculoMatricula();
		try {
			System.out.println(getControlador().buscar(vehiculo));
		} catch (IllegalArgumentException e) {
			System.out.printf("Vehiculo no encontrado%n");
		}
		System.out.printf("Vehiculo encontrado correctamente%n");
	}

	public void modificarCliente() throws OperationNotSupportedException {
		getControlador().modificar(Consola.leerClienteDni(), Consola.leerNombre(), Consola.leerTelefono());
		System.out.printf("Cliente modificado correctamente%n");
	}

	public void devolverAlquilerCliente() throws OperationNotSupportedException {
		getControlador().devolver(Consola.leerClienteDni(), Consola.leerFechaDevolucion());
		System.out.printf("Alquiler del cliente devuelto correctamente%n");

	}

	public void devolverAlquilerVehiculo() throws OperationNotSupportedException {
		getControlador().devolver(Consola.leerVehiculoMatricula(), Consola.leerFechaDevolucion());
		System.out.printf("Alquiler del cliente devuelto correctamente%n");
	}

	public void borrarCliente() throws OperationNotSupportedException {

		getControlador().borrar(Consola.leerClienteDni());
		System.out.printf("Cliente borrado correctamente%n");

	}

	public void borrarVehiculo() throws OperationNotSupportedException {
		getControlador().borrar(Consola.leerVehiculoMatricula());
		System.out.printf("Vehiculo borrado correctamente%n");

	}

	public void borrarAlquiler() throws OperationNotSupportedException {
		getControlador().borrar(Consola.leerAlquiler());
		System.out.printf("Alquiler borrado correctamente%n");

	}

	public void listarClientes() {
		List<Cliente> clientes = getControlador().getClientes();
		if (!clientes.isEmpty()) {
			clientes.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni));
			clientes.forEach(c -> System.out.printf(LISTAS, clientes.indexOf(c) + 1, c));
		} else {
			System.out.printf("No hay clientes para listar%n");
		}
	}

	public void listarVehiculos() {
		List<Vehiculo> vehiculos = getControlador().getVehiculos();
		if (!vehiculos.isEmpty()) {
			vehiculos.sort(Comparator.comparing(Vehiculo::getMarca).thenComparing(Vehiculo::getModelo)
					.thenComparing(Vehiculo::getMatricula));
			vehiculos.forEach(v -> System.out.printf(LISTAS, vehiculos.indexOf(v) + 1, v));
		} else {
			System.out.printf("No hay vehiculos para listar%n");
		}
	}

	public void listarAlquileres() {
		List<Alquiler> alquileres = getControlador().getAlquileres();
		if (!alquileres.isEmpty()) {
			Comparator<Cliente> comparadorCliente = Comparator.comparing(Cliente::getNombre)
					.thenComparing(Cliente::getDni);
			alquileres.sort(Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getCliente,
					comparadorCliente));
			alquileres.forEach(alquiler -> System.out.printf(LISTAS, alquileres.indexOf(alquiler) + 1, alquiler));

		} else {
			System.out.printf("No hay alquileres para listar%n");
		}
	}

	public void listarAlquileresCliente() {
		Cliente cliente = Consola.leerClienteDni();
		List<Alquiler> alquileres = getControlador().getAlquileres(cliente);

		Optional<Alquiler> primerAlquiler = alquileres.stream().sorted(Comparator.comparing(Alquiler::getFechaAlquiler)
				.thenComparing(Alquiler::getCliente, Comparator.comparing(Cliente::getNombre))).findFirst();

		if (primerAlquiler.isPresent()) {
			System.out.printf("Alquileres del cliente %s: %n", cliente.getNombre());
			alquileres.stream()
					.sorted(Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getCliente,
							Comparator.comparing(Cliente::getNombre)))
					.forEach(alquiler -> System.out.printf(LISTAS, alquileres.indexOf(alquiler) + 1, alquiler));
		} else {
			System.out.printf("Este cliente no tienen alquileres para listar%n");
		}
	}

	public void listarAlquileresVehiculo() {
		Vehiculo vehiculo = Consola.leerVehiculoMatricula();
		List<Alquiler> alquileres = getControlador().getAlquileres(vehiculo);
		if (!alquileres.isEmpty()) {
			Comparator<Cliente> comparadorCliente = Comparator.comparing(Cliente::getNombre)
					.thenComparing(Cliente::getDni);
			alquileres.sort(Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getCliente,
					comparadorCliente));
			alquileres.forEach(alquiler -> System.out.printf(LISTAS, alquileres.indexOf(alquiler) + 1, alquiler));
		} else {
			System.out.printf("Este vehiculo no tienen alquileres para listar%n");
		}
	}

	public void mostrarEstadisticasMensualesTipoVehiculo() {
		Map<TipoVehiculo, Integer> estadisticas = inicializarEstadisticas();
		if (!estadisticas.isEmpty()) {
			System.out.printf("Estadásticas de alquileres por tipo de vehículo: %n");
			for (Map.Entry<TipoVehiculo, Integer> entry : estadisticas.entrySet()) {
				System.out.printf("%s alquilados: %s%n", entry.getKey(), entry.getValue());
			}
		} else {
			System.out.printf("No hay estadásticas que mostrar en este mes%n");
		}
	}

	private Map<TipoVehiculo, Integer> inicializarEstadisticas() {
		Map<TipoVehiculo, Integer> estadisticas = new TreeMap<>();

		LocalDate mes = Consola.leerMes();
		List<Alquiler> alquileres = getControlador().getAlquileres();

		for (Alquiler alquiler : alquileres) {
			if (alquiler.getFechaAlquiler().getMonth().equals(mes.getMonth())
					&& alquiler.getFechaAlquiler().getYear() == mes.getYear()) {
				TipoVehiculo tipoVehiculo = TipoVehiculo.get(alquiler.getVehiculo());
				estadisticas.put(tipoVehiculo, estadisticas.getOrDefault(tipoVehiculo, 0) + 1);
			}
		}

		return estadisticas;
	}

}
