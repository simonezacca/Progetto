package com.ndovado.standalone.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.bind.Marshaller.Listener;

import com.ndovado.standalone.listener.LoginListener;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String titolo = "Pannello Principale";
	private JPanel panel = new JPanel();
	
	private JButton btnGestioneServizi;
	private JButton btnConsultaLog;
	
	
	public MainFrame() {
		
		JFrame mainFrame = new JFrame(titolo);
		mainFrame.setSize(800, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().add(panel);
		placeComponents(panel);

		mainFrame.setVisible(true);
	}
	
	private void placeComponents(JPanel panel) {
		panel.setLayout(null);
		
		btnGestioneServizi = new JButton("Gestione Servizi");
		btnGestioneServizi.setSize(171, 100);
		btnGestioneServizi.setLocation(170, 200);
		GestioneServiziListener gestioneServiziListener = new GestioneServiziListener(); 
		btnGestioneServizi.addActionListener(gestioneServiziListener);
		panel.add(btnGestioneServizi);
		
		btnConsultaLog = new JButton("Consulta Log");
		btnConsultaLog.setLocation(471, 200);
		btnConsultaLog.setSize(171, 100);
		ConsultaLogListener visualizzaLogListener = new ConsultaLogListener();
		btnConsultaLog.addActionListener(visualizzaLogListener);
		panel.add(btnConsultaLog);
		
		
	}
	
	public class GestioneServiziListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			ServicesFrame sf = new ServicesFrame();
			
		}
		
	}
	
	public class ConsultaLogListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			LogFrame lf = new LogFrame();
			
		}
		
	}

}

