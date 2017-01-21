package Ventanas;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JSpinner;

import utilidades.GestionFicheros;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JCheckBox;

import datos.BD;
import datos.GestionReservas;
import datos.IGestionReservas;
import datos.Usuarioo;
import Alojamientos.Apartamento;
import Hilos.Cronometro;

public class VentanaMisReservas extends JFrame implements ActionListener {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JButton btnNewButton, btnAceptar;
	private Usuarioo usuario;
	private BD bd;
	JList list;
	DefaultListModel modelo;

	// Constructor al que pasamos el usuario del que dependen las reservas y la
	// bd donde estan almacenadas.
	public VentanaMisReservas(Usuarioo usuario, BD bd) {
		this.usuario = usuario;
		this.bd = bd;
		modelo = new DefaultListModel();
		initialize();
	}

	private void initialize() {

		// Gestion de la ventana.
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblNewLabel = new JLabel("Reserva:");
		lblNewLabel.setBounds(45, 11, 84, 30);
		frame.getContentPane().add(lblNewLabel);

		// Boton conel que se borran las reservas.
		btnNewButton = new JButton("Borrar reserva");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				// Selecciona la reserva que quiere borrar.
				ElementoReserva er = (ElementoReserva) list.getSelectedValue();

				// Gestion de la consulta de borrado de reserva.
				String sql = "DELETE FROM Reservas WHERE ID_RESERVA =" + er.getId_res();
				try {
					// Ejecuta la consulta para borrar en la bd.
					bd.getOrden().executeUpdate(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
					// Borra el elemento seleccionado de la lista.
				}
				modelo.removeElement(er);
				// Refresca la lista cuando ha habido una cancelacion.
				sql = "SELECT * FROM ALOJAMIENTO WHERE ID_ALOJAMIENTO = " + er.getId_alojamiento();
				ResultSet rs = null;
				int ocupacion = 0;
				try {
					// Conecta con la bd para refrescar la lista.
					rs = bd.ejecutarConsulta(sql);

					if (rs.next()) {
						ocupacion = rs.getInt("NUM_OCUPADAS");
					} else {

						JOptionPane.showMessageDialog(null,
								"Ha ocurrido un error a la hora de actualizar el alojamiento.");
					}
					// Corta la conexion con la bd.
					bd.terminarConsulta(rs);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// Actualiza la ocupacionen los alojamientos despues de una
				// cancelacion de reserva.
				ocupacion--;
				if (ocupacion < 0)
					ocupacion = 0;
				// Ejecuta la actualizacion en la bd.
				sql = "UPDATE ALOJAMIENTO SET NUM_OCUPADAS=" + ocupacion + " WHERE ID_ALOJAMIENTO ="
						+ er.getId_alojamiento();
				try {
					bd.getOrden().executeUpdate(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		// Boton aceptar para volver de la ventanaMisReservas a la
		// ventanaReservas.
		btnNewButton.setBounds(480, 183, 128, 23);
		frame.getContentPane().add(btnNewButton);
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			// Action listener del boton aceptar.
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				VentanaReserva vR = new VentanaReserva(usuario, bd);
				vR.getFrame().setVisible(true);
			}
		});
		btnAceptar.setBounds(480, 100, 91, 23);
		frame.getContentPane().add(btnAceptar);

		// Diseña la lista de la ventanaMisReservas.
		list = new JList();
		list.setModel(modelo);
		list.setBounds(32, 52, 211, 179);
		int j = 0;
		// Gestiona la consulta para obtener las reservas cuando el usuario
		// entra a la ventanaMisReservas.
		String sql = "SELECT * FROM RESERVAS WHERE ID_USUARIOO = " + usuario.getId();
		ElementoReserva er = null;
		int idAloj, idReserva, idUsuario;
		String nomHotel = "", ciudad = "", fechaIni = "", fechaFin = "";
		double precio = 0;
		ResultSet rs = null;
		try {
			// Conecta con la bd para ejecutar la consulta.
			rs = bd.ejecutarConsulta(sql);

			int contador = 0;
			while (rs.next()) {

				// Lleva la cuenta de las reservas que hay.
				contador++;
				// Indica los datos de las reservas del usuario.
				idAloj = rs.getInt("ID_ALOJAMIENTO");
				idReserva = rs.getInt("ID_RESERVA");
				idUsuario = rs.getInt("ID_USUARIOO");
				fechaIni = rs.getString("FECHA_INI");
				fechaFin = rs.getString("FECHA_FIN");

				// Hace una segunda consulta a Alojamiento para obtener el
				// nombre, la ciudad y el precio.
				String sql2 = "SELECT * FROM ALOJAMIENTO WHERE ID_ALOJAMIENTO =" + idAloj;
				ResultSet rs2 = bd.ejecutarConsulta(sql2);

				if (rs2.next()) {
					nomHotel = rs2.getString("NOMBRE");
					ciudad = rs2.getString("PAIS");
					precio = rs2.getDouble("PRECIO");
				}
				// Corta la conexion con la bd despues de la consulta.
				bd.terminarConsulta(rs2);

				// Calcula el precio total de la reserva.
				SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
				Date fi, ff;
				try {
					fi = (Date) format.parse(fechaIni);
					ff = (Date) format.parse(fechaFin);
					long diff = ff.getTime() - fi.getTime();
					int dias = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
					precio = dias * precio;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Añade las reservas con todos los datos al modelo de la lista.
				er = new ElementoReserva(idReserva, idAloj, idUsuario, fechaIni, fechaFin, ciudad, nomHotel, precio);
				modelo.addElement(er);
			}
			// Cuando no hay reservas sale vacio.
			if (contador == 0) {
				modelo.addElement("No hay ninguna reserva.");
				System.out.println("sin reservas");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Termina la conexion despues de finalizar la consulta.
		bd.terminarConsulta(rs);

		// Alade la lista a la ventana y le da tamaño.
		list.setBounds(32, 52, 411, 179);
		frame.getContentPane().add(list);
		this.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}
