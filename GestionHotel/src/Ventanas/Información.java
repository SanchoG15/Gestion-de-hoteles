package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Informaci�n extends JDialog implements ActionListener {

	private Informaci�n ventana = this;
	private final JPanel panelInformaci�n = new JPanel();

	public Informaci�n() {
		setTitle("Informaci�n");
		setBounds(100, 100, 600, 465);

		// Indica las coordenadas de el borde de el panel
		getContentPane().setLayout(new BorderLayout());
		panelInformaci�n.setLayout(new FlowLayout());
		panelInformaci�n.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelInformaci�n, BorderLayout.CENTER);
		{
			// Dise�o del panel de informaion.
			JScrollPane scrollPane = new JScrollPane();
			JTextArea textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setBackground(new Color(250, 250, 250));
			// El tipo de letra
			textArea.setFont(new Font("Times New Roman", Font.BOLD, 15));
			scrollPane.setViewportView(textArea);
			// Largura del jpanel
			textArea.setRows(30);
			// Ancho de jpanel
			textArea.setColumns(50);
			panelInformaci�n.add(scrollPane);
			textArea.setText("\n                                                            \n\n"
					+ "                �Cansado de buscar alojamiento y no encontrar nada? \n" + "\n"
					+ "                En esta nuestra compa�ia tratamos de facilitar la labor de todos nuestros \n"
					+ "                usuarios, para ello te facilitamos la busqueda de hoteles y apartamentos \n"
					+ "                contando con un gran numero de inumerables destinos al mejor precio. \n"
					+ "                Garantizado!!\n" + "\n"
					+ "                Registrate ya y disfruta de las reservas en los mejores hoteles y \n"
					+ "                alojamientos de europa!!!\n" + "\n"
					+ "                Para cualquier duda pongase en contacto con atencion al cliente:\n"
					+ "                901456839 (numero de pago)\n");

			textArea.setCaretPosition(0);
		}
		{
			/** Creamos ademas de el panel, el boton OK.
			*   Para cuando hayamos terminado de leer la ayuda cerrar el panel.
			*/ 
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("ok");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		setVisible(true);
	}

	// actionPerformed que cierra la ventana de informacion.
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("ok")) {
			ventana.dispose();
		}
	}

}
