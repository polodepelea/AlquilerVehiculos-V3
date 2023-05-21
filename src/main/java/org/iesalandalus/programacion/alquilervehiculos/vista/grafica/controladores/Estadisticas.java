package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javafx.scene.chart.NumberAxis;

import java.util.List;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;

import javafx.fxml.FXML;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

public class Estadisticas extends Controlador {
	@FXML
	private StackedBarChart<String, Number> graficoEstadisticas;
	private VistaGrafica vistagrafica = VistaGrafica.getInstancia();

	public void initialize() {
		estadisticas();
		NumberAxis yAxis = (NumberAxis) graficoEstadisticas.getYAxis();
		yAxis.setAutoRanging(false);
		yAxis.setLowerBound(0);
		yAxis.setUpperBound(100);
		yAxis.setTickUnit(10);
	}

	private void estadisticas() {
		List<Alquiler> alquileres = vistagrafica.getControlador().getAlquileres();
		int totalAlquileres = alquileres.size();
		int contTurismo = 0;
		int contFurgoneta = 0;
		int contAutobus = 0;

		for (Alquiler alquiler : alquileres) {
			Vehiculo vehiculo = alquiler.getVehiculo();
			if (vehiculo instanceof Turismo) {
				contTurismo++;
			} else if (vehiculo instanceof Furgoneta) {
				contFurgoneta++;
			} else if (vehiculo instanceof Autobus) {
				contAutobus++;
			}
		}

		double porcentajeTurismo = (contTurismo / (double) totalAlquileres) * 100;
		double porcentajeFurgoneta = (contFurgoneta / (double) totalAlquileres) * 100;
		double porcentajeAutobus = (contAutobus / (double) totalAlquileres) * 100;

		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.getData().add(new XYChart.Data<>("Turismo", porcentajeTurismo));
		series.getData().add(new XYChart.Data<>("Furgoneta", porcentajeFurgoneta));
		series.getData().add(new XYChart.Data<>("Autobús", porcentajeAutobus));

		graficoEstadisticas.getData().add(series);

	}
}
