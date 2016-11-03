package Alojamientos;

import java.io.Serializable;

public abstract class Apartamento extends Alojamiento implements Serializable {
	private static final long serialVersionUID = 4306348484934038L;
	private String nombreAp;
	private int precio;
	private String tipoAp;
	
	//Array de Strings
	public static String[] apartamentos = { "Posada Doña Manuela", "Apartamentos Casa Rivera", "Euroapartaments",
			"Tryp Apartaments", "Apartaments California", "Benidorm Apartaments", "Apartamentos tia Paca" };

	//Constructor de la clase
	public Apartamento(String nombreAp, String tipoAp, int precio, int codigoAl, String tipoAl, String pais,
			String[] apartamentos) {
		super(codigoAl, tipoAl, pais, nombreAl, fechaMes, fechaDia);

		this.nombreAp = nombreAp;
		this.precio = precio;
		this.tipoAp = tipoAp;

	}

	public String getNombreAp() {
		return nombreAp;
	}

	public void setNombreAp(String nombreAp) {
		this.nombreAp = nombreAp;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getTipoAp() {
		return tipoAp;
	}

	public void setTipoAp(String tipoAp) {
		this.tipoAp = tipoAp;
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
