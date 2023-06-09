package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.util.List;	

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
	
public class OpcionesVehiculo extends Controlador {

	VentanaBuscarVehiculo ventana = new VentanaBuscarVehiculo();

	@FXML
	private VistaGrafica vistagrafica = VistaGrafica.getInstancia();

	@FXML
	private TextField matricula;

	@FXML
	private TextField modelo;

	@FXML
	private TextField marca;

	@FXML
	private TextField cilindrada;

	@FXML
	private TextField plazas;

	@FXML
	private TextField pma;

	@FXML
	private TextField tipo;

	@FXML
	private void initialize() {

		List<Vehiculo> listaVehiculos = vistagrafica.getControlador().getVehiculos();
		String matriculaBuscado = Vehiculo.getVehiculoConMatricula(VentanaBuscarVehiculo.matriculaValue).getMatricula();

		Vehiculo vehiculoEncontrado = null;
		for (Vehiculo vehiculo : listaVehiculos) {
			if (vehiculo.getMatricula().equals(matriculaBuscado)) {
				vehiculoEncontrado = vehiculo;
				break;
			}
		}

		matricula.setText(vehiculoEncontrado.getMatricula());

		modelo.setText(vehiculoEncontrado.getModelo());

		marca.setText(vehiculoEncontrado.getMarca());

		if (vehiculoEncontrado instanceof Turismo turismo) {
			cilindrada.setText(String.format("%s", turismo.getCilindrada()));
			tipo.setText("Turismo");

		} else if (vehiculoEncontrado instanceof Autobus autobus) {
			plazas.setText(String.format("%s", autobus.getPlazas()));
			tipo.setText("Autobus");
		} else if (vehiculoEncontrado instanceof Furgoneta furgoneta) {
			plazas.setText(String.format("%s", furgoneta.getPlazas()));

			pma.setText(String.format("%s", furgoneta.getPma()));
			tipo.setText("Furgoneta");
		}

	}

	@FXML
	private void borrarVehiculo() {
		try {
			vistagrafica.getControlador()
					.borrar(Vehiculo.getVehiculoConMatricula(VentanaBuscarVehiculo.matriculaValue));
			Dialogos.mostrarDialogoInformacion("Informacion", "Vehiculo borrado correctamente", getEscenario());
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("Erroe", e.getMessage(), getEscenario());
		}

	}

	@FXML
	private void botonAlquilar() {
		VehiculoAlquilar ventanaPrincipal = (VehiculoAlquilar) Controladores.get("vistas/VehiculoAlquilar.fxml", "",
				null);
		ventanaPrincipal.getEscenario().show();
	}

	@FXML
	private void botonDevolver() {
		VehiculoDevolver ventanaPrincipal = (VehiculoDevolver) Controladores.get("vistas/VehiculoDevolver.fxml", "",
				null);
		ventanaPrincipal.getEscenario().show();
	}

	
}