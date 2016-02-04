package com.ndovado.webapp.beans.prenotazioni.statiprenotazione;

import java.io.Serializable;

public abstract class AStatoPrenotazioneBean implements Serializable{

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected Long id;

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
}
