package Ventanas;

public class ElementoReserva {
	// Atributos de las reservas.
	private int id_res;
	private int id_alojamiento;
	private int id_usuario;
	private String fechaIni, fechaFin;
	private String ciudad, nomAloj;
	private double precio;

	// Constructor con todos los atributos, getters y setters.
	public ElementoReserva(int id_res, int id_alojamiento, int id_usuario, String fechaIni, String fechaFin,
			String ciudad, String nomAloj, double precio) {
		super();
		this.id_res = id_res;
		this.id_alojamiento = id_alojamiento;
		this.id_usuario = id_usuario;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.ciudad = ciudad;
		this.nomAloj = nomAloj;
		this.precio = precio;
	}

	public int getId_res() {
		return id_res;
	}

	public void setId_res(int id_res) {
		this.id_res = id_res;
	}

	public int getId_alojamiento() {
		return id_alojamiento;
	}

	public void setId_alojamiento(int id_alojamiento) {
		this.id_alojamiento = id_alojamiento;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getNomAloj() {
		return nomAloj;
	}

	/** Mestodo toString implementado para mostrar los datos de las reservas a
	*   los usuarios en la VentanaMisReservas.
	*/ 
	@Override
	public String toString() {
		return nomAloj + " " + ciudad + " " + fechaIni + " " + fechaFin + " " + precio + "€";
	}

}
