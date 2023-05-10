package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.io.IOException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class VentanaBuscarClientes extends Controlador{
	
	@FXML
	private VistaGrafica vistagrafica = VistaGrafica.getInstancia();
	
	@FXML
	private TextField dni;
	
	@FXML
	private BorderPane bp;
	
	
	
	
	@FXML
	private void comprobarCliente() throws IOException {
		
		if(vistagrafica.getControlador().buscar(Cliente.getClienteConDni(dni.getText()))==null) {
			Dialogos.mostrarDialogoInformacion("Erdor","El cliente no existe" , getEscenario());
		}
		else {
			
			Parent root = null;
			try {
				root = FXMLLoader.load(LocalizadorRecursos.class.getResource("vistas/modificarCliente.fxml"));
			} catch (IOException e) {
				Dialogos.mostrarDialogoInformacion("Erdor","El cliente no existe" , getEscenario());
			}

			bp.setCenter(root);
			
		   
		}
	}
	
	
	

}
