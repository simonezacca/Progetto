package com.ndovado.controllers.utente;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ndovado.dominio.core.ARuolo;
import com.ndovado.dominio.core.Utente;
import com.ndovado.tecservices.persistenza.base.ServizioPersistenzaBase;
import com.ndovado.webapp.bean.UtenteBean;


public class UtenteControllerDominio {
	
	public UtenteControllerDominio() {
		
	}
	
	private static SessionFactory sf = ServizioPersistenzaBase.getSessionFactory();

	public static boolean esisteIndirizzoMail(String mailAddress) {
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
	
	public static Utente creaNuovoUtente(String aCognome, String aNome, String aMail, String aPassword, String ruoloCode) {
		Utente u = new Utente(aCognome, aNome);
		u.setMail(aMail);
		u.setPassword(aPassword);
		if (ruoloCode.equals("gestore")) {
			u.setRuolo(ARuolo.getRuoloGestore());
		} else {
			u.setRuolo(ARuolo.getRuoloLocatario());
		}
		// rendo persistente il nuovo utente creato
		ServizioPersistenzaBase.<Utente>saveOrUpdate(u);
		return u;
	}
	
	public static TokenAutenticazioneUtente creaTokenAutenticazione(Utente aUtente) {
		return new TokenAutenticazioneUtente(aUtente, aUtente.getRuolo());
	}
	
	public static UtenteBean creaOAggiornaUtenteDaBean(UtenteBean data) {
		if (data.isNewBean()) {
			// nuovo utente da creare
			Utente u = creaNuovoUtente(
					data.getCognome(), data.getNome(), data.getMail(), data.getPassword(), data.getRuolo());
			data.setId(u.getId());
		} else {
			// utente gia esistente
			Utente u = ServizioPersistenzaBase.<Utente>get(Utente.class, data.getId());
			u.setCognome(data.getCognome());
			u.setNome(data.getNome());
			u.setMail(data.getMail());
			u.setPassword(data.getPassword());
			ServizioPersistenzaBase.<Utente>saveOrUpdate(u);
		}
		return data;
	}
}
