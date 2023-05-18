package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.io.IOException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.BorderPane;

public class VentanaBuscarVehiculo extends Controlador {
	
	public static String matriculaValue = "";

	@FXML
	private VistaGrafica vistagrafica = VistaGrafica.getInstancia();

	@FXML
	private TextField matricula;

	@FXML
	private BorderPane bp;
	
	
	@FXML
	public String getMatriculaValue() {
	    return matricula.getText();
	}

	@FXML
	private void comprobarVehiculo() {

		try {
			if (vistagrafica.getControlador().buscar(Vehiculo.getVehiculoConMatricula(matricula.getText())) == null) {
				
				Dialogos.mostrarDialogoInformacion("Error", "El vehiculo no existe", getEscenario());
			} else {
				
				matriculaValue = matricula.getText();
				Parent root = null;
				try {
					
					root = FXMLLoader.load(LocalizadorRecursos.class.getResource("vistas/OpcionesVehiculo.fxml"));
				} catch (IOException e) {
					Dialogos.mostrarDialogoInformacion("Error", "El bp a falldo", getEscenario());
				}

				bp.setCenter(root);

			}
		} catch (IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("Error", e.getMessage(), getEscenario());
		}
	}
	
	

}
