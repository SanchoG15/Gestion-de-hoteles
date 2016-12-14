package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;

import java.awt.Color;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollBar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractListModel;
import javax.swing.JTextArea;

import Alojamientos.Apartamento;
import Alojamientos.Hotel;
import Hilos.Cronometro;
import datos.BD;
import datos.Usuarioo;
import utilidades.GestionFicheros;

public class VentanaReserva extends JPanel implements ActionListener {

	private JFrame frame;
	private JButton btnMiReserva, btnVolver;
	private JList list;
	private JButton btnNewButton;
	private JButton btnReservar;
	private JLabel lblCalendario, lblIntroduceElN, lblDas, lblMes,
			lblSeleccioneElHotel;
	private JButton btnMisReservas;
	private JComboBox<String> comboBox_1, comboBox, comboBox_2;
	private Thread t ;
	private Usuarioo usuario;
	private BD bd;
	private int botonPulsado;


	/**
	 * Launch the application.
	 */
	/**
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaReserva window = new VentanaReserva();
					window.setVisible(true);
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
	public VentanaReserva(Usuarioo usuario, BD bd) {
		initialize();
		this.usuario = usuario;
		this.bd = bd;
		botonPulsado=0;
		frame.setTitle("¡¡Elija su destino favorito, "+usuario.getNombre() +"!!");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setSize(400, 400);
		frame.setVisible(true);

		frame.setBounds(250, 250, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnReservar = new JButton("Reservar");
		btnReservar.setBackground(Color.GREEN);
		btnReservar.setBounds(295, 241, 98, 29);
		frame.getContentPane().add(btnReservar);
		btnReservar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				t.interrupt();
				t = (new Thread(new Cronometro(frame)));

				t.start();
				JOptionPane.showMessageDialog(null,
						"Operación realizada correctamente");
				try {

					GestionFicheros.escribirFichero2(
							(String) list.getSelectedValue(),
							(String) comboBox_1.getSelectedItem(),
							(String) comboBox_2.getSelectedItem(),
							(String) comboBox.getSelectedItem(),
							usuario);
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		lblCalendario = new JLabel("Calendario:");
		lblCalendario.setBounds(295, 11, 69, 14);
		frame.getContentPane().add(lblCalendario);

		lblDas = new JLabel("D\u00EDas");
		lblDas.setBounds(295, 36, 46, 14);
		frame.getContentPane().add(lblDas);

		lblMes = new JLabel("Mes");
		lblMes.setBounds(360, 36, 46, 14);
		frame.getContentPane().add(lblMes);

		lblIntroduceElN = new JLabel("Introduce la ciudad deseada\r\n");
		lblIntroduceElN.setBounds(298, 135, 172, 14);
		frame.getContentPane().add(lblIntroduceElN);

		lblSeleccioneElHotel = new JLabel(
				"Seleccione el Hotel/ Apartamento deseado:");
		lblSeleccioneElHotel.setBounds(10, 82, 245, 29);
		frame.getContentPane().add(lblSeleccioneElHotel);

		btnMisReservas = new JButton("Mis reservas");
		btnMisReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				t.interrupt();
				t = (new Thread(new Cronometro(frame)));
				t.start();
				VentanaMisReservas vMreserv = new VentanaMisReservas(usuario,bd);
				vMreserv.getFrame().setVisible(true);
			}
		});
		btnMisReservas.setBounds(66, 32, 119, 23);
		frame.getContentPane().add(btnMisReservas);
		// Introduzcimos en los combobox un array de String
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Madrid",
				"Barcelona", "Paris", "Bilbao", "Berlin", "Milan", "Varsovia",
				"Londres", "Glasgow", "Amsterdam", "Lisboa", "Roma",
				"Helsinki", "Praga", "Bruselas", "Moscu", "Atenas",
				"Viena", "Zurich", "Munich" }));
		comboBox.setToolTipText("");
		comboBox.setBounds(325, 160, 89, 20);
		comboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//JOptionPane.showMessageDialog(null,comboBox.getSelectedItem(),"WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
				if(botonPulsado!=0){
					String tipo="";
					if(botonPulsado==1){
						tipo="APARTAMENTO";
					}
					if(botonPulsado==2){
						tipo="HOTEL";
					}
					DefaultListModel model = new DefaultListModel();
					list.setModel(model);
					String sql = "SELECT ID_ALOJAMIENTO,NOMBRE FROM Alojamiento WHERE TIPO = '"+tipo+"' and PAIS = '"+comboBox.getSelectedItem()+"'";
					System.out.println(sql);
					try {
						ResultSet rs = bd.getOrden().executeQuery(sql);
						while(rs.next()){
							model.addElement(rs.getInt("ID_ALOJAMIENTO")+"- "+rs.getString("NOMBRE"));					}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		frame.getContentPane().add(comboBox);
		

		comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] { "1", "2",
				"3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
				"14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
				"24", "25", "26", "27", "28", "29", "30", "31" }));
		comboBox_1.setBounds(295, 55, 52, 23);
		frame.getContentPane().add(comboBox_1);

		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
				"3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		comboBox_2.setBounds(360, 55, 52, 23);
		frame.getContentPane().add(comboBox_2);

		JButton btnVolver_1 = new JButton("Volver");
		btnVolver_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.interrupt();
				frame.setVisible(false);
				VentanaPrincipal pV = new VentanaPrincipal();
				pV.getJfM().setVisible(true);
			}
		});
		btnVolver_1.setBounds(410, 313, 82, 23);
		frame.getContentPane().add(btnVolver_1);

		list = new JList();
		list.setBounds(20, 162, 225, 174);
		frame.getContentPane().add(list);
		// Cuando pulsamos el boton apartamento se muestra en el jlist el array
		// de apartementos
		JButton btnNewButton_1 = new JButton("Apartamento");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonPulsado=1;//Apartamento 1.
				DefaultListModel model = new DefaultListModel();
				list.setModel(model);
				//JOptionPane.showMessageDialog(null,comboBox.getSelectedItem(),"WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
				//for (int i = 0; i < Apartamento.getApartamentos().length; i++)
					//model.addElement(Apartamento.getApartamentos()[i]);
				String sql = "SELECT ID_ALOJAMIENTO,NOMBRE FROM Alojamiento WHERE TIPO = 'APARTAMENTO' and PAIS = '"+comboBox.getSelectedItem()+"'";
				System.out.println(sql);
				try {
					ResultSet rs = bd.getOrden().executeQuery(sql);
					while(rs.next()){
						model.addElement(rs.getInt("ID_ALOJAMIENTO")+"- "+rs.getString("NOMBRE"));					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton_1.setBounds(109, 122, 119, 23);
		frame.getContentPane().add(btnNewButton_1);
		// Cuando pulsamos el boton hotel se muestra en el jlist el array de
		// hoteles
		JButton btnNewButton = new JButton("Hotel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				botonPulsado=2;//Hotel 2;
				DefaultListModel model = new DefaultListModel();
				list.setModel(model);
				//for (int i = 0; i < Hotel.getHoteles().length; i++)
				//	model.addElement(Hotel.getHoteles()[i]);
				String sql = "SELECT ID_ALOJAMIENTO,NOMBRE FROM Alojamiento WHERE TIPO = 'HOTEL' and PAIS = '"+comboBox.getSelectedItem()+"'";
				System.out.println(sql);
				try {
					ResultSet rs = bd.getOrden().executeQuery(sql);
					while(rs.next()){
						model.addElement(rs.getInt("ID_ALOJAMIENTO")+"- "+rs.getString("NOMBRE"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 122, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		//Cuando abramos la clase se inicie el Cronometro
		t = (new Thread(new Cronometro(frame)));
		t.start();
	}

	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
