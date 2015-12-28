package com.ndovado.dominio.pagamenti;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ndovado.dominio.prenotazioni.Prenotazione;
import com.ndovado.tecservices.persistenza.base.IIdentificabile;

/**
 * Implementare i metodi equals() and hasCode()
 */
@Entity
@Table(name = "pagamento")
public class Pagamento implements IIdentificabile {

	/**
	 * Default constructor
	 */
	@SuppressWarnings("unused")
	private Pagamento() {
	}
	/**
	 * 
	 * @param aPrenotazione
	 */
	public Pagamento(Prenotazione aPrenotazione) {
		if (aPrenotazione!=null) {
			this.setPrenotazioneSaldata(aPrenotazione);
		}
	}

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long idPagamento;

	/**
	 * 
	 */
	@Column(name = "data_ora_pagamento")
	private Date dataOraPagamento;

	/**
	 * 
	 */
	@Column(name = "id_transazione")
	private String idTransazione;

	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name="prenotazione_id")
	private Prenotazione prenotazioneSaldata;

	/**
	 * @return
	 */
	public Date getDataOraPagamento() {
		// TODO implement here
		return null;
	}

	/**
	 * @param aDataOraPagamento
	 */
	public void setDataPagamento(Date aDataPagamento) {
		// TODO implement here
	}

	/**
	 * @return
	 */
	public String getIdTransazione() {
		return idTransazione;
	}

	/**
	 * @param aIdTransazione
	 */
	public void setIdTransazione(String aIdTransazione) {
		if (aIdTransazione!=null) {
			this.idTransazione = aIdTransazione;
		}
	}
	/**
	 * @return the idPagamento
	 */
	public Long getId() {
		return idPagamento;
	}
	/**
	 * @param idPagamento the idPagamento to set
	 */
	protected void setId(Long idPagamento) {
		this.idPagamento = idPagamento;
	}
	/**
	 * @param dataOraPagamento the dataOraPagamento to set
	 */
	public void setDataOraPagamento(Date dataOraPagamento) {
		if (dataOraPagamento!=null) {
			this.dataOraPagamento = dataOraPagamento;	
		}
	}
	/**
	 * @return the prenotazioneSaldata
	 */
	public Prenotazione getPrenotazioneSaldata() {
		return prenotazioneSaldata;
	}
	/**
	 * @param prenotazioneSaldata the prenotazioneSaldata to set
	 */
	public void setPrenotazioneSaldata(Prenotazione prenotazioneSaldata) {
		if (prenotazioneSaldata!=null) {
			this.prenotazioneSaldata = prenotazioneSaldata;
		}
	}

}