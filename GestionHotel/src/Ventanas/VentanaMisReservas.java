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
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JCheckBox;

import datos.BD;
import datos.GestionReservas;
import datos.IGestionReservas;
import datos.Usuarioo;
import Alojamientos.Apartamento;

public class VentanaMisReservas extends JFrame implements ActionListener {

	private JFrame frame;
	private JButton btnNewButton, btnAceptar;
	private JLabel lblNewLabel;
	JList list;
	private Usuarioo usuario;
	private BD bd;

	/**
	 * Launch the application.
	 */
	/**public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMisReservas window = new VentanaMisReservas();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 */
	public VentanaMisReservas(Usuarioo usuario, BD bd) {
		
		this.usuario = usuario;
		this.bd=bd;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblNewLabel = new JLabel("Reserva:");
		lblNewLabel.setBounds(45, 11, 84, 30);
		frame.getContentPane().add(lblNewLabel);

		btnNewButton = new JButton("Borrar reserva");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				IGestionReservas ig= new GestionReservas();
				ig.borrarReserva();
			}
		});
		btnNewButton.setBounds(280, 183, 128, 23);
		frame.getContentPane().add(btnNewButton);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				VentanaReserva vR = new VentanaReserva(usuario,bd);
				vR.getFrame().setVisible(true);

			}

		});
		btnAceptar.setBounds(280, 100, 91, 23);
		frame.getContentPane().add(btnAceptar);

		File fichero = new File("Reservas"+usuario.getUsuario()+".txt");
		Scanner s = null;
		String[] temporal = null;
		try {

			// Leemos el contenido del fichero
			s = new Scanner(fichero);
			list = new JList();
			DefaultListModel modelo = new DefaultListModel();
			
			list.setModel(modelo);
			//list = new JList();
			list.setBounds(32, 52, 211, 179);
			int j=0;
			
			// Leemos linea a linea el fichero
			while (s.hasNextLine()) {
				
				String tp = s.nextLine();
				temporal = tp.split("/");
				GestionReservas gR = new GestionReservas();
System.out.println("linea: "+tp);
				gR.setNombreAl((temporal[0]));
				gR.setFechaMes(Integer.parseInt(temporal[1]));
				gR.setFechaDía(Integer.parseInt(temporal[2]));
				gR.setPais(temporal[3]);
				modelo.add(j,tp);
				j++;
				System.out.println("temporal:"+temporal.toString()); // Imprimimos la linea
			}
		} catch (Exception ex) {
			System.out.println();
		} finally {
			// Cerramos el fichero tanto si la lectura ha sido correcta o no
			try {
				if (s != null)
					s.close();
			} catch (Exception ex2) {
				System.out.println();
			}
			// Introduzco en el Jlist el contenido del fichero
			if (temporal != null) {
				//JList list = new JList(temporal);
				//list.setBounds(32, 52, 211, 179);
				frame.getContentPane().add(list);
			}else{
				JLabel noHayReservas = new JLabel("NO HAY RESERVAS");
				noHayReservas.setBounds(32, 52, 211, 179);
				frame.getContentPane().add(noHayReservas);
				}
			this.setVisible(true);
		}

	}

	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
