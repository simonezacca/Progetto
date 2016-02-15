package com.ndovado.webapp.backingbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import com.ndovado.webapp.beans.prenotazioni.PrenotazioneBean;
import com.ndovado.webapp.controllers.GestionePrenotazioneController;

@ManagedBean(name="modificaPrenotazioneBB")
@ViewScoped
public class ModificaPrenotazioneBackingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GestionePrenotazioneController controller = new GestionePrenotazioneController();
	private PrenotazioneBean prenotazioneCorrente;
	
	public ModificaPrenotazioneBackingBean() {
		// caso modifica
		prenotazioneCorrente = (PrenotazioneBean) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get("prenotazioneDaVisualizzare");
	}
	
	public String doAggiornaPrenotazione() {
		controller.doAggiornaPrenotazione(prenotazioneCorrente);
		return "prenotazioni";
	}

	/**
	 * @return the prenotazioneCorrente
	 */
	public PrenotazioneBean getPrenotazioneCorrente() {
		return prenotazioneCorrente;
	}

	/**
	 * @param prenotazioneCorrente the prenotazioneCorrente to set
	 */
	public void setPrenotazioneCorrente(PrenotazioneBean prenotazioneCorrente) {
		this.prenotazioneCorrente = prenotazioneCorrente;
	}
}
