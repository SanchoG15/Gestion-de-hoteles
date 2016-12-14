package Ventanas;

import java.awt.EventQueue;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;

import utilidades.GestionFicheros;
import datos.BD;
import datos.Usuarioo;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class VentanaRecuperarContraseña extends JPanel implements
		ActionListener {

	private JFrame frame;
	private JTextField textField;
	private JButton btnAceptar;
	private JLabel lblNewLabel;
	private JLabel lblUsuario;
	private JLabel lblNombre;
	private JTextField textField_1;
	private ArrayList<Usuarioo> listaUsuario= new ArrayList<Usuarioo>();
	private BD bd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRecuperarContraseña window = new VentanaRecuperarContraseña(new BD());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Metodo que comprueba el usuario y nombre y devuelve la contraseña de este
	public void devolverContraseña() {
		String sql = "SELECT * FROM USUARIOO WHERE USUARIO = '"+textField.getText()+"' and NOMBRE='"+textField_1.getText()+"'";
		try {
			ResultSet rs = bd.getOrden().executeQuery(sql);
			if(!rs.next()){
				JOptionPane.showMessageDialog(null,
					"El usuario no esta en el sistema.",
					"WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null,
						"La contraseña es "+rs.getString("CONTRASENIA"),
						"WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
			}
				
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
//		listaUsuario  = GestionFicheros.leerFichero();
//		
//		String contraseña = "";
//		for (Usuarioo usuario : listaUsuario) {
//			if (usuario.getUsuario().equals(textField.getText())
//					&& usuario.getNombre().equals(textField_1.getText())) {
//				
//				contraseña = usuario.getContrasenia();
//
//			}
//		}
//		return contraseña;

	}

	/**
	 * Create the application.
	 */
	public VentanaRecuperarContraseña(BD bd) {
		initialize();
		this.bd= bd;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame("Identificate para recivir la cotraseña");
		frame.getContentPane().setBackground(new Color(240, 240, 240));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(143, 55, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				VentanaPrincipal vP = new VentanaPrincipal();
				vP.getJfM().setVisible(true);
				devolverContraseña();
			}
		});
		btnAceptar.setBounds(159, 181, 89, 23);
		frame.getContentPane().add(btnAceptar);

		lblNewLabel = new JLabel("Introduce tus datos:");
		lblNewLabel.setBounds(119, 24, 167, 20);
		frame.getContentPane().add(lblNewLabel);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(63, 58, 59, 14);
		frame.getContentPane().add(lblUsuario);

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(63, 96, 70, 14);
		frame.getContentPane().add(lblNombre);

		textField_1 = new JTextField();
		textField_1.setBounds(143, 86, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public JFrame getFrame() {
		return frame;
	}

}
