package Alojamientos;

import java.io.Serializable;

public abstract class Hotel extends Alojamiento implements Serializable {
	private static final long serialVersionUID = 1029656478665098638L;
	
	//private String nombreH;
	//private int precio;
	private int estrellas;
	static String[] hoteles = { "NH", "Barcelo", "Hilton", "Tryp", "Hesperia", "Hotel Europa", "Melia", "Park Hotel" };

	//Constructor de la clase
	public Hotel(String nombreH, int precio, int estrellas, int codigoAl, String tipoAl, String pais,
			String[] hoteles,int fechaMes, int fechaDia) {
		super(codigoAl, tipoAl, pais, nombreH);

		this.nombreAl = nombreH;
		this.precioAl = precio;
		this.estrellas = estrellas;
	}

	public String getNombreH() {
		return nombreAl;
	}

	public void setNombreH(String nombreH) {
		this.nombreAl = nombreH;
	}

	public int getPrecio() {
		return precioAl;
	}

	public void setPrecio(int precio) {
		this.precioAl = precio;
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
