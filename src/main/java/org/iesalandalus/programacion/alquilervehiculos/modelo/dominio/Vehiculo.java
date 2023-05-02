package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public abstract class Vehiculo {

	private static final String ER_MARCA = "([A-Z][a-z]+)(( [A-Z][a-z]+)|(\\-[A-Z][a-z]+)|([A-Z][a-z]+))?|([A-Z]+)";
	private static final String ER_MATRICULA = "\\d{4}[QWRTYPSDFGHJKLZXCVBNM]{3}";

	private String marca;
	private String modelo;
	private String matricula;

	protected Vehiculo(String marca, String modelo, String matricula) {
		setMarca(marca);
		setModelo(modelo);
		setMatricula(matricula);
	}

	protected Vehiculo(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No es posible copiar un veh�culo nulo.");
		}
		marca = vehiculo.getMarca();
		modelo = vehiculo.getModelo();
		matricula = vehiculo.getMatricula();
	}

	public static Vehiculo copiar(Vehiculo vehiculo) {
		Vehiculo vehiculoCopia = null;

		if (vehiculo instanceof Turismo turismo) {
			vehiculoCopia = new Turismo(turismo);

		} else if (vehiculo instanceof Autobus autobus) {
			vehiculoCopia = new Autobus(autobus);

		} else if (vehiculo instanceof Furgoneta furgoneta) {
			vehiculoCopia = new Furgoneta(furgoneta);

		}
		return vehiculoCopia;

	}

	public static Vehiculo getVehiculoConMatricula(String matricula) {
		return new Turismo("Audi", "2", 2, matricula);

	}

	public abstract int getFactorPrecio();

	public String getMarca() {
		return marca;
	}

	private void setMarca(String marca) {
		if (marca == null) {
			throw new NullPointerException("ERROR: La marca no puede ser nula.");
		}
		if (!marca.matches(ER_MARCA)) {
			throw new IllegalArgumentException("ERROR: La marca no tiene un formato v�lido.");
		}

		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	private void setModelo(String modelo) {
		if (modelo == null) {
			throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
		}
		if (modelo.isBlank()) {
			throw new IllegalArgumentException("ERROR: El modelo no puede estar en blanco.");
		}
		this.modelo = modelo;
	}

	public String getMatricula() {
		return matricula;
	}

	private void setMatricula(String matricula) {
		if (matricula == null) {
			throw new NullPointerException("ERROR: La matr�cula no puede ser nula.");
		}
		if (!matricula.matches(ER_MATRICULA)) {
			throw new IllegalArgumentException("ERROR: La matr�cula no tiene un formato v�lido.");
		}

		this.matricula = matricula;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Vehiculo))
			return false;
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(matricula, other.matricula);
	}

}
