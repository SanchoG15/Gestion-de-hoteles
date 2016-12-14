package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import utilidades.GestionFicheros;
import datos.BD;
import datos.Usuarioo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.SystemColor;
import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class VentanaRegistro extends JPanel implements ActionListener{

	private JFrame frame;
	public static JTextField textField;
	public static JTextField textField_1;
	public static JTextField textField_4;
	private JButton btnNewButton;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblNewLabel;
	public static JTextField textField_5;
	private JLabel lblUsuario;
	private Usuarioo usuario, nombre, DNI, contrasenia;
	private ArrayList<Usuarioo> listaUsuarios;
	private BD bd;

	/**
	 * Launch the application.
	 *//**
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro window = new VentanaRegistro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	*/
	public VentanaRegistro(BD bd) {
		this.bd = bd;
		initialize();
	}

	// Contenido del panel

	private void initialize() {
		
		
		frame = new JFrame("Introduzca sus datos personales");
		frame.setSize(300, 300);
		frame.setVisible(true);
		
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		textField = new JTextField();
		textField.setBounds(117, 49, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(117, 84, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(117, 117, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		btnNewButton = new JButton("Registro");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setBounds(275, 188, 89, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(24, 52, 57, 14);
		frame.getContentPane().add(lblNombre);
		
		lblApellido = new JLabel("DNI:");
		lblApellido.setBounds(24, 87, 57, 14);
		frame.getContentPane().add(lblApellido);
		
		lblNewLabel = new JLabel("Contrase\u00F1a:");
		lblNewLabel.setBounds(24, 112, 69, 31);
		frame.getContentPane().add(lblNewLabel);
		
		textField_5 = new JTextField();
		textField_5.setBounds(117, 18, 86, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(24, 21, 57, 14);
		frame.getContentPane().add(lblUsuario);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton) {
			if(textField.getText().equals("")){
				JOptionPane.showMessageDialog(null,"Debes introducir tu nombre.");
				return;
			}
			if(textField_1.getText().equals("")){
				JOptionPane.showMessageDialog(null,"Debes introducir tu DNI.");
				return;
			}
			if(textField_4.getText().equals("")){
				JOptionPane.showMessageDialog(null,"Debes introducir tu contraseña.");
				return;
			}
			if(textField_5.getText().equals("")){
				JOptionPane.showMessageDialog(null,"Debes introducir tu nombre de usuario.");
				return;
			}
			String sql = "INSERT INTO USUARIOO(usuario,contrasenia,nombre,dni) VALUES('"+textField_5.getText() + "','"+textField_4.getText() + "','"+textField.getText() + "','"+textField_1.getText() + "')";
			System.out.println(sql);
			try {
				bd.getOrden().execute(sql);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			frame.setVisible(false);
		//Guarda en el fichero el usuario introducido
		try {
			
			GestionFicheros.escribirFichero();
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		VentanaPrincipal vP= new VentanaPrincipal();
		vP.getJfM().setVisible(true);
		}
		
		
	}
	
	
}


