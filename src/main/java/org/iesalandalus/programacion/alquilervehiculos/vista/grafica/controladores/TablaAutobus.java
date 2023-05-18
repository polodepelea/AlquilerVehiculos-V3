package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;


import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class TablaAutobus extends Controlador{
	@FXML
	private VistaGrafica vistagrafica = VistaGrafica.getInstancia();
	
	@FXML
	private TableView<Vehiculo> tableView;

	@FXML
	private TableColumn<Vehiculo, String> marca;

	@FXML
	private TableColumn<Vehiculo, String> modelo;
	
	@FXML
	private TableColumn<Vehiculo, String> matricula;
	
	@FXML
	private TableColumn<Vehiculo, Integer> plazas;

	public void initialize() {
	    marca.setCellValueFactory(new PropertyValueFactory<>("marca"));
	    modelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
	    matricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
	    plazas.setCellValueFactory(new PropertyValueFactory<>("plazas"));


	    List<Vehiculo> autobus = listarFurgonetas();
	  

	    tableView.setItems(FXCollections.observableArrayList(autobus));
	}
	
	
	
	@FXML
	private List<Vehiculo> listarFurgonetas() {
					
		List<Vehiculo> vehiculos = vistagrafica.getControlador().getVehiculos();
		List<Vehiculo> autobus = new ArrayList<>();

		for (Vehiculo vehiculo : vehiculos) {
		    if (vehiculo instanceof Autobus) {
		        autobus.add(vehiculo);
		    }
		}
		
		return autobus;
		}
	
	
	
	
		
	}


