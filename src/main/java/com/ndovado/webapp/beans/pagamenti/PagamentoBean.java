package com.ndovado.webapp.beans.pagamenti;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import com.ndovado.webapp.beans.prenotazioni.PrenotazioneBean;

@ManagedBean(name = "pagamentoBean")
public class PagamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDateTime dataOraPagamento;
	private String idTransazione;
	private PrenotazioneBean prenotazioneSaldata;
	private String numeroCC;
	private Integer meseScadenza;
	private Integer annoScadenza;
	private String circuito;
	private List<String> circuitiDisponibili;
	
	private Boolean autorizzato = false;
	
	private String pagamentoVerso;
	
	private Integer ccv;
	
	public PagamentoBean() {
		initCircuiti();
	}
	
	private void initCircuiti() {
		circuitiDisponibili = new ArrayList<String>();
		circuitiDisponibili.add("Visa");
		circuitiDisponibili.add("Mastercard");
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataOraPagamento() {
		return dataOraPagamento;
	}

	public void setDataOraPagamento(LocalDateTime dataOraPagamento) {
		this.dataOraPagamento = dataOraPagamento;
	}

	public String getIdTransazione() {
		return idTransazione;
	}

	public void setIdTransazione(String idTransazione) {
		this.idTransazione = idTransazione;
	}

	public PrenotazioneBean getPrenotazioneSaldata() {
		return prenotazioneSaldata;
	}

	public void setPrenotazioneSaldata(PrenotazioneBean prenotazioneSaldata) {
		this.prenotazioneSaldata = prenotazioneSaldata;
	}

	public String getNumeroCC() {
		return numeroCC;
	}

	public void setNumeroCC(String numeroCC) {
		this.numeroCC = numeroCC;
	}

	public Integer getMeseScadenza() {
		return meseScadenza;
	}

	public void setMeseScadenza(Integer meseScadenza) {
		this.meseScadenza = meseScadenza;
	}

	public Integer getAnnoScadenza() {
		return annoScadenza;
	}

	public void setAnnoScadenza(Integer annoScadenza) {
		this.annoScadenza = annoScadenza;
	}

	public String getCircuito() {
		return circuito;
	}

	public void setCircuito(String circuito) {
		this.circuito = circuito;
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

	/**
	 * @return the circuitiDisponibili
	 */
	public List<String> getCircuitiDisponibili() {
		return circuitiDisponibili;
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
}
