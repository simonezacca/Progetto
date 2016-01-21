package com.ndovado.webapp.controllers;


import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.ndovado.helpers.core.UtenteHelper;
import com.ndovado.webapp.beans.core.UtenteBean;

@ManagedBean(name="loginController")
public class LoginUtenteController {
	
	public String doLogin(UtenteBean u) {
		if (u.getMail().length()>0)
			if (UtenteHelper.esisteIndirizzoMail(u.getMail()) && UtenteHelper.verificaCredenzialiUtente(u.getMail(), u.getPassword())) {
				UtenteBean unew = UtenteHelper.creaUtenteBeanDaMail(u.getMail());
				u.cloneFrom(unew);
				u.setLogged(true);
				return u.getHomePageName();
			}
		return null;	
	}
	
	public String doLogout(UtenteBean u) {
		if(u!=null) {
			u.setLogged(false);
			terminaSessioneUtente();
		}
		return "/index?faces-redirect=true";
	}
	public String doRegistrazione(UtenteBean u) {
		if (!UtenteHelper.esisteIndirizzoMail(u.getMail()) || !u.isNewBean()) {
			u = UtenteHelper.creaOAggiornaUtenteDaBean(u);
			u.setLogged(true);
			return u.getHomePageName();
		}
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("mailMessage", "Indirizzo mail esistente");
		return null;
	}
	private void terminaSessioneUtente() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
}