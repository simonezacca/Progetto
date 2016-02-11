package com.ndovado.standalone.controller;

import javax.swing.JOptionPane;

import com.ndovado.standalone.exceptions.SerializzazioneException;
import com.ndovado.standalone.model.Amministratore;
import com.ndovado.standalone.serialization.PersistenzaFile;

public class AdminController {
	
	private PersistenzaFile pf;
	private static final String serializationPath = "/tmp/com.ndovado.standalone.admins";
	
	private Amministratore adminCorrente;
	
	public AdminController() {
		adminCorrente = new Amministratore();
		pf = new PersistenzaFile(serializationPath);
	}
	
	public void serializza(Amministratore admin) {
		try {
			adminCorrente = admin;
			pf.serializza(admin);
			JOptionPane.showMessageDialog(null, "Serializzazione effettuata correttamente", "Serializzazione", JOptionPane.INFORMATION_MESSAGE);
		} catch (SerializzazioneException e1) {
			JOptionPane.showMessageDialog(null, "Errore durante la serializzazione del file", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Amministratore deserializza() {
		try {
			adminCorrente = (Amministratore) pf.deserializza();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Errore durante la deserializzazione del file", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		return adminCorrente;
	}

	
	public Boolean verificaCoppiaLogin(String username, String password) {
		deserializza();
		if (adminCorrente.isValid()) {
				return adminCorrente.getUsername().equals(username) && adminCorrente.getPassword().equals(password);	
		}
		else return false;
		
	}

	/**
	 * @return the adminCorrente
	 */
	public Amministratore getAdminCorrente() {
		return adminCorrente;
	}

}
