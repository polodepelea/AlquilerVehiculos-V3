package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class VehiculoAlquilar extends Controlador {

	VentanaBuscarClientes ventana = new VentanaBuscarClientes();

	private VistaGrafica vistagrafica = VistaGrafica.getInstancia();

	@FXML
	private TextField dni;

	@FXML
	private DatePicker fecha;

	@FXML
	private void alquilarCliente() {
		try {
			vistagrafica.getControlador().insertar(new Alquiler(Cliente.getClienteConDni(dni.getText()),
					Vehiculo.getVehiculoConMatricula(VentanaBuscarVehiculo.matriculaValue), fecha.getValue()));
			Dialogos.mostrarDialogoInformacion("Informacion", "Alquiler creado correctamente", getEscenario());
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("Error", e.getMessage(), getEscenario());
		}

	}

}
