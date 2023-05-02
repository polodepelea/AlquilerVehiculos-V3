package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public interface IVehiculos {

	List<Vehiculo> get();

	void insertar(Vehiculo vehiculo) throws OperationNotSupportedException;

	Vehiculo buscar(Vehiculo vehiculo);

	void borrar(Vehiculo turismo) throws OperationNotSupportedException;

	void comenzar();
	
	void terminar();

}