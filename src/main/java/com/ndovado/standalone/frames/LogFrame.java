package com.ndovado.standalone.frames;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LogFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String titolo = "Pannello Log";
	
	private static final String logPathNdovado = "/tmp/com.ndovado.log";
	private static final String logPathHibernate = "/tmp/org.hibernate.log";
	private static final String logPathDozer = "/tmp/org.dozer.log";
	private static final String[] logPaths = {logPathNdovado,logPathHibernate,logPathDozer};
	
	
	// pannello superiore
	JPanel psup = new JPanel();
		//elementi pannello superiore
		// bottone carica file log
		JButton btnLoadLogFile = new JButton("Visualizza log");
		// combo drop down con il nome dei file
		JComboBox comboLogPaths = new JComboBox(logPaths);

	// pannello centrale
	JPanel pcen = new JPanel(new BorderLayout());
		// scrollable text area
		private JTextArea jtLog;
		private JScrollPane scrollLog;
			
		
	private void initGUI() {
		// superiore
		psup.add(btnLoadLogFile);
		psup.add(comboLogPaths);
		
		// init scroll e textarea
		jtLog = new JTextArea();
		scrollLog = new JScrollPane(jtLog, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		pcen.add(scrollLog);
		
		// container di tutto
		Container cont = this.getContentPane();
		cont.add(psup, BorderLayout.NORTH);
		cont.add(pcen, BorderLayout.CENTER);
		
		// grafico a schermo la jframe
		this.pack();
		this.setLocationRelativeTo(null);
		this.setSize(800, 600);
		this.setVisible(true);
		
	} 
	
	
	
	public LogFrame() {
		super(titolo);
		//JFrame logFrame = new JFrame(titolo);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		initGUI();
		initActionListeners();
	}
	
	
	private void loadLogIntoTextArea(String logPath) {
		 FileReader reader;
		 BufferedReader br;
		try {
			reader = new FileReader( logPath );
			br = new BufferedReader(reader);
	        jtLog.read( br, null );
	        jtLog.requestFocus();
	        br.close();
	        // vado in ultima riga
	        jtLog.setCaretPosition(jtLog.getDocument().getLength());
	        
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Errore: File log non esistente","Errore",JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			
			JOptionPane.showMessageDialog(null, "Errore durante il caricamento del file di log","Errore",JOptionPane.ERROR_MESSAGE);
		} 
	}
	
	private void initActionListeners() {
		btnLoadLogFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String currentLogPath = comboLogPaths.getSelectedItem().toString();
				loadLogIntoTextArea(currentLogPath);
				
			}
		});
	}

}
