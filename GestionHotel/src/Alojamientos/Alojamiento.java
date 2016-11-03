package Alojamientos;

public class Alojamiento {

	protected int codigoAl;
	protected String tipoAl;
	protected String pais;
	protected static String nombreAl;
	protected static int fechaMes;
	protected static int fechaDia;

	// Constructor de la clase
	public Alojamiento(int codigoAl, String tipoAl, String pais, String nombreAl, int fechaMes, int fechaDia) {

		codigoAl = this.codigoAl;
		tipoAl = this.tipoAl;
		pais = this.pais;
		nombreAl = this.nombreAl;
		fechaMes = this.fechaMes;
		fechaDia = this.fechaDia;

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

	public static String getNombreAl() {
		return nombreAl;
	}

	public static void setNombreAl(String nombreAl) {
		Alojamiento.nombreAl = nombreAl;
	}

	public static int getFechaMes() {
		return fechaMes;
	}

	public static void setFechaMes(int fechaMes) {
		Alojamiento.fechaMes = fechaMes;
	}

	public static int getFechaDia() {
		return fechaDia;
	}

	public static void setFechaDia(int fechaDia) {
		Alojamiento.fechaDia = fechaDia;
	}

}
