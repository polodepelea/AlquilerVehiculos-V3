package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class InsertarAutobus extends Controlador {

	@FXML
	private VistaGrafica vistagrafica = VistaGrafica.getInstancia();

	@FXML
	private BorderPane bp;

	@FXML
	private TextField plazas;

	@FXML
	private TextField matricula;

	@FXML
	private TextField marca;

	@FXML
	private TextField modelo;

	@FXML
	private void insertarAutobus() {

		try {
			vistagrafica.getControlador().insertar(new Autobus(marca.getText(), modelo.getText(), Integer.parseInt(plazas.getText()),
					matricula.getText()));
			Dialogos.mostrarDialogoInformacion("Informacion", "Autobus insertado correctamente", getEscenario());
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("Erroe", e.getMessage(), getEscenario());
		}
	}

}
