package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;


import java.util.List;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class TablaClientes extends Controlador{
	@FXML
	private VistaGrafica vistagrafica = VistaGrafica.getInstancia();
	
	@FXML
	private TableView<Cliente> tableView;

	@FXML
	private TableColumn<Cliente, String> nombreColumn;

	@FXML
	private TableColumn<Cliente, Integer> dniColumn;
	
	@FXML
	private TableColumn<Cliente, Integer> telefonoColumn;

	public void initialize() {
	    nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
	    dniColumn.setCellValueFactory(new PropertyValueFactory<>("dni"));
	    telefonoColumn.setCellValueFactory(new PropertyValueFactory<>("telefono"));

	    List<Cliente> clientes = listarClientes();
	  

	    tableView.setItems(FXCollections.observableArrayList(clientes));
	}
	
	
	
	@FXML
	private List<Cliente> listarClientes() {
			return vistagrafica.getControlador().getClientes();			
			
		}
	
	
	
		
	}


