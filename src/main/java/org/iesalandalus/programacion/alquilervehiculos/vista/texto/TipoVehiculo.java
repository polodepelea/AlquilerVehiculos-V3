package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public enum TipoVehiculo {
	TURISMO("Turismo"), AUTOBUS("Autobus"), FURGONETA("Furgoneta");

	private String nombre;

	private TipoVehiculo(String nombre) {
		this.nombre = nombre;
	}

	private static boolean esOrdinalValido(int ordinal) {
		return ordinal >= 0 && ordinal < values().length;
	}

	public static TipoVehiculo get(int ordinal) throws OperationNotSupportedException {
		if (esOrdinalValido(ordinal)) {
			return values()[ordinal];

		}
		throw new OperationNotSupportedException("Ordinal inválido");

	}

	public static TipoVehiculo get(Vehiculo vehiculo) {
		TipoVehiculo vehiculoCopia = null;

		if (vehiculo instanceof Turismo) {
			vehiculoCopia = TURISMO;

		}
		else if (vehiculo instanceof Autobus) {
			vehiculoCopia = AUTOBUS;

		}
		else if (vehiculo instanceof Furgoneta) {
			vehiculoCopia = FURGONETA;

		}
		return vehiculoCopia;

	}

	@Override
	public String toString() {
		return String.format("%s", nombre);
		
	}

}
