package com.ndovado.tecservices.persistenza.base;

import java.util.HashMap;
import java.util.Map;

import com.ndovado.dominio.core.Utente;

public class UtenteDAO extends GenericDAO<Utente> {

	public UtenteDAO() {
		super(Utente.class);
	}
	
	public Utente cercaUtentePerMail(String mail){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("mail", mail);  

		return super.findOneResult(Utente.FIND_BY_EMAIL, parameters);
	}
}
