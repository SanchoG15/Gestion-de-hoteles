package datos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionReservas implements IGestionReservas, Serializable {
	private static final long serialVersionUID = 879965647864716638L;
	
	private int codigoReserva;
	private String pais;
	private int fechaDía;
	private int fechaMes;
	private String nombreAl;

	// Constructores de la clase
	public GestionReservas(int codigoReserva, String pais, int fechaMes, int fechaDía, String nombreAl) {
		this.codigoReserva = codigoReserva;
		this.fechaDía = fechaDía;
		this.fechaMes = fechaMes;
		this.pais = pais;
		this.nombreAl = nombreAl;

	}

	public GestionReservas() {

	}

	// Metodo que borra las reservas del fichero Reservas.txt
	public void borrarReserva() {
		BufferedWriter bw;
		try {

			bw = new BufferedWriter(new FileWriter("Reservas.txt"));
			bw.write("");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void seleccionarHotel() {
	
	}

	@Override
	public void seleccionarApartamento() {
		
	}

	public int getCodigoReserva() {
		return codigoReserva;
	}

	public void setCodigoReserva(int codigoReserva) {
		this.codigoReserva = codigoReserva;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getFechaDía() {
		return fechaDía;
	}

	public void setFechaDía(int fechaDía) {
		this.fechaDía = fechaDía;
	}

	public int getFechaMes() {
		return fechaMes;
	}

	public void setFechaMes(int fechaMes) {
		this.fechaMes = fechaMes;
	}

	public String getNombreAl() {
		return nombreAl;
	}

	public void setNombreAl(String nombreAl) {
		this.nombreAl = nombreAl;
	}

}
