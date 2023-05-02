package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

public interface IFuenteDatos {

	IClientes crearClientes();

	IAlquileres crearAlquileres();

	IVehiculos crearVehiculos();

}