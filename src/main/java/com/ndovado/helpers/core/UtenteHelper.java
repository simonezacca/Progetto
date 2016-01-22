package com.ndovado.helpers.core;

import com.ndovado.dominio.core.Utente;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistenza.base.UtenteDAO;
import com.ndovado.webapp.beans.core.UtenteBean;

public class UtenteHelper  {
	
	public UtenteHelper() {
		
	}
	
	private static UtenteHelper istance;

	private static UtenteDAO ud = new UtenteDAO();
	
	public static boolean esisteIndirizzoMail(String mailAddress) {
		Utente u = ud.cercaUtentePerMail(mailAddress);
		return u!=null;
	}
	
	public static boolean verificaCredenzialiUtente(String username, String password) {
		AppLogger.debug("verificaCredenzialiUtente [username: "+username+ ", password: "+password+"]");
		Utente u = ud.cercaUtentePerMail(username);
		if (u!=null) {
			AppLogger.debug("verifica uguaglianza password:"+password+"=="+u.getPassword());
			return u.getPassword().equals(password);
		}
		AppLogger.debug("verificaCredenziali: utente = null");
		return false;
	}

	protected static UtenteHelper getIstance() {
		if (istance==null) {
			istance = new UtenteHelper();
		}
		return istance;
	}
	
	public static UtenteBean creaUtenteBeanDaMail(String mail) {
		// recupero il model con utentedao
		// poi converto il model in bean con utenteconverter
		return new UtenteBean(ud.cercaUtentePerMail(mail));
	}
}
