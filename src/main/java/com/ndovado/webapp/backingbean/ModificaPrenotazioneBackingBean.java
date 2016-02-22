package com.ndovado.webapp.backingbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ndovado.exceptions.prenotazioni.CancellazionePrenotazioneException;
import com.ndovado.exceptions.prenotazioni.SalvataggioPrenotazioneException;
import com.ndovado.exceptions.prenotazioni.SelezionePrenotazioneException;
import com.ndovado.tecservices.jsf.JSFHelper;
import com.ndovado.webapp.beans.prenotazioni.PrenotazioneBean;
import com.ndovado.webapp.controllers.GestionePrenotazioneController;

@ManagedBean(name="modificaPrenotazioneBB")
@ViewScoped
public class ModificaPrenotazioneBackingBean implements Serializable { // NO_UCD (unused code)

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GestionePrenotazioneController controller = new GestionePrenotazioneController();
	private PrenotazioneBean prenotazioneCorrente;
	
	public ModificaPrenotazioneBackingBean() {
		try {
			initPrenotazioneCorrente();
		} catch (SelezionePrenotazioneException e) {
			JSFHelper.redirectTo("/locatario/index.xhtml");
		}
	}
	
	private void initPrenotazioneCorrente() throws SelezionePrenotazioneException {
		prenotazioneCorrente = (PrenotazioneBean) JSFHelper.get("prenotazioneDaVisualizzare");
		if (prenotazioneCorrente== null) {
			throw new SelezionePrenotazioneException();
		}
	}
	
	public String aggiornaPrenotazione() {
		try {
			controller.doAggiornaPrenotazione(prenotazioneCorrente);
			return "successo/successoSalvataggioPrenotazione?faces-redirect=true";
		} catch (SalvataggioPrenotazioneException e) {
			return "errore/erroreRegistrazionePrenotazione?faces-redirect=true";
		}
	}

	public String cancellaPrenotazione() {
		try {
			controller.doCancellaPrenotazione(prenotazioneCorrente);
			return "successo/successoCancellaPrenotazione?faces-redirect=true";
		} catch (CancellazionePrenotazioneException e) {
			return "errore/erroreCancellaPrenotazione?faces-redirect=true";
		}
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
