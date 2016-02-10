package com.ndovado.standalone.frames;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ndovado.standalone.frames.MainFrame.ConsultaLogListener;
import com.ndovado.standalone.frames.MainFrame.GestioneServiziListener;

public class ServicesFrame extends JFrame {

	/**
	 * 
	 */
	private String titolo = "Pannello Servizi";
	private JPanel panel = new JPanel();
	
	private static final long serialVersionUID = 1L;
	
	public ServicesFrame() {
		JFrame servicesFrame = new JFrame(titolo);
		servicesFrame.setSize(800, 600);
		servicesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		servicesFrame.getContentPane().add(panel);
		placeComponents(panel);

		servicesFrame.setVisible(true);
	}
	
	private void placeComponents(JPanel panel) {
		panel.setLayout(null);
		//TODO implementare interfaccia Servizio Comune
		
		
	}

}
