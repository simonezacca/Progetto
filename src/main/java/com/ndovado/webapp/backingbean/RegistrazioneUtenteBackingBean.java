package com.ndovado.webapp.backingbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.ndovado.webapp.beans.core.ARuoloBean;
import com.ndovado.webapp.beans.core.GestoreBean;
import com.ndovado.webapp.beans.core.LocatarioBean;
import com.ndovado.webapp.beans.core.UtenteBean;
import com.ndovado.webapp.controllers.GestioneUtenteController;

@ManagedBean(name="registrazioneUtenteBB")
@RequestScoped
public class RegistrazioneUtenteBackingBean {

	private GestioneUtenteController controller = new GestioneUtenteController();
	private static ARuoloBean[] ruoli;
	
	public RegistrazioneUtenteBackingBean() {
		
	}
	
	static {
		ruoli = new ARuoloBean[2];
		ruoli[0] = new LocatarioBean();
		ruoli[1] = new GestoreBean();
	}
	
	public ARuoloBean[] getRuoliValues() {
		return ruoli;
	}	
	
	@ManagedProperty(value="#{utenteBean}")
	private UtenteBean utenteCorrente;
	
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
	
	public String doRegistrazione() {
		// TODO spostare controllo mail qui inveve che in GestioneControllerUtente
		UtenteBean newUB = controller.doRegistrazione(utenteCorrente);
		String outcome;
		if (newUB!=null) {
			utenteCorrente.cloneFrom(newUB);
			//AppLogger.debug("Nuovo bean utente da doRegistrazione "+utenteCorrente);
			outcome = utenteCorrente.getHomePageName();
		} else
			outcome = null;
		return outcome; 
	}

}
