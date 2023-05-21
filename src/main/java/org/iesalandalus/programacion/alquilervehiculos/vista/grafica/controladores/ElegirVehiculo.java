package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.io.IOException;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.layout.BorderPane;

public class ElegirVehiculo extends Controlador {

	private static final String ERROR_TITULO = "Error";
	private static final String ERROR_MENSAJE = "Error al cargar los recursos";

	@FXML
	private BorderPane bp;

	@FXML
	private void ventanaFurgoneta() {
		Parent root = null;
		try {
			root = FXMLLoader.load(LocalizadorRecursos.class.getResource("vistas/InsertarFurgoneta.fxml"));
		} catch (IOException e) {
			mostrarDialogoError();
		}
		bp.setCenter(root);
	}

	@FXML
	private void ventanaTurismo() {
		Parent root = null;
		try {
			root = FXMLLoader.load(LocalizadorRecursos.class.getResource("vistas/InsertarTurismo.fxml"));
		} catch (IOException e) {
			mostrarDialogoError();
		}
		bp.setCenter(root);
	}

	@FXML
	private void ventanaAutobus() {
		Parent root = null;
		try {
			root = FXMLLoader.load(LocalizadorRecursos.class.getResource("vistas/InsertarAutobus.fxml"));
		} catch (IOException e) {
			mostrarDialogoError();
		}
		bp.setCenter(root);
	}

	private void mostrarDialogoError() {
		Dialogos.mostrarDialogoInformacion(ERROR_TITULO, ERROR_MENSAJE, getEscenario());
	}

}
