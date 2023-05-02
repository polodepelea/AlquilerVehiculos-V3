package org.iesalandalus.programacion.alquilervehiculos.vista.texto;	

import java.time.LocalDate;	
import java.time.format.DateTimeFormatter;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private static final String PATRON_FECHA = "dd/MM/yyyy";
	private static final String PATRON_MES = "MM/yyyy";
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern(PATRON_FECHA);

	private Consola() {
	}

	public static void mostrarCabecera(String mensaje) {
		System.out.println(mensaje);
		System.out.println("-".repeat(mensaje.length()));
	}

	public static void mostrarMenuAcciones() {
		mostrarCabecera("Men� de opciones");
		for (int i = 0; i < Accion.values().length; i++) {
			System.out.printf("%s %n", Accion.values()[i]);
		}
	}

	public static Accion elegirAccion() {
		Accion accion = null;
		int opcion;
		do {
			opcion = leerEntero("Seleccione una opci�n: ");
			try {
				accion = Accion.get(opcion);
			} catch (OperationNotSupportedException e) {

				System.out.println(e.getMessage());
			}
		} while (accion == null);
		return accion;
	}

	private static String leerCadena(String mensaje) {
		System.out.print(mensaje);
		return Entrada.cadena();
	}

	private static int leerEntero(String mensaje) {
		System.out.print(mensaje);
		return Entrada.entero();
	}

	private static LocalDate leerFecha(String mensaje, String patron) {
		LocalDate fecha = null;
		boolean esFecha = false;
		do {
			try {
				System.out.print(mensaje);
				String fechaTexto = Entrada.cadena();
				if (patron.equals(PATRON_MES)) {
					fechaTexto = "01/" + fechaTexto;
				}
				fecha = LocalDate.parse(fechaTexto, FORMATO_FECHA);
				esFecha = true;
			} catch (Exception ex) {
				System.out.printf("Debe introducir una fecha en el formato %s.%n",patron);
			}
		} while (!esFecha);
		return fecha;
	}

	public static Cliente leerCliente() {
		return new Cliente(leerNombre(), leerCadena("Digite el dni: "), leerTelefono());
	}

	public static Cliente leerClienteDni() {
		String dni = leerCadena("Digite el DNI: ");
		return Cliente.getClienteConDni(dni);
	}

	public static String leerNombre() {
		return leerCadena("Digite el nombre: ");
	}

	public static String leerTelefono() {
		return leerCadena("Digite el telefono: ");
	}

	public static Vehiculo leerVehiculo() throws OperationNotSupportedException {

		return leerVehiculo(elegirTipoVehiculo());

	}

	private static void mostrarMenuTiposVehiculos() {
		mostrarCabecera("Men� de Vehiculos");
		for (int i = 0; i < TipoVehiculo.values().length; i++) {
			System.out.printf("%d. %s %n", i, TipoVehiculo.values()[i]);
		}

	}

	private static TipoVehiculo elegirTipoVehiculo() throws OperationNotSupportedException {
		mostrarMenuTiposVehiculos();
		int opcion;
		TipoVehiculo tipoVehiculo = null;
		do {
			opcion = leerEntero("Elija el tipo de vehiculo: ");
			try {
				tipoVehiculo = TipoVehiculo.get(opcion);
			} catch (OperationNotSupportedException e) {
				System.out.printf(e.getMessage());
			}

		} while (tipoVehiculo == null);

		return tipoVehiculo;

	}

	private static Vehiculo leerVehiculo(TipoVehiculo tipoVehiculo) {
		String marca = leerCadena("Digite la marca: ");
		String modelo = leerCadena("Digite el modelo: ");
		String matricula = leerCadena("Digite la matricula: ");
		Vehiculo vehiculo = null;

		switch (tipoVehiculo) {
		case AUTOBUS:
			int plazas = leerEntero("Digite las plazas: ");
			vehiculo = new Autobus(marca, modelo, plazas, matricula);
			break;
		case FURGONETA:
			int pma = leerEntero("Digite el pma: ");
			int plazas1 = leerEntero("Digite las plazas: ");
			vehiculo = new Furgoneta(marca, modelo, pma, plazas1, matricula);
			break;
		case TURISMO:
			int cilindrada = leerEntero("Digite las cilindradas: ");
			vehiculo = new Turismo(marca, modelo, cilindrada, matricula);
			break;

		default:
			break;
		}

		return vehiculo;
	}

	public static Vehiculo leerVehiculoMatricula() {
		String matricula = leerCadena("Digite la matricula: ");
		return Vehiculo.getVehiculoConMatricula(matricula);
	}

	public static Alquiler leerAlquiler() {
		Cliente cliente = leerClienteDni();
		Vehiculo turismo = leerVehiculoMatricula();
		LocalDate fechaAlquiler = leerFecha("Digite la fecha de alquiler: ", PATRON_FECHA);
		return new Alquiler(cliente, turismo, fechaAlquiler);
	}

	public static LocalDate leerFechaDevolucion() {
		return leerFecha("Digite la fecha de devolucion: ", PATRON_FECHA);
	}

	public static LocalDate leerMes() {
		return leerFecha("Escriba el mes que quiere: ", PATRON_MES);
	}

}
