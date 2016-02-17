package com.ndovado.webapp.backingbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.ndovado.tecservices.jsf.JSFHelper;
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
		prenotazioneCorrente = (PrenotazioneBean) JSFHelper.get("prenotazioneDaVisualizzare");
		if (prenotazioneCorrente==null) {
			JSFHelper.redirectTo("/locatario/index.xhtml");
		}
	}
	
	public String aggiornaPrenotazione() {
		controller.doAggiornaPrenotazione(prenotazioneCorrente);
		return null;
	}

	public String cancellaPrenotazione() {
		controller.doCancellaPrenotazione(prenotazioneCorrente);
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
