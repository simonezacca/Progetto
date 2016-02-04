package com.ndovado.webapp.beans.prenotazioni;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;


@ManagedBean(name="lineaPreanotazioneBean")
public class LineaPrenotazioneBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private IPrenotabileBean oggettoPrenotato;
	private PrenotazioneBean prenotazioneCorrente;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the oggettoPrenotato
	 */
	public IPrenotabileBean getOggettoPrenotato() {
		return oggettoPrenotato;
	}
	/**
	 * @param oggettoPrenotato the oggettoPrenotato to set
	 */
	public void setOggettoPrenotato(IPrenotabileBean oggettoPrenotato) {
		this.oggettoPrenotato = oggettoPrenotato;
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
