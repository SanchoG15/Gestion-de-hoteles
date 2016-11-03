package Alojamientos;

import java.io.Serializable;

public abstract class Hotel extends Alojamiento implements Serializable {
	private static final long serialVersionUID = 1029656478665098638L;
	
	private String nombreH;
	private int precio;
	private int estrellas;
	static String[] hoteles = { "NH", "Ac", "Hilton", "Tryp", "Hesperia", "Hotel Europa", "Melia", "Park Hotel" };

	//Constructor de la clase
	public Hotel(String nombreH, int precio, int estrellas, int codigoAl, String tipoAl, String pais,
			String[] hoteles) {
		super(codigoAl, tipoAl, pais, nombreAl, fechaMes, fechaDia);

		this.nombreH = nombreH;
		this.precio = precio;
		this.estrellas = estrellas;
	}

	public String getNombreH() {
		return nombreH;
	}

	public void setNombreH(String nombreH) {
		this.nombreH = nombreH;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

	public static String[] getHoteles() {
		return hoteles;
	}

	public void setHoteles(String[] hoteles) {
		this.hoteles = hoteles;
	}

}
