package Alojamientos;

public class Alojamiento {

	protected int codigoAl;
	protected String tipoAl;
	protected String pais;
	protected String nombreAl;
	protected int precioAl;

	// Constructor de la clase
	public Alojamiento(int codigoAl, String tipoAl, String pais, String nombreAl) {

		codigoAl = this.codigoAl;
		tipoAl = this.tipoAl;
		pais = this.pais;
		nombreAl = this.nombreAl;

	}

	// Gesters and Seters
	public int getCodigoAl() {
		return codigoAl;
	}

	public void setCodigoAl(int codigoAl) {
		this.codigoAl = codigoAl;
	}

	public String getTipoAl() {
		return tipoAl;
	}

	public void setTipoAl(String tipoAl) {
		this.tipoAl = tipoAl;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getNombreAl() {
		return nombreAl;
	}

	public void setNombreAl(String nombreAl) {
		nombreAl = nombreAl;
	}

	
}
