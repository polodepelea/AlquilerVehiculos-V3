package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class ClienteDevolver extends Controlador {

	VentanaBuscarClientes ventana = new VentanaBuscarClientes();

	private VistaGrafica vistagrafica = VistaGrafica.getInstancia();

	@FXML
	private DatePicker fecha;

	@FXML
	private void devolverVehiculo() {
		try {
			vistagrafica.getControlador().devolver(Cliente.getClienteConDni(VentanaBuscarClientes.dniValue),
					fecha.getValue());
			Dialogos.mostrarDialogoInformacion("Informacion", "Alquiler devuelto correctamente", getEscenario());
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("Erroe", e.getMessage(), getEscenario());
		}

	}

}
