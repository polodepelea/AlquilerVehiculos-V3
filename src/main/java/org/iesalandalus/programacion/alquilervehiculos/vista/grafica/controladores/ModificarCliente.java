package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ModificarCliente extends Controlador {

	VentanaBuscarClientes ventana = new VentanaBuscarClientes();
	

	@FXML
	private VistaGrafica vistagrafica = VistaGrafica.getInstancia();

	@FXML
	private TextField nombre;

	@FXML
	private TextField telefono;
	
	@FXML
	private void initialize() {

		nombre.setText(Cliente.getClienteConDni(VentanaBuscarClientes.dniValue).getNombre());
		
		telefono.setText(Cliente.getClienteConDni(VentanaBuscarClientes.dniValue).getTelefono());
		

	}

	@FXML
	private void mofificarCliente() {

		try {
			vistagrafica.getControlador().modificar(Cliente.getClienteConDni(VentanaBuscarClientes.dniValue),
					nombre.getText(), telefono.getText());
		} catch (OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("Erroe", e.getMessage(), getEscenario());
		}

	}
	
	
	@FXML
	private void borrarCliente() {
		try {
			vistagrafica.getControlador().borrar(Cliente.getClienteConDni(VentanaBuscarClientes.dniValue));
		} catch (OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("Erroe", e.getMessage(), getEscenario());
		}
		
	}
	
	@FXML
	private void botonAlquilar() {
		ClienteAlquilar ventanaPrincipal = (ClienteAlquilar) Controladores.get("vistas/ClienteAlquilar.fxml", "", null);
		ventanaPrincipal.getEscenario().show();
	}
	
	@FXML
	private void botonDevolver() {
		ClienteDevolver ventanaPrincipal = (ClienteDevolver) Controladores.get("vistas/ClienteDevolver.fxml", "", null);
		ventanaPrincipal.getEscenario().show();
	}
	
	


{
	
}
}