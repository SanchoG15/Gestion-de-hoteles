package utilidades;

import java.awt.LayoutManager;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ListModel;

import Ventanas.VentanaRegistro;
import Ventanas.VentanaReserva;
import datos.GestionReservas;
import datos.Usuarioo;
import datos.Usuarioo;

public class GestionFicheros {

	// Constructor vacio
	public GestionFicheros() {

	}

	// Metodo que lee el fichero de usuarios
	public static ArrayList<Usuarioo> leerFichero() {
		ArrayList<Usuarioo> usuario = new ArrayList<Usuarioo>();

		File fichero = new File("Usuario.txt");
		Scanner s = null;

		try {
			// Leemos el contenido del fichero
			s = new Scanner(fichero);

			// Leemos linea a linea el fichero
			while (s.hasNextLine()) {
				String tp = s.nextLine();

				String[] temporal = tp.split(",");
				Usuarioo u = new Usuarioo();
				u.setUsuario(temporal[0]);
				u.setNombre(temporal[1]);
				u.setDNI((temporal[2]));
				u.setContrasenia(temporal[3]);
				// Guardo el en arraylist usuario el contenido del array
				// temporal
				usuario.add(u);

				System.out.println(temporal.toString()); // Imprimimos la linea
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos el fichero tanto si la lectura ha sido correcta o no
			try {
				if (s != null)
					s.close();
			} catch (Exception ex2) {
				ex2.printStackTrace();
			}
		}
		return usuario;
	}

	// Metodo que escribe en el fichero de usuarios
	public static void escribirFichero() {

		try {
			FileWriter fichero = new FileWriter("Usuario.txt", true);

			fichero.write(VentanaRegistro.textField_5.getText() + "," + VentanaRegistro.textField.getText() + ","
					+ VentanaRegistro.textField_1.getText() + "," + VentanaRegistro.textField_4.getText() + "\r\n");
			fichero.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Metodo que lee el contenido del fichero Reservas
	public static ArrayList<GestionReservas> leerFichero2() {
		ArrayList<GestionReservas> reserva = new ArrayList<GestionReservas>();

		File fichero = new File("Reservas.txt");
		Scanner s = null;

		try {

			// Leemos el contenido del fichero
			s = new Scanner(fichero);
			// Leemos linea a linea el fichero
			while (s.hasNextLine()) {
				String tp = s.nextLine();

				String[] temporal = tp.split("/");
				GestionReservas gR = new GestionReservas();

				gR.setNombreAl((temporal[0]));
				gR.setFechaMes(Integer.parseInt(temporal[1]));
				gR.setFechaDía(Integer.parseInt(temporal[2]));
				gR.setPais(temporal[3]);
				// Guardo en el arraylist reserva el contenido del array
				// temporal
				reserva.add(gR);

				System.out.println(temporal.toString()); // Imprimimos la linea
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos el fichero tanto si la lectura ha sido correcta o no
			try {
				if (s != null)
					s.close();
			} catch (Exception ex2) {
				ex2.printStackTrace();
			}
		}
		return reserva;
	}

	// Metodo que escribe en el fichero Reservas
	public static void escribirFichero2(String hotel, String dia, String mes, String pais, Usuarioo usuario) {

		try {
			FileWriter fichero = new FileWriter("Reservas"+usuario.getUsuario()+".txt", true);

			fichero.write(hotel + "/" + dia + "/" + mes + "/" + pais + "\r\n");
			fichero.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
