package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.time.LocalDate;
import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controles.FormateadorCeldaFecha;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TablaAlquilerCliente extends Controlador {
	
	private VistaGrafica vistagrafica = VistaGrafica.getInstancia();

	VentanaBuscarClientes2 clienteBuscado = new VentanaBuscarClientes2();

	@FXML
	private TableView<Alquiler> tableView;
	@FXML
	private TableColumn<Alquiler, Cliente> cliente;
	@FXML
	private TableColumn<Alquiler, Vehiculo> vehiculo;
	@FXML
	private TableColumn<Alquiler, LocalDate> fechaAlquiler;
	@FXML
	private TableColumn<Alquiler, LocalDate> fechaDevolucion;

	public void initialize() {
		cliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		vehiculo.setCellValueFactory(new PropertyValueFactory<>("vehiculo"));
		fechaAlquiler.setCellValueFactory(new PropertyValueFactory<>("fechaAlquiler"));
		fechaDevolucion.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));
		fechaAlquiler.setCellFactory(cell -> new FormateadorCeldaFecha());
		fechaDevolucion.setCellFactory(cell -> new FormateadorCeldaFecha());

		tableView.setItems(FXCollections.observableArrayList(vistagrafica.getControlador().getAlquileres()));
	}

	@FXML
	private List<Alquiler> listarClientes() {

		return vistagrafica.getControlador().getAlquileres(Cliente.getClienteConDni(VentanaBuscarClientes2.dniValue));

	}

}
