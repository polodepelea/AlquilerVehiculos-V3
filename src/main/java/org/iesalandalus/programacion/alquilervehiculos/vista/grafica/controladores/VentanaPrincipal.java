package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.io.IOException;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class VentanaPrincipal extends Controlador {
	@FXML
	private Button btPulsame;

	@FXML
	private void initialize() {
		System.out.println("inicializadamente");
	}

	@FXML
	private void abrirVentanaClientes() {
		VentanaClientes ventanaClientes = (VentanaClientes) Controladores.get("vistas/VentanaClientes.fxml", "", null);

		ventanaClientes.getEscenario().show();

	}

	@FXML
	private BorderPane bp;
	@FXML
	private AnchorPane ap;

	@FXML
	private void home() {
		bp.setCenter(ap);

	}

	@FXML
	void page1() {
		cargarPagina("page1");

	}

	@FXML
	void buscarCliente() {
		cargarPagina("buscarCliente");

	}

	@FXML
	void tablaClientes() {
		cargarPagina("TablaClientes");

	}

	@FXML
	void elegirVehiculo() {
		cargarPagina("ElegirVehiculo");

	}

	@FXML
	void buscarVehiculo() {
		cargarPagina("buscarVehiculo");

	}

	@FXML
	void elegirVehiculo2() {
		cargarPagina("ElegirVehiculo2");

	}

	@FXML
	void tablaAlquiler() {
		cargarPagina("TablaAlquiler");

	}

	@FXML
	void buscarCliente2() {
		cargarPagina("buscarCliente2");

	}

	@FXML
	void buscarVehiculo2() {
		cargarPagina("buscarVehiculo2");

	}

	@FXML
	void estadisticas() {
		cargarPagina("Estadisticas");

	}

	private void cargarPagina(String page) {
		Parent root = null;
		try {
			root = FXMLLoader.load(LocalizadorRecursos.class.getResource("vistas/" + page + ".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		bp.setCenter(root);
	}

}
