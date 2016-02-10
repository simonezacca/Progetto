package com.ndovado.standalone.frames;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ndovado.standalone.listener.LoginListener;
import com.ndovado.standalone.model.Amministratore;
import com.ndovado.tecservices.loggers.AppLogger;

import java.awt.Container;
import java.awt.GridLayout;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private final String titolo = "Login";
	private final JLabel infoLabel = new JLabel("Effettua il Login");
	private final JPanel panel = new JPanel();

	public LoginFrame() {
		JFrame loginFrame = new JFrame();
		loginFrame.setSize(300, 200);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.add(panel);
		placeComponents(panel);

		loginFrame.setVisible(true);
	}

	private void placeComponents(JPanel panel) {

		panel.setLayout(null);

		// Etichetta utente
		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		// Campo inserimento nome utente
		JTextField userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		// Etichetta password
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		// Campo inserimento password
		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);

		// Bottone per registrarsi
		JButton registerButton = new JButton("Sign");
		registerButton.setBounds(180, 80, 80, 25);
		panel.add(registerButton);
		
		// Bottone per il login
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(10, 80, 80, 25);
		loginButton.addActionListener(new LoginListener(this,userText,passwordText));
		panel.add(loginButton);
		
	}


}

