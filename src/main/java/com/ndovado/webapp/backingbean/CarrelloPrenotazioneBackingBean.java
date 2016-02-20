package com.ndovado.webapp.backingbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.ndovado.exceptions.prenotazioni.SalvataggioPrenotazioneException;
import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.pagamenti.PagamentoBean;
import com.ndovado.webapp.beans.prenotazioni.CarrelloPrenotazioneBean;
import com.ndovado.webapp.beans.prenotazioni.PrenotazioneBean;
import com.ndovado.webapp.controllers.GestionePrenotazioneController;

@ManagedBean(name="carrelloPrenotazioneBB")
@ViewScoped
public class CarrelloPrenotazioneBackingBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GestionePrenotazioneController controller;
	
	@ManagedProperty(value="#{carrelloPrenotazioneBean}")
	private CarrelloPrenotazioneBean carrello;
	
	
	public CarrelloPrenotazioneBackingBean() {
		controller = new GestionePrenotazioneController();
	}
	
	public String confermaPrenotazione() {
		StrutturaBean sbean = carrello.getStrutturaCorrente();
		PrenotazioneBean pbean = carrello.getPrenotazioneCorrente();
		
		try {
			pbean = controller.doSalvaPrenotazione(sbean, pbean);
		} catch (SalvataggioPrenotazioneException e) {
			return "errore/erroreRegistrazionePrenotazione?faces-redirect=true";
		}
		// aggiorno con la prenotazione persistita su db
		
		// ottengo le coordinate per il pagamento al gestore
		String coordinateGestore = sbean.getGestore().getCoordinatePagamento();
		// creo un nuovo pagamento e imposto le coordinate del gestore
		PagamentoBean pagbean = new PagamentoBean();
		pagbean.setPagamentoVerso(coordinateGestore);
		
		// associo il pagamento appena creato alla prenotazione corrente
		pbean.setPagamentoAssociato(pagbean);
		carrello.setPrenotazioneCorrente(pbean);
		//carrello.svuotaCarrello();
		return "pagamentoPrenotazione?faces-redirect=true";
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
