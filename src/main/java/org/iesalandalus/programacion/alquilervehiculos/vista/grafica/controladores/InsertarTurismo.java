package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class InsertarTurismo extends Controlador {

	private VistaGrafica vistagrafica = VistaGrafica.getInstancia();

	@FXML
	private BorderPane bp;

	@FXML
	private TextField cilindrada;

	@FXML
	private TextField matricula;

	@FXML
	private TextField marca;

	@FXML
	private TextField modelo;

	@FXML

	private void insertarTurismo() {

		try {
			vistagrafica.getControlador().insertar(new Turismo(marca.getText(), modelo.getText(), Integer.parseInt(cilindrada.getText()),
					matricula.getText()));
			Dialogos.mostrarDialogoInformacion("Informacion", "Turismo insertado correctamente", getEscenario());

		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("Erroe", e.getMessage(), getEscenario());
		}
	}

}
