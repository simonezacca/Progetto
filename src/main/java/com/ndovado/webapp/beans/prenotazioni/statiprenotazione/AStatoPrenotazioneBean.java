package com.ndovado.webapp.beans.prenotazioni.statiprenotazione;

import java.io.Serializable;

import com.ndovado.dominio.prenotazioni.Prenotazione;

public abstract class AStatoPrenotazioneBean implements Serializable{

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected Long id;
	
	protected Prenotazione prenotazione;

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
	
	@Override
	public abstract String toString();

	/**
	 * @return the prenotazione
	 */
	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	/**
	 * @param prenotazione the prenotazione to set
	 */
	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}
}
