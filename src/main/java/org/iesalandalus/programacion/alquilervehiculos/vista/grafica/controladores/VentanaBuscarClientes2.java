package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.io.IOException;	

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class VentanaBuscarClientes2 extends Controlador {

    public static String dniValue = "";

    private VistaGrafica vistagrafica = VistaGrafica.getInstancia();

    private static final String ERROR_TITULO = "Error";
    private static final String ERROR_CLIENTE_NO_EXISTE = "El cliente no existe";
    private static final String ERROR_BP_FALLIDO = "El bp ha fallado";


    @FXML
    private TextField dni;

    @FXML
    private BorderPane bp;

    @FXML
    public String getDniValue() {
        return dni.getText();
    }

    @FXML
    private void comprobarCliente() {
        try {
            if (vistagrafica.getControlador().buscar(Cliente.getClienteConDni(dni.getText())) == null) {
                Dialogos.mostrarDialogoInformacion(ERROR_TITULO, ERROR_CLIENTE_NO_EXISTE, getEscenario());
            } else {
                dniValue = dni.getText();
                cargarTablaAlquilerCliente();
            }
        } catch (IllegalArgumentException e) {
            Dialogos.mostrarDialogoError(ERROR_TITULO, e.getMessage(), getEscenario());
        }
    }

    private void cargarTablaAlquilerCliente() {
        try {
            Parent root = FXMLLoader.load(LocalizadorRecursos.class.getResource("vistas/TablaAlquilerCliente.fxml"));
            bp.setCenter(root);
        } catch (IOException e) {
            Dialogos.mostrarDialogoInformacion(ERROR_TITULO, ERROR_BP_FALLIDO, getEscenario());
        }
    }
}

