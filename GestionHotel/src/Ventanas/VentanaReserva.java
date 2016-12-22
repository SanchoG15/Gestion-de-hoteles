package Ventanas;

import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;

import java.awt.Color;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JScrollBar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractListModel;
import javax.swing.JTextArea;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Alojamientos.Apartamento;
import Alojamientos.Hotel;
import Hilos.Cronometro;
import datos.BD;
import datos.Usuarioo;
import utilidades.GestionFicheros;

public class VentanaReserva extends JPanel implements ActionListener {
//TAREA INCLUIR CALENDARIO 
	private JFrame frame;
	private JButton btnMiReserva, btnVolver;
	private JList list;
	private JButton btnNewButton;
	private JButton btnReservar;
	private JLabel lblCalendario,lblCalendarioS, lblIntroduceElN, lblDas, lblMes,
			lblSeleccioneElHotel;
	private JButton btnMisReservas;
	private JComboBox<String> comboBox_1, comboBox, comboBox_2;
	private Thread t ;
	private Usuarioo usuario;
	private BD bd;
	private int botonPulsado;
	JDatePickerImpl datePicker;
	JDatePickerImpl datePicker1;

	public VentanaReserva(Usuarioo usuario, BD bd) {
		
		initialize();
		this.usuario = usuario;
		this.bd = bd;
		botonPulsado=0;
		frame.setTitle("¡¡Elija su destino favorito, "+usuario.getNombre() +"!!");
	}

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
				ElementoAlojamiento ea=(ElementoAlojamiento) list.getSelectedValue();
				int numHabitaciones, ocupacion;
				String sql= "SELECT * FROM ALOJAMIENTO WHERE ID_ALOJAMIENTO = "+ea.getId(); 
				try {
					ResultSet rs = bd.getOrden().executeQuery(sql);
					if (rs.next()){
						numHabitaciones= rs.getInt("NUM_HABITACIONES");
						ocupacion = rs.getInt("NUM_OCUPADAS");
						if(ocupacion>=numHabitaciones){
							JOptionPane.showMessageDialog(null,
									"El alojamiento esta completo, sentimos las molestias.\nNo se ha realizado la reserva.");
						}else{
							
							sql = "INSERT INTO Reservas(ID_ALOJAMIENTO,ID_USUARIOO,FECHA_INI,FECHA_FIN) VALUES("+ea.getId() + ","+usuario.getId() + ",'"+datePicker.getJFormattedTextField().getText() + "','"+datePicker1.getJFormattedTextField().getText()+ "')";
							System.out.println(sql);
							try {
								bd.getOrden().execute(sql);
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							ocupacion++;
							sql = "UPDATE ALOJAMIENTO SET NUM_OCUPADAS="+ocupacion+" WHERE ID_ALOJAMIENTO ="+ea.getId();
							System.out.println(sql);
							try {
								bd.getOrden().execute(sql);
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							//JOptionPane.showMessageDialog(null,ea.getId()+" "+ usuario.getId()+" "+datePicker.getJFormattedTextField().getText());
							JOptionPane.showMessageDialog(null,
									"Operación realizada correctamente"+numHabitaciones+"- "+ocupacion);
						}
					}else{
						JOptionPane.showMessageDialog(null,"Ha ocurrido un error a la hora de reservar el hotel.");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				while(rs.next()){
//					model.addElement(rs.getInt("ID_ALOJAMIENTO")+"- "+rs.getString("NOMBRE"));		
				
//				try {
//					
//					GestionFicheros.escribirFichero2(
//							(ElementoAlojamiento) list.getSelectedValue(),
//							(String) comboBox_1.getSelectedItem(),
//							(String) comboBox_2.getSelectedItem(),
//							(String) comboBox.getSelectedItem(),
//							usuario);
//				} catch (Throwable e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}

			}
		});

		lblCalendario = new JLabel("Fecha Entrada:");
		lblCalendario.setBounds(295, 11, 100, 14);
		frame.getContentPane().add(lblCalendario);
		
		//JDatePicker txtFechaIni = new JDatePicker();
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
		datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
		frame.getContentPane().add(datePicker);
		datePicker.setBounds(295, 30, 200, 60);
		datePicker.setVisible(true);
		
		lblCalendarioS = new JLabel("Fecha Salida:");
		lblCalendarioS.setBounds(295, 100, 100, 14);
		frame.getContentPane().add(lblCalendarioS);
		
		UtilDateModel model1 = new UtilDateModel();
		Properties p1 = new Properties();
		p1.put("text.today", "Today");
		p1.put("text.month", "Month");
		p1.put("text.year", "Year");
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1,p1);
		datePicker1 = new JDatePickerImpl(datePanel1,new DateLabelFormatter());
		frame.getContentPane().add(datePicker1);
		datePicker1.setBounds(295, 120, 200, 60);
		datePicker1.setVisible(true);
		
		lblIntroduceElN = new JLabel("Introduce la ciudad deseada:\r\n");
		lblIntroduceElN.setBounds(295, 170, 180, 40);
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
		comboBox.setBounds(295, 200, 100, 30);
	
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
					ElementoAlojamiento ea = null;
					try {
						ResultSet rs = bd.getOrden().executeQuery(sql);
						while(rs.next()){
							ea=new ElementoAlojamiento(rs.getInt("ID_ALOJAMIENTO"),rs.getString("NOMBRE"));
							model.addElement(ea);					}
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
		//frame.getContentPane().add(comboBox_1);

		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
				"3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		comboBox_2.setBounds(360, 55, 52, 23);
		//frame.getContentPane().add(comboBox_2);

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
				ElementoAlojamiento ea = null;
				try {
					ResultSet rs = bd.getOrden().executeQuery(sql);
					while(rs.next()){
						ea=new ElementoAlojamiento(rs.getInt("ID_ALOJAMIENTO"),rs.getString("NOMBRE"));
						model.addElement(ea);					}
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
				ElementoAlojamiento ea = null;
				try {
					ResultSet rs = bd.getOrden().executeQuery(sql);
					while(rs.next()){
						ea=new ElementoAlojamiento(rs.getInt("ID_ALOJAMIENTO"),rs.getString("NOMBRE"));
						model.addElement(ea);
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
