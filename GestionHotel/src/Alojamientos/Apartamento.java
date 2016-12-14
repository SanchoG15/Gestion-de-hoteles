package Alojamientos;

import java.io.Serializable;

public abstract class Apartamento extends Alojamiento implements Serializable {
	private static final long serialVersionUID = 4306348484934038L;
	//private String nombreAp;
	private int precio;
	//private String tipoAp;
	
	//Array de Strings
	public static String[] apartamentos = { "Posada Doña Manuela", "Apartamentos Casa Rivera", "Euroapartaments",
			"Tryp Apartaments", "Apartaments California", "Iberia Apartaments", "Apartamentos tia Paca" };

	//Constructor de la clase
	public Apartamento(String nombreAp, String tipoAp, int precio, int codigoAl, String tipoAl, String pais,
			String[] apartamentos) {
		super(codigoAl, tipoAl, pais, nombreAp);

		this.nombreAl = nombreAp;
		this.precio = precio;
		this.tipoAl = tipoAp;

	}

	public String getNombreAp() {
		return nombreAl;
	}

	public void setNombreAp(String nombreAp) {
		this.nombreAl = nombreAp;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getTipoAp() {
		return tipoAl;
	}

	public void setTipoAp(String tipoAp) {
		this.tipoAl = tipoAp;
	}

	public static String[] getApartamentos() {
		return apartamentos;
	}

	public static void setApartamentos(String[] apartamentos) {
		Apartamento.apartamentos = apartamentos;
	}
/**
	 public void listaApartamentos(String[] apartamentos){
	
	 for (int i = 0; i < apartamentos.length; i++) {
	 System.out.println(apartamentos[i]);
	 }

	 }
*/
}
