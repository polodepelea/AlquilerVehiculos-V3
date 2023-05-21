package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.io.IOException;	

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class VentanaBuscarVehiculo2 extends Controlador {

    public static String matriculaValue = "";

    private VistaGrafica vistagrafica = VistaGrafica.getInstancia();

    private static final String ERROR_TITULO = "Error";
    private static final String ERROR_VEHICULO_NO_EXISTE = "El vehiculo no existe";
    private static final String ERROR_BP_FALLIDO = "El bp ha fallado";

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
                Dialogos.mostrarDialogoInformacion(ERROR_TITULO, ERROR_VEHICULO_NO_EXISTE, getEscenario());
            } else {
                matriculaValue = matricula.getText();
                cargarTablaAlquilerVehiculo();
            }
        } catch (IllegalArgumentException e) {
            Dialogos.mostrarDialogoError(ERROR_TITULO, e.getMessage(), getEscenario());
        }
    }

    private void cargarTablaAlquilerVehiculo() {
        try {
            Parent root = FXMLLoader.load(LocalizadorRecursos.class.getResource("vistas/TablaAlquilerVehiculo.fxml"));
            bp.setCenter(root);
        } catch (IOException e) {
            Dialogos.mostrarDialogoInformacion(ERROR_TITULO, ERROR_BP_FALLIDO, getEscenario());
        }
    }
}

