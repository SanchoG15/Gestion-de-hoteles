package Ventanas;

public class ElementoAlojamiento {

	int id;
	String nombre;

	// Clase creada para el manejo de id en las gestiones de bd.
	public ElementoAlojamiento(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

	public int getId() {
		return id;
	}

}
