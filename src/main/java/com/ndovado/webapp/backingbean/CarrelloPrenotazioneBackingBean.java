package com.ndovado.webapp.backingbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.prenotazioni.CarrelloPrenotazioneBean;
import com.ndovado.webapp.beans.prenotazioni.PrenotazioneBean;
import com.ndovado.webapp.controllers.GestionePrenotazioneController;

@ManagedBean(name="carrelloPrenotazioneBB")
@ViewScoped
public class CarrelloPrenotazioneBackingBean {
	
	private GestionePrenotazioneController controller;
	
	@ManagedProperty(value="#{carrelloPrenotazioneBean}")
	private CarrelloPrenotazioneBean carrello;
	
	
	public CarrelloPrenotazioneBackingBean() {
		controller = new GestionePrenotazioneController();
	}
	
	public String confermaPrenotazione() {
		StrutturaBean sbean = carrello.getStrutturaCorrente();
		PrenotazioneBean pbean = carrello.getPrenotazioneCorrente();
		
		controller.doSalvaPrenotazione(sbean, pbean);
		return "pagamentoPrenotazione";
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
