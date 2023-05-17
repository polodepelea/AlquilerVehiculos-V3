package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.io.IOException;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class InsertarAutobus extends Controlador{
	
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
		
	    Autobus turismo = new Autobus(marca.getText(),modelo.getText(),Integer.parseInt(plazas.getText()),matricula.getText());
	    
	    try {
	        vistagrafica.getControlador().insertar(turismo);
	    } catch (OperationNotSupportedException e) {
	    	Dialogos.mostrarDialogoError("Erroe", e.getMessage(), getEscenario());
	    }           
	}
	
	

}
