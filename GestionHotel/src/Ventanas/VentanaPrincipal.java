package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument.Content;

import Hilos.Cronometro;
import Imagenes.PanelImagen;
import datos.BD;
import datos.Usuarioo;
import utilidades.GestionFicheros;

import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VentanaPrincipal extends JPanel implements ActionListener {
	// Mejoras:
	//Comprobar que el usuario no exista al registarte
	

	private ArrayList<Usuarioo> listaUsuarios = new ArrayList<Usuarioo>();
	private JButton btnRegistrarse, btnSalir, btnInformación;
	public static JButton btnEntrar;
	private JButton btnNewButton;
	private JTextField txtContrasea;
	private JTextField txtUsuario;
	private JFrame jfM;
	private JLabel lblContrasea, lblUsusario;
	private BD bd;
	
	public VentanaPrincipal() {
		
		bd=new BD();
		jfM = new JFrame("Bienvenido a Taubmann`s Sleep in");
		jfM.getContentPane().setLayout(null);

		// Para que aparezca la imagen
		PanelImagen p = new PanelImagen("/Imagenes/hoteles_destinia.jpg");
		this.add(p, BorderLayout.CENTER);
		p.repaint();

		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBackground(UIManager.getColor("Button.darkShadow"));
		btnRegistrarse.addActionListener(this);
		btnSalir = new JButton("Salir");
		btnSalir.setBackground(UIManager.getColor("CheckBox.darkShadow"));

		btnSalir.addActionListener(this);
		btnInformación = new JButton("Información");
		btnInformación.setBackground(UIManager.getColor("Button.darkShadow"));
		btnInformación.addActionListener(this);

		btnEntrar = new JButton("Entrar");
		btnEntrar.setBackground(SystemColor.activeCaption);
		btnEntrar.addActionListener(this);

		btnRegistrarse.setBounds(293, 28, 133, 32);
		btnSalir.setBounds(375, 325, 150, 32);
		btnInformación.setBounds(108, 325, 133, 32);
		btnEntrar.setBounds(279, 175, 89, 23);

		jfM.getContentPane().add(btnRegistrarse);
		jfM.getContentPane().add(btnSalir);
		jfM.getContentPane().add(btnInformación);
		jfM.getContentPane().add(btnEntrar);

		txtContrasea = new JTextField();
		txtContrasea.setBounds(124, 156, 86, 20);
		jfM.getContentPane().add(txtContrasea);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(124, 110, 86, 20);
		jfM.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);

		lblUsusario = new JLabel("Ususario:");
		lblUsusario.setBounds(40, 101, 86, 38);
		jfM.getContentPane().add(lblUsusario);

		lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(27, 150, 87, 32);
		jfM.getContentPane().add(lblContrasea);

		btnNewButton = new JButton("\u00BFContrase\u00F1a olvidada?");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(72, 235, 183, 23);
		jfM.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jfM.setVisible(false);
				VentanaRecuperarContraseña vR = new VentanaRecuperarContraseña(bd);
				vR.getFrame().setVisible(true);

			}
		});

		jfM.setLocation(100, 50);
		jfM.setResizable(false);
		jfM.setVisible(true);
		jfM.setSize(650, 450);
		jfM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		VentanaPrincipal vp = new VentanaPrincipal();// uso de constructor para
														// la ventana

	}

	public void actionPerformed(ActionEvent e) {// sobreescribimos el metodo del
												// listener

		JButton botonPulsado = (JButton) e.getSource();
		if (botonPulsado.equals(btnRegistrarse)) {

			jfM.setVisible(false);
			new VentanaRegistro(bd);
		}

		if (botonPulsado.equals(btnInformación)) {

			new Información();
		}
		if (botonPulsado.equals(btnSalir)) {

			jfM.setVisible(false);
			System.exit(0);

		}
		if (botonPulsado.equals(btnEntrar)) {
			
			// Comparo el usuario introducido con el fichero si no coincide,
			// mensaje de error
			//listaUsuarios = GestionFicheros.leerFichero();
			boolean elemento = false;
//			for (Usuarioo usuario : listaUsuarios) {
//				if (usuario.getUsuario().equals(txtUsuario.getText())
//						&& usuario.getContrasenia().equals(
//								txtContrasea.getText())) {
//					jfM.setVisible(false);
//					new VentanaReserva(usuario,bd);
//					elemento = true;
//				}
//
//			}
			String sql = "SELECT * FROM USUARIOO WHERE USUARIO = '"+txtUsuario.getText()+"' and CONTRASENIA='"+txtContrasea.getText()+"'";
			try {
				ResultSet rs = bd.getOrden().executeQuery(sql);
				if(!rs.next()){
					JOptionPane.showMessageDialog(null,
						"El usuario/contraseña introducido es incorrecto",
						"WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
				}else{
					jfM.setVisible(false);
					Usuarioo u = new Usuarioo(rs.getString("USUARIO"),rs.getString("NOMBRE"),rs.getString("DNI"),rs.getString("CONTRASENIA"),rs.getInt("ID_USUARIOO"));
					new VentanaReserva(u,bd);
				}
					
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
				
		}
	}

	public JFrame getJfM() {
		return jfM;
	}
}
