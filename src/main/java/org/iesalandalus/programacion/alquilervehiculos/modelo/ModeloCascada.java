package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class ModeloCascada extends Modelo {

	public ModeloCascada(FactoriaFuenteDatos factoriaFuenteDatos) {
		super(factoriaFuenteDatos);

	}

	public void insertar(Cliente cliente) throws OperationNotSupportedException {

		getClientes().insertar(new Cliente(cliente));

	}

	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {

		getVehiculos().insertar(Vehiculo.copiar(vehiculo));

	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {

		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		Cliente cliente = getClientes().buscar(alquiler.getCliente());
		Vehiculo vehiculo = getVehiculos().buscar(alquiler.getVehiculo());

		if (cliente == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		}
		if (vehiculo == null) {
			throw new OperationNotSupportedException("ERROR: No existe el vehiculo del alquiler.");
		}

		getAlquileres().insertar(new Alquiler(cliente, vehiculo, alquiler.getFechaAlquiler()));

	}

	public Cliente buscar(Cliente cliente) {

		Cliente copia = null;
		Cliente clienteBuscar = getClientes().buscar(cliente);
		if (clienteBuscar != null) {
			copia = new Cliente(clienteBuscar);

		}
		return copia;

	}

	public Vehiculo buscar(Vehiculo vehiculo) {
		Vehiculo copia = null;
		Vehiculo vehiculoBuscar = getVehiculos().buscar(vehiculo);
		if (vehiculoBuscar != null) {
			copia = Vehiculo.copiar(vehiculoBuscar);

		}
		return copia;
	}

	public Alquiler buscar(Alquiler alquiler) {
		Alquiler copia = null;
		Alquiler alquilerBuscar = getAlquileres().buscar(alquiler);
		if (alquilerBuscar != null) {
			copia = new Alquiler(alquilerBuscar);

		}
		return copia;
	}

	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {

		getClientes().modificar(cliente, nombre, telefono);

	}

	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		getAlquileres().devolver(cliente, fechaDevolucion);

	}

	@Override
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		getAlquileres().devolver(vehiculo, fechaDevolucion);

	}

	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		for (Alquiler alquiler : getAlquileres().get(cliente)) {
			getAlquileres().borrar(alquiler);
		}

		getClientes().borrar(cliente);

	}

	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		for (Alquiler alquiler : getAlquileres().get(vehiculo)) {
			getAlquileres().borrar(alquiler);
		}

		getVehiculos().borrar(vehiculo);
	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {

		getAlquileres().borrar(alquiler);

	}

	public List<Cliente> getListaClientes() {

		List<Cliente> copiaClientes = new ArrayList<>();
		for (Cliente cliente : getClientes().get()) {
			copiaClientes.add(new Cliente(cliente));
		}
		return copiaClientes;
	}

	public List<Vehiculo> getListaVehiculos() {

		List<Vehiculo> copiaVehiculo = new ArrayList<>();
		for (Vehiculo vehiculo : getVehiculos().get()) {
			copiaVehiculo.add(Vehiculo.copiar(vehiculo));
		}
		return copiaVehiculo;
	}

	public List<Alquiler> getListaAlquileres() {

		List<Alquiler> copiaAlquiler = new ArrayList<>();
		for (Alquiler alquiler : getAlquileres().get()) {
			copiaAlquiler.add(new Alquiler(alquiler));
		}
		return copiaAlquiler;
	}

	public List<Alquiler> getListaAlquileres(Cliente cliente) {

		List<Alquiler> copiaAlquilerCliente = new ArrayList<>();
		for (Alquiler alquiler : getAlquileres().get(cliente)) {
			copiaAlquilerCliente.add(new Alquiler(alquiler));
		}
		return copiaAlquilerCliente;
	}

	public List<Alquiler> getListaAlquileres(Vehiculo vehiculo) {

		List<Alquiler> copiaAlquilerVehiculo = new ArrayList<>();
		for (Alquiler alquiler : getAlquileres().get(vehiculo)) {
			copiaAlquilerVehiculo.add(new Alquiler(alquiler));
		}
		return copiaAlquilerVehiculo;
	}

}
