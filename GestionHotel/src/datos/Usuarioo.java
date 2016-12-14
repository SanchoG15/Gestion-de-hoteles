package datos;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuarioo implements Serializable {
	private static final long serialVersionUID = 56302426476654346L;

	private String Usuario;
	private String nombre;
	private String DNI;
	private String contrasenia;

	private int id;
	
	//Constructor
	public String getUsusario() {
		return Usuario;
	}

	// Constructor con parametros
	public Usuarioo(String Ususario, String nombre, String DNI, String contrasenia, int Id) {
		this.Usuario = Usuario;
		this.nombre = nombre;
		this.DNI = DNI;
		this.contrasenia = contrasenia;
		this.id = Id;
	}

	// constructor vacio
	public Usuarioo() {
		
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDNI() {
		return this.DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public static void getlistaUsuarios(String[] temporal) {

	}

}
