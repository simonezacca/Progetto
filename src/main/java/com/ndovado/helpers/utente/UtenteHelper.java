package com.ndovado.helpers.utente;

import com.ndovado.bridge.IBean;
import com.ndovado.bridge.IBeanModelBridge;
import com.ndovado.bridge.IModel;
import com.ndovado.dominio.core.ARuolo;
import com.ndovado.dominio.core.Utente;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistenza.base.ServizioPersistenzaBase;
import com.ndovado.tecservices.persistenza.base.UtenteDAO;
import com.ndovado.webapp.bean.UtenteBean;


public class UtenteHelper implements IBeanModelBridge {
	
	public UtenteHelper() {
		
	}

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
	
	public static Utente creaNuovoUtente(
			String aCognome, String aNome, String aMail, String aPassword, String ruoloCode) {
		Utente u = new Utente(aCognome, aNome);
		u.setMail(aMail);
		u.setPassword(aPassword);
		if (ruoloCode.equals("gestore")) {
			u.setRuolo(ARuolo.getRuoloGestore());
		} else {
			u.setRuolo(ARuolo.getRuoloLocatario());
		}
		// rendo persistente il nuovo utente creato
		ud.saveOrUpdate(u);
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
			// normalizzo la stringa ruolo
			data.setRuolo(u.getRuolo().toString());
		} else {
			// utente gia esistente
			Utente u = ServizioPersistenzaBase.<Utente>get(Utente.class, data.getId());
			u.setCognome(data.getCognome());
			u.setNome(data.getNome());
			u.setMail(data.getMail());
			u.setPassword(data.getPassword());
			ud.saveOrUpdate(u);
			// normalizzo la stringa ruolo
			data.setRuolo(u.getRuolo().toString());
		}
		return data;
	}

	@Override
	public IBean createBeanByModel(IModel model) {
		UtenteBean ub = new UtenteBean();
		Utente u = (Utente) model;
		ub.setId(u.getId());
		ub.setCognome(u.getCognome());
		ub.setNome(u.getNome());
		ub.setPassword(u.getPassword());
		ub.setRuolo(u.getRuolo().toString());
		return ub;
	}

	@Override
	public IModel createModelByBean(IBean bean) {
		Utente u = new Utente();
		UtenteBean ub = (UtenteBean) bean;
		return u;
	}

	@Override
	public IBean updateBeanByModel(IModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IModel updateModelByBean(IBean bean) {
		// TODO Auto-generated method stub
		return null;
	}
}
