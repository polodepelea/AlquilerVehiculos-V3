package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class InsertarFurgoneta extends Controlador {

	private VistaGrafica vistagrafica = VistaGrafica.getInstancia();

	@FXML
	private BorderPane bp;

	@FXML
	private TextField plazas;

	@FXML
	private TextField pma;

	@FXML
	private TextField matricula;

	@FXML
	private TextField marca;

	@FXML
	private TextField modelo;

	@FXML
	private void insertarFurgoneta() {

		try {
			vistagrafica.getControlador().insertar(new Furgoneta(marca.getText(), modelo.getText(), Integer.parseInt(pma.getText()),
					Integer.parseInt(plazas.getText()), matricula.getText()));
			Dialogos.mostrarDialogoInformacion("Informacion", "Furgonea insertada correctamente", getEscenario());

		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("Erroe", e.getMessage(), getEscenario());
		}
	}

}
