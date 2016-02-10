package com.ndovado.standalone.frames;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ndovado.standalone.frames.MainFrame.ConsultaLogListener;
import com.ndovado.standalone.frames.MainFrame.GestioneServiziListener;

public class LogFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String titolo = "Pannello Log";
	private JPanel panel = new JPanel();
	
	public LogFrame() {
		
		JFrame logFrame = new JFrame(titolo);
		logFrame.setSize(800, 600);
		logFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logFrame.getContentPane().add(panel);
		placeComponents(panel);

		logFrame.setVisible(true);
	}
	
	private void placeComponents(JPanel panel) {
		panel.setLayout(null);
		//TODO implementare interfaccia logframe
		
	}
}
