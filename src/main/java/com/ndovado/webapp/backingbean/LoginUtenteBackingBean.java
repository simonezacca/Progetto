package com.ndovado.webapp.backingbean;


import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import com.ndovado.exceptions.utente.CredenzialiErrateException;
import com.ndovado.exceptions.utente.UtenteNonTrovatoException;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.webapp.beans.core.UtenteBean;
import com.ndovado.webapp.controllers.GestioneUtenteController;

@ManagedBean(name="loginControllerBB")
public class LoginUtenteBackingBean implements Serializable{ // NO_UCD (unused code)

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GestioneUtenteController controller = new GestioneUtenteController();
	
	@ManagedProperty(value="#{utenteBean}")
	private UtenteBean utenteCorrente;
	
	public String doLogin(){
		String outcome;
		try {
			UtenteBean newUB = controller.doLogin(utenteCorrente);
			utenteCorrente.cloneFrom(newUB);
			AppLogger.debug("Nuovo bean utente da doLogin "+utenteCorrente);
			outcome = utenteCorrente.getHomePageName();
			return outcome;
		} catch (UtenteNonTrovatoException e1) {
			AppLogger.error("UtenteNonTrovatoException "+utenteCorrente);
			outcome = "erroreLogin?faces-redirect=true";
			return outcome;
		} catch (CredenzialiErrateException e2) {
			AppLogger.error("CredenzialiErrateException "+utenteCorrente);
			outcome = "erroreLogin?faces-redirect=true";
			return outcome;
		} 
	}

	/**
	 * @return the utenteCorrente
	 */
	public UtenteBean getUtenteCorrente() {
		return utenteCorrente;
	}

	/**
	 * @param utenteCorrente the utenteCorrente to set
	 */
	public void setUtenteCorrente(UtenteBean utenteCorrente) {
		this.utenteCorrente = utenteCorrente;
	}
	
	public String doLogout() {
		utenteCorrente.setLogged(false);
		resetSession();
		return "/index?faces-redirect=true";
	
	}
	
	private void resetSession() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
}
