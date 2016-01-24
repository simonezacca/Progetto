package com.ndovado.webapp.backingbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.ndovado.webapp.beans.core.ARuoloBean;
import com.ndovado.webapp.beans.core.GestoreBean;
import com.ndovado.webapp.beans.core.LocatarioBean;
import com.ndovado.webapp.beans.core.UtenteBean;

@ManagedBean(name="registrazioneUtenteBB")
@RequestScoped
public class RegistrazioneUtenteBackingBean {

	private static ARuoloBean[] ruoli;
	
	public RegistrazioneUtenteBackingBean() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		ruoli = new ARuoloBean[2];
		ruoli[0] = new LocatarioBean();
		ruoli[1] = new GestoreBean();
	}
	
	public ARuoloBean[] getRuoliValues() {
		return ruoli;
	}	
	
	@ManagedProperty(value="#{utenteB}")
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
		System.out.println(utenteCorrente);
		return null;
	}
	
	public GestoreBean getGestoreRuoloBean() {
		return new GestoreBean();
	}
	
	public LocatarioBean getLocatarioRuoloBean() {
		return new LocatarioBean();
	}
}
