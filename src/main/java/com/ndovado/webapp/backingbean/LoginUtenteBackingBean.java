package com.ndovado.webapp.backingbean;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.webapp.beans.core.UtenteBean;
import com.ndovado.webapp.controllers.GestioneUtenteController;

@ManagedBean(name="loginControllerBB")
public class LoginUtenteBackingBean {

	private GestioneUtenteController controller = new GestioneUtenteController();
	
	@ManagedProperty(value="#{utenteBean}")
	private UtenteBean utenteCorrente;
	
	public String doLogin(){
		UtenteBean newUB = controller.doLogin(utenteCorrente);
		String outcome;
		if (newUB!=null) {
			utenteCorrente.cloneFrom(newUB);
			AppLogger.debug("Nuovo bean utente da doLogin "+utenteCorrente);
			outcome = utenteCorrente.getHomePageName();
		} else
			outcome = null;
		return outcome; 
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
