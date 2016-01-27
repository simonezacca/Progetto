package com.ndovado.webapp.controllers;

import com.ndovado.dominio.core.CatalogoUtenti;
import com.ndovado.dominio.core.Utente;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.mappers.UserMapper;
import com.ndovado.webapp.beans.core.UtenteBean;

public class GestioneUtenteController {
	
	private UserMapper umapper = UserMapper.getInstance(); 
	private CatalogoUtenti cumodel = CatalogoUtenti.getInstance();
	
	public UtenteBean doLogin(UtenteBean ub) {
		// recupero indirizzo mail da bean
		String mail = ub.getMail();
		// istanzio un model di utente dall'indirizzo mail
		Utente umodel = cumodel.getUtentePerEmail(mail);
		if(umodel!=null) {
			// controllo le credenziali
			if (cumodel.verificaCredenzialiUtente(umodel.getMail(), umodel.getPassword())) {
				// se l'indirizzo mail è associato ad un utente
				// converto il model utente in bean utente
				// e popolo il bean con le informazioni del model
				ub = umapper.getBeanFromModel(umodel);
				// imposto il bollean loggato a true
				ub.setLogged(true);
				// ritorno il bean aggiornato
				return ub;
			}
		}
		return null;
	}
	
	public UtenteBean doRegistrazione(UtenteBean ub) {
		// se l'indizzo mail non esiste in memoria
		// TODO verificare tramite chiamata AJAX la verifica della mail cosi
		//	da evitare questo controllo
		AppLogger.debug("Verifico esistenza mail per: "+ub.getMail());
		if (!cumodel.isMailEsistente(ub.getMail())) {
			// converto il bean in model
			AppLogger.debug("Converto bean utente in model utente.");
			Utente umodel = umapper.getModelFromBean(ub);
			// salvo il molde su DB
			AppLogger.debug("Persisto il model utente su DB");
			cumodel.salvaOAggiornaUtente(umodel);
			// ripopolo il bean con le info del model persistito
			AppLogger.debug("Ripopolo bean utente da model utente");
			ub = umapper.getBeanFromModel(umodel);
			// imposto il bollean loggato a true
			ub.setLogged(true);
			// ritorno il bean aggiornato
			return ub; 
		}
		AppLogger.debug("Indirizzo mail esistente: "+ub.getMail());
		return null;
	}

	
	public Boolean verificaEsistenzaMail(String mailToCheck) {
		return cumodel.isMailEsistente(mailToCheck);
	}
}
