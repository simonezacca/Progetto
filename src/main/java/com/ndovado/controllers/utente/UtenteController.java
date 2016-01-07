package com.ndovado.controllers.utente;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ndovado.dominio.core.ARuolo;
import com.ndovado.dominio.core.Utente;
import com.ndovado.tecservices.persistenza.base.ServizioPersistenzaBase;


public class UtenteController {
	
	public UtenteController() {
		
	}
	
	private static SessionFactory sf = ServizioPersistenzaBase.getSessionFactory();

	public static boolean verificaEsistenzaMail(String mailAddress) {
		if (mailAddress!=null) {
			Session session = sf.openSession();
			Query query = session.getNamedQuery("cercaUtentePerMail").setString("mail", mailAddress);

			Integer occorrenze = query.list().size();
			session.close();
			return occorrenze>0 ? true : false;
		}
		return false;
	}
	
	public static boolean verificaCredenzialiUtente(String username, String password) {
		if (username!=null && password!=null) {
			Session session = sf.openSession();
			Query query = session.getNamedQuery("verificaCredenzialiUtente")
								.setString("mail", username)
								.setString("password", password);

			Integer occorrenze = query.list().size();
			session.close();
			return occorrenze>0 ? true : false;
		}
		return false;
	}
	
	public static Utente creaNuovoUtente(String aCognome, String aNome, String aMail, String aPassword, Integer ruoloCode) {
		Utente u = new Utente(aCognome, aNome);
		u.setMail(aMail);
		u.setPassword(aPassword);
		if (ruoloCode.equals(new Integer(1))) {
			// caso gestore - ruoloCode == 1
			u.setRuolo(ARuolo.getRuoloGestore());
		} else {
			// caso locatario - ruoloCode == 0
			u.setRuolo(ARuolo.getRuoloLocatario());
		}
		// rendo persistente il nuovo utente creato
		ServizioPersistenzaBase.<Utente>saveOrUpdate(u);
		return u;
	}
	
	public static TokenAutenticazioneUtente creaTokenAutenticazione(Utente aUtente) {
		return new TokenAutenticazioneUtente(aUtente, aUtente.getRuolo());
	}
}
