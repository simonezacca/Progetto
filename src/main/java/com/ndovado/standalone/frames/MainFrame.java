package com.ndovado.standalone.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static String titolo = "Pannello Principale";
	private JPanel panel = new JPanel();

	private JButton btnGestioneServizi;
	private JButton btnConsultaLog;

	public MainFrame() {

		super(titolo);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().add(panel);
		placeComponents(panel);
		close();

		this.setVisible(true);
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

	public class GestioneServiziListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unused")
			ServicesFrame sf = new ServicesFrame();
		}
	}

	public class ConsultaLogListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unused")
			LogFrame lf = new LogFrame();
		}
	}

	public void close(){
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		    	Integer risposta = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler chiudere l'applicazione?", "Stai per chiudere l'applicazione", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		        if ( risposta == JOptionPane.YES_OPTION) {
		        	 LoginFrame loginFrame = new LoginFrame();
				     loginFrame.setVisible(true);
		        	}
			   
		    }
		});
	}
}
