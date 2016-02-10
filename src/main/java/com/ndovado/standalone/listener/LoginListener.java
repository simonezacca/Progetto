package com.ndovado.standalone.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ndovado.standalone.controller.LoginController;
import com.ndovado.standalone.frames.MainFrame;
import com.ndovado.standalone.model.Amministratore;
import com.ndovado.tecservices.loggers.AppLogger;

public class LoginListener implements ActionListener {

	private LoginController controller = new LoginController();
	private JTextField userField;
	private JPasswordField passField;
	private JFrame loginFrame;
	
	Amministratore admin = new Amministratore();
	
	
	public LoginListener(JFrame lframe, JTextField userText, JPasswordField passwordText) {
		loginFrame = lframe;
		userField = userText;
		passField = passwordText;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		updateAdmin();
		AppLogger.debug("Verifico coppia credenziali login");
		if (controller.verificaCoppiaLogin(admin.getUsername(), admin.getPassword())) {
			// TODO chiudere all'apertura
			loginFrame.setVisible(false);
			MainFrame mframe = new MainFrame();
		} else {
			String title = "Login fallito";
			String message = "Credenziali di accesso non valide!";
			JOptionPane.showMessageDialog(loginFrame, message, title, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void updateAdmin() {
		admin.setUsername(userField.getText());
		admin.setPassword(passField.getText());
		AppLogger.debug("Aggiorno istanza amministratore: "+admin);
	}

}
