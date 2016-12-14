package Hilos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Ventanas.VentanaPrincipal;
import Ventanas.VentanaReserva;

public class Cronometro implements Runnable {
	private static final long serialVersionUID = 134634533635537L;
	
	private int minutos;
	private int segundos;
	private boolean cronometroActivo;
	private JFrame frame2;

	// Constructor de cronometro
	public Cronometro(JFrame f) {
		frame2 = f;
		cronometroActivo = true;
	}

	//Hilo de gestion de eventos
	public void run() {
		try {
			// Mientras cronometroActivo sea true seguira aumentando el tiempo
			while (cronometroActivo == true) {
				segundos++;
				// Para que sume uno por cada segundo
				Thread.sleep(1000);

				System.out.println("segundos:" + segundos);
				if (segundos >= 60) {
					segundos = 0;
					minutos++;

				}
				// cuando el contador llega a un minuto da el aviso
				if (minutos >= 1) {
					cronometroActivo = false;
				}
			}
			if (cronometroActivo == false) {
				// Aviso de tiempo exedido, sale de la aplicacion
				JOptionPane.showMessageDialog(frame2, "Tiempo de reserva excedido", "WARNING_MESSAGE",
						JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
		} catch (Exception e) {
		}

	}

}
