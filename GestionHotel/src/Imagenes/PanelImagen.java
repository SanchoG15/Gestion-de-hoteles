package Imagenes;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

//Clase para itroducir imagenes en las ventanas
public class PanelImagen extends javax.swing.JPanel {
	String imagen = "";

	public PanelImagen(String imagen) {
		this.imagen = imagen;
		this.setSize(700, 500);
	}

	@Override
	public void paintComponent(Graphics g) {
		Dimension tamanio = getSize();
		ImageIcon imagenFondo = new ImageIcon(getClass().getResource("src/Imagenes/hoteles_destinia.jpg"));
		g.drawImage(imagenFondo.getImage(), 0, 0, tamanio.width, tamanio.height, null);
		setOpaque(false);
		super.paintComponent(g);
	}

}
