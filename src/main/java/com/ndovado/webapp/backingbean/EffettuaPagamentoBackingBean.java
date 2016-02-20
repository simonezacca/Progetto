package com.ndovado.webapp.backingbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.ndovado.webapp.beans.pagamenti.PagamentoBean;
import com.ndovado.webapp.beans.prenotazioni.CarrelloPrenotazioneBean;
import com.ndovado.webapp.beans.prenotazioni.PrenotazioneBean;
import com.ndovado.webapp.controllers.GestionePagamentoController;

@ManagedBean(name="effettuaPagamentoBB")
@ViewScoped
public class EffettuaPagamentoBackingBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GestionePagamentoController controller = new GestionePagamentoController();
	
	private PagamentoBean pagamentoCorrente;
	
	@ManagedProperty(value="#{carrelloPrenotazioneBean}")
	private	CarrelloPrenotazioneBean carrello;

	private	PrenotazioneBean prenotazioneCorrente;
	
	private String coordinatePagamento;
	
	
	
	public EffettuaPagamentoBackingBean() {
		
	}
	
	@PostConstruct
	public void initPagamento() {
		prenotazioneCorrente = carrello.getPrenotazioneCorrente();
		pagamentoCorrente = prenotazioneCorrente.getPagamentoAssociato();
	}

	
	public String effettuaPagamento() {
		pagamentoCorrente = controller.doSalvaPagamento(pagamentoCorrente);
		if (pagamentoCorrente.isAutorizzato()) {
			// pagamento ACCETTATO
			// redirect alla pagina di successo
			carrello.svuotaCarrello();
			return "successo/successoPagamento.xhtml?faces-redirect=true";
		}
		carrello.svuotaCarrello();
		// pagamento RESPINTO
		// redirect alla pagina di errore
		return "errore/errorePagamento?faces-redirect=true";
		
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

	/**
	 * @return the coordinatePagamento
	 */
	public String getCoordinatePagamento() {
		return coordinatePagamento;
	}

	/**
	 * @param coordinatePagamento the coordinatePagamento to set
	 */
	public void setCoordinatePagamento(String coordinatePagamento) {
		this.coordinatePagamento = coordinatePagamento;
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

	/**
	 * @return the pagamentoCorrente
	 */
	public PagamentoBean getPagamentoCorrente() {
		return pagamentoCorrente;
	}

	/**
	 * @param pagamentoCorrente the pagamentoCorrente to set
	 */
	public void setPagamentoCorrente(PagamentoBean pagamentoCorrente) {
		this.pagamentoCorrente = pagamentoCorrente;
	}

}
