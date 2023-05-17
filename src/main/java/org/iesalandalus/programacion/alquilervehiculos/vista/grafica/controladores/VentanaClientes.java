package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;	

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.TextField; 



public class VentanaClientes extends Controlador{
	@FXML
	private VistaGrafica vistagrafica = VistaGrafica.getInstancia();
	
	
	 @FXML
	 private TextField nombre;
	 @FXML
	 private TextField dni;
	 @FXML
	 private TextField telefono;
	 
	
	@FXML
	private void insertarCliente() {
		
	    Cliente cliente = new Cliente(nombre.getText(), dni.getText(), telefono.getText());
	    
	    try {
	        vistagrafica.getControlador().insertar(cliente);
	    } catch (OperationNotSupportedException e) {
	    	Dialogos.mostrarDialogoError("Erroe", e.getMessage(), getEscenario());
	    }           
	}

	
	
	
	

}
