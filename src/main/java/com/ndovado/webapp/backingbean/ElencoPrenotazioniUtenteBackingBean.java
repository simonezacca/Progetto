package com.ndovado.webapp.backingbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.ndovado.webapp.beans.core.LocatarioBean;
import com.ndovado.webapp.beans.core.UtenteBean;
import com.ndovado.webapp.beans.prenotazioni.PrenotazioneBean;
import com.ndovado.webapp.controllers.GestionePrenotazioneController;

@ManagedBean(name="elencoPrenotazioniUtenteBB")
@ViewScoped
public class ElencoPrenotazioniUtenteBackingBean implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GestionePrenotazioneController controller;
	
	@ManagedProperty(value="#{utenteBean}")
	private UtenteBean utenteCorrente;
	private LocatarioBean locatarioCorrente;
	
	private List<PrenotazioneBean> elencoPrenotazioni;
	
	public ElencoPrenotazioniUtenteBackingBean() {
		controller = new GestionePrenotazioneController();
	}
	
	@PostConstruct
	protected void postCostruct() {
		locatarioCorrente = (LocatarioBean) utenteCorrente.getRuolo();
		elencoPrenotazioni = controller.getElencoPrenotazioniBeansByLocatario(locatarioCorrente);
	}
	/**
	 * @return the elencoPrenotazioni
	 */
	public List<PrenotazioneBean> getElencoPrenotazioni() {
		return this.elencoPrenotazioni;
	}

	/**
	 * @param elencoPrenotazioni the elencoPrenotazioni to set
	 */
	public void setElencoPrenotazioni(List<PrenotazioneBean> elencoPrenotazioni) {
		this.elencoPrenotazioni = elencoPrenotazioni;
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

}