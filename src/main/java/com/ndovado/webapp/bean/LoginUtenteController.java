package com.ndovado.webapp.bean;


import javax.faces.context.FacesContext;

import com.ndovado.helpers.utente.UtenteHelper;

public class LoginUtenteController {
	
	public String doLogin(UtenteBean u) {
		if (u.getMail().length()>0)
			if (UtenteHelper.esisteIndirizzoMail(u.getMail()) && UtenteHelper.verificaCredenzialiUtente(u.getMail(), u.getPassword())) {
				UtenteBean unew = UtenteHelper.creaUtenteBeanDaMail(u.getMail());
				u.cloneFrom(unew);
				u.setLogged(true);
				return "areaPrivata";
			}
		return null;	
	}
	
	public String doLogout(UtenteBean u) {
		if(u!=null) {
			u.setLogged(false);
			terminaSessioneUtente();
		}
		return "logout"; // outcome
	}
	public String doRegistrazione(UtenteBean u) {
		if (!UtenteHelper.esisteIndirizzoMail(u.getMail())) {
			u = UtenteHelper.creaOAggiornaUtenteDaBean(u);
			u.setLogged(true);
			return "areaPrivata";
		}
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("mailMessage", "Indirizzo mail esistente");
		return null;
	}
	private void terminaSessioneUtente() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
}
