package com.ndovado.standalone.frames;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ndovado.standalone.controller.AdminController;
import com.ndovado.standalone.model.Amministratore;
import com.ndovado.tecservices.loggers.AppLogger;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L; 
	
	private AdminController controller = new AdminController();
	Amministratore adminLocale = controller.getAdminCorrente();
	
	
	private final static String titolo = "Login";
	private final JLabel infoLabel = new JLabel("Effettua il Login");
	private final JPanel panel = new JPanel();
	
	// componenti grafici
	JLabel userLabel;
	JTextField userText;
	JLabel passwordLabel;
	JPasswordField passwordText;
	JButton btnRegister;
	JButton btnLogin;
	
	
	

	public LoginFrame() {
		super(titolo);
		
		this.setSize(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		placeComponents(panel);
		centerFrame();
		addActionListener();
		

		this.setVisible(true);
	}

	private void placeComponents(JPanel panel) {

		panel.setLayout(null);

		// Etichetta utente
		userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		// Campo inserimento nome utente
		userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		// Etichetta password
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		// Campo inserimento password
		passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);

		// Bottone per registrarsi
		btnRegister = new JButton("Registra");
		btnRegister.setBounds(180, 80, 80, 25);
		panel.add(btnRegister);
		
		// Bottone per il login
		btnLogin = new JButton("Login");
		btnLogin.setBounds(10, 80, 80, 25);
		//btnLoginButton.addActionListener(new LoginListener(this,userText,passwordText));
		panel.add(btnLogin);
		
	}
	
	private void centerFrame() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getWidth()/2, dim.height/2-this.getHeight()/2);
	}
	
	private void updateAdminFields() {
		adminLocale.setUsername(userText.getText());
		adminLocale.setPassword(passwordText.getText());
		AppLogger.debug("Aggiorno istanza amministratore: "+adminLocale);
	}
	
	private void addActionListener() {
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateAdminFields();
				AppLogger.debug("Verifico coppia credenziali login");
				if (controller.verificaCoppiaLogin(adminLocale.getUsername(), adminLocale.getPassword())) {
					// TODO chiudere all'apertura
					//this.setVisible(false);
					@SuppressWarnings("unused")
					MainFrame mframe = new MainFrame();
				} else {
					String title = "Login fallito";
					String message = "Credenziali di accesso non valide!";
					JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateAdminFields();
				controller.serializza(adminLocale);
			}
		});
	}

}

