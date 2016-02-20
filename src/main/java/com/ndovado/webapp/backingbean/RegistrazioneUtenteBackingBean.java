package com.ndovado.webapp.backingbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.ndovado.exceptions.utente.MailEsistenteException;
import com.ndovado.webapp.beans.core.ARuoloBean;
import com.ndovado.webapp.beans.core.GestoreBean;
import com.ndovado.webapp.beans.core.LocatarioBean;
import com.ndovado.webapp.beans.core.UtenteBean;
import com.ndovado.webapp.controllers.GestioneUtenteController;

@ManagedBean(name="registrazioneUtenteBB")
@RequestScoped
public class RegistrazioneUtenteBackingBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		String outcome;
		try {
			UtenteBean newUB = controller.doRegistrazione(utenteCorrente);
			utenteCorrente.cloneFrom(newUB);
			//AppLogger.debug("Nuovo bean utente da doRegistrazione "+utenteCorrente);
			outcome = utenteCorrente.getHomePageName();
			return outcome;
		} catch (MailEsistenteException e) {
			outcome = "erroreMailEsistente?faces-redirect=true";
			return outcome;
		}
	}

}
