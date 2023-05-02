package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import javax.naming.OperationNotSupportedException;

public enum Accion {
	SALIR("Salir") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			//Accion ya no hace nada//
			
		}
	},
	INSERTAR_CLIENTE("Insertar cliente") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.insertarCliente();

		}
	},
	INSERTAR_VEHICULO("Insertar vehiculo") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.insertarVehiculo();

		}
	},
	INSERTAR_ALQUILER("Insertar alquiler") { 
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.insertarAlquiler();

		}
	},
	BUSCAR_CLIENTE("Buscar cliente") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.buscarCliente();

		}
	},
	BUSCAR_VEHICULO("Buscar vehiculo") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.buscarVehiculo();

		}
	},
	BUSCAR_ALQUILER("Buscar alquiler") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.buscarAlquiler();

		}
	},
	MODIFICAR_CLIENTE("Modificar cliente") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.modificarCliente();
		}
	},
	DEVOLVER_ALQUILER_CLIENTE("Devolver alquiler cliente") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.devolverAlquilerCliente();
		}
	},
	DEVOLVER_ALQUILER_VEHICULO("Devolver alquiler vehiculo") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.devolverAlquilerVehiculo();
		}
	},
	BORRAR_CLIENTE("Borrar cliente") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.borrarCliente();
		}
	},
	BORRAR_VEHICULO("Borrar vehiculo") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.borrarVehiculo();
		}
	},
	BORRAR_ALQUILER("Borrar alquiler") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.borrarAlquiler();
		}
	},
	LISTAR_CLIENTES("Listar clientes") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.listarClientes();
		}
	},
	LISTAR_VEHICULO("Listar vehiculo") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.listarVehiculos();
		}
	},
	LISTAR_ALQUILERES("Listar alquileres") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.listarAlquileres();
		}
	},
	LISTAR_ALQUILERES_CLIENTE("Listar alquileres de cliente") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.listarAlquileresCliente();
		}
	},
	LISTAR_ALQUILERES_VEHICULO("Listar alquileres de vehiculo") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.listarAlquileresVehiculo();
		}
	},
	MOSTRAR_ESTADISTICAS_MENSUALES("Mostrar estadisticas mensuales") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.mostrarEstadisticasMensualesTipoVehiculo();
		}
	};

	private static VistaTexto vista;
	private String texto;

	private Accion(String texto) {
		this.texto = texto;
	}

	static void setVista(VistaTexto vista) {
		if (vista == null) {
			throw new NullPointerException("La vista no puede ser nulo");
		}
		Accion.vista = vista;

	}

	public abstract void ejecutar() throws OperationNotSupportedException;

	private static boolean esOrdinalValido(int ordinal) {
		return ordinal >= 0 && ordinal < values().length;
	}

	public static Accion get(int ordinal) throws OperationNotSupportedException {
		if (esOrdinalValido(ordinal)) {
			return values()[ordinal];

		}
		throw new OperationNotSupportedException("Ordinal inválido");

	}

	@Override
	public String toString() {
		return String.format("%d. %s", ordinal(), texto);
	}

}
