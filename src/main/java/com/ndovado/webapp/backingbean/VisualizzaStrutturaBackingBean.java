package com.ndovado.webapp.backingbean;

import java.io.Serializable;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.ndovado.exceptions.struttura.SelezioneStrutturaException;
import com.ndovado.tecservices.jsf.JSFHelper;
import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.prenotazioni.CarrelloPrenotazioneBean;
import com.ndovado.webapp.beans.prenotazioni.RisultatoRicercaBean;

@ManagedBean(name="visualizzaStrutturaBB")
@ViewScoped
public class VisualizzaStrutturaBackingBean implements Serializable { // NO_UCD (unused code)

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RisultatoRicercaBean RRcorrente;
	
	private StrutturaBean strutturaCorrente;
	
	@ManagedProperty(value="#{carrelloPrenotazioneBean}")
	private CarrelloPrenotazioneBean carrello;
	
	
	public VisualizzaStrutturaBackingBean() {
		try {
			initStrutturaCorrente();
		} catch (SelezioneStrutturaException e) {
			JSFHelper.redirectTo("/locatario/index.xhtml");
		}
		
	}
	
	private void initStrutturaCorrente() throws SelezioneStrutturaException {
		RRcorrente = (RisultatoRicercaBean) JSFHelper.get("RRcorrente");
		if (RRcorrente!=null) {
			strutturaCorrente = RRcorrente.getStruttura();
		} else if (strutturaCorrente==null){
			strutturaCorrente = (StrutturaBean) JSFHelper.get("strutturaCorrente");
		}
		if (strutturaCorrente==null) {
			throw new SelezioneStrutturaException();
		}
	}
	
	
	/**
	 * @return the rRcorrente
	 */
	public RisultatoRicercaBean getRRcorrente() {
		return RRcorrente;
	}
	/**
	 * @param rRcorrente the rRcorrente to set
	 */
	public void setRRcorrente(RisultatoRicercaBean rRcorrente) {
		RRcorrente = rRcorrente;
	}


	/**
	 * @return the strutturaCorrente
	 */
	public StrutturaBean getStrutturaCorrente() {
		return strutturaCorrente;
	}


	/**
	 * @param strutturaCorrente the strutturaCorrente to set
	 */
	public void setStrutturaCorrente(StrutturaBean strutturaCorrente) {
		this.strutturaCorrente = strutturaCorrente;
	}

	@PreDestroy
	public void azzeraCarrello() {
		// azzero il carrello
		this.carrello.svuotaCarrello();
	}


	/**
	 * @return the carrello
	 */
	public CarrelloPrenotazioneBean getCarrello() {
		return carrello;
	}


	/**
	 * @param carrello the carrello to set
	 */
	public void setCarrello(CarrelloPrenotazioneBean carrello) {
		this.carrello = carrello;
	}
}
