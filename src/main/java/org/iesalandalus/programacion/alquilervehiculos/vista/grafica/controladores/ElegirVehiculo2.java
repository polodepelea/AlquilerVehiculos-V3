package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.io.IOException;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ElegirVehiculo2 extends Controlador{
	
	@FXML
	private VistaGrafica vistagrafica = VistaGrafica.getInstancia();
	
	@FXML
	private BorderPane bp;
	
	 
	 
	 
	@FXML
	private void ventanaFurgoneta() {
		Parent root = null;
		try {
			root = FXMLLoader.load(LocalizadorRecursos.class.getResource("vistas/TablaFurgoneta.fxml"));
		} catch (IOException e) {
			Dialogos.mostrarDialogoInformacion("Error", "El bp a falldo", getEscenario());
		}
		bp.setCenter(root);
		
	}
	
	
	@FXML
	private void ventanaTurismo() {
		Parent root = null;
		try {
			root = FXMLLoader.load(LocalizadorRecursos.class.getResource("vistas/TablaTurismo.fxml"));
		} catch (IOException e) {
			Dialogos.mostrarDialogoInformacion("Error", "El bp a falldo", getEscenario());
		}
		bp.setCenter(root);
		
	}
	
	@FXML
	private void ventanaAutobus() {
		Parent root = null;
		try {
			root = FXMLLoader.load(LocalizadorRecursos.class.getResource("vistas/TablaAutobus.fxml"));
		} catch (IOException e) {
			Dialogos.mostrarDialogoInformacion("Error", "El bp a falldo", getEscenario());
		}
		bp.setCenter(root);
	}

}
