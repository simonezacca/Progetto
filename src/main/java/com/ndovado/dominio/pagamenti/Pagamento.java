package com.ndovado.dominio.pagamenti;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.ndovado.dominio.prenotazioni.Prenotazione;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistence.base.IPersistente;

/**
 * Implementare i metodi equals() and hasCode()
 */
@Entity
@Table(name = "pagamento")
public class Pagamento implements IPersistente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_ora_pagamento")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime dataOraPagamento;

	/**
	 * 
	 */
	@Column(name = "id_transazione")
	private String idTransazione;

	/**
	 * 
	 */

	@OneToOne(optional=false)
	//@PrimaryKeyJoinColumn
	private Prenotazione prenotazioneSaldata;
	
	@Column(name="numeroCC")
	private String numeroCC;
	
	@Column(name="meseScadenza")
	private Integer meseScadenza;
	
	@Column(name="annoScadenza")
	private Integer annoScadenza;
	
	@Column(name="circuito")
	private String circuito;
	
	@Transient
	private Boolean autorizzato = false;
	
	@Column(name="pagamentoVerso")
	private String pagamentoVerso;
	
	@Transient
	private Integer ccv;
	

	/**
	 * Default constructor
	 */
	public Pagamento() {
		AppLogger.debug("Istanzio nuovo: "+this.getClass().getName());
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
	 * @return
	 */
	public LocalDateTime getDataOraPagamento() {
		return this.dataOraPagamento;
	}

	/**
	 * @param aDataOraPagamento
	 */
	public void setDataPagamento(LocalDateTime aDataPagamento) {
		this.dataOraPagamento = aDataPagamento;
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
		return id;
	}
	/**
	 * @param idPagamento the idPagamento to set
	 */
	public void setId(Long idPagamento) {
		this.id = idPagamento;
	}
	/**
	 * @param dataOraPagamento the dataOraPagamento to set
	 */
	public void setDataOraPagamento(LocalDateTime dataOraPagamento) {
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataOraPagamento == null) ? 0 : dataOraPagamento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idTransazione == null) ? 0 : idTransazione.hashCode());
		//result = prime * result + ((prenotazioneSaldata == null) ? 0 : prenotazioneSaldata.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
	 * @return the numeroCC
	 */
	public String getNumeroCC() {
		return numeroCC;
	}



	/**
	 * @param numeroCC the numeroCC to set
	 */
	public void setNumeroCC(String numeroCC) {
		this.numeroCC = numeroCC;
	}



	/**
	 * @return the meseScadenza
	 */
	public Integer getMeseScadenza() {
		return meseScadenza;
	}



	/**
	 * @param meseScadenza the meseScadenza to set
	 */
	public void setMeseScadenza(Integer meseScadenza) {
		this.meseScadenza = meseScadenza;
	}



	/**
	 * @return the annoScadenza
	 */
	public Integer getAnnoScadenza() {
		return annoScadenza;
	}



	/**
	 * @param annoScadenza the annoScadenza to set
	 */
	public void setAnnoScadenza(Integer annoScadenza) {
		this.annoScadenza = annoScadenza;
	}



	/**
	 * @return the circuito
	 */
	public String getCircuito() {
		return circuito;
	}



	/**
	 * @param circuito the circuito to set
	 */
	public void setCircuito(String circuito) {
		this.circuito = circuito;
	}



	/**
	 * @return the autorizzato
	 */
	public Boolean isAutorizzato() {
		return autorizzato;
	}



	/**
	 * @param autorizzato the autorizzato to set
	 */
	public void setAutorizzato(Boolean autorizzato) {
		this.autorizzato = autorizzato;
	}



	/**
	 * @return the pagamentoVerso
	 */
	public String getPagamentoVerso() {
		return pagamentoVerso;
	}



	/**
	 * @param pagamentoVerso the pagamentoVerso to set
	 */
	public void setPagamentoVerso(String pagamentoVerso) {
		this.pagamentoVerso = pagamentoVerso;
	}



	/**
	 * @return the ccv
	 */
	public Integer getCcv() {
		return ccv;
	}



	/**
	 * @param ccv the ccv to set
	 */
	public void setCcv(Integer ccv) {
		this.ccv = ccv;
	}

}