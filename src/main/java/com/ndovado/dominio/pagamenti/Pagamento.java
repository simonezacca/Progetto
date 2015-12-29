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
import com.ndovado.tecservices.persistenza.base.IPersistente;

/**
 * Implementare i metodi equals() and hasCode()
 */
@Entity
@Table(name = "pagamento")
public class Pagamento implements IPersistente {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataOraPagamento == null) ? 0 : dataOraPagamento.hashCode());
		result = prime * result + ((idPagamento == null) ? 0 : idPagamento.hashCode());
		result = prime * result + ((idTransazione == null) ? 0 : idTransazione.hashCode());
		result = prime * result + ((prenotazioneSaldata == null) ? 0 : prenotazioneSaldata.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pagamento))
			return false;
		Pagamento other = (Pagamento) obj;
		if (dataOraPagamento == null) {
			if (other.dataOraPagamento != null)
				return false;
		} else if (!dataOraPagamento.equals(other.dataOraPagamento))
			return false;
		if (idPagamento == null) {
			if (other.idPagamento != null)
				return false;
		} else if (!idPagamento.equals(other.idPagamento))
			return false;
		if (idTransazione == null) {
			if (other.idTransazione != null)
				return false;
		} else if (!idTransazione.equals(other.idTransazione))
			return false;
		if (prenotazioneSaldata == null) {
			if (other.prenotazioneSaldata != null)
				return false;
		} else if (!prenotazioneSaldata.equals(other.prenotazioneSaldata))
			return false;
		return true;
	}
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