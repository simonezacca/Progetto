package com.ndovado.dominio.prenotazioni.statiprenotazione;

import javax.persistence.Entity;

import com.ndovado.tecservices.loggers.AppLogger;

/**
 * 
 */
@Entity
public class StatoConfermata extends AStatoPrenotazione {

	/**
	 * Default constructor
	 */
	public StatoConfermata() {
		AppLogger.debug("Istanzio nuovo: "+this.getClass().getName());
	}

	/**
	 * 
	 */
	public void confermaPrenotazione() {
	}

	/**
	 * 
	 */
	public void completaPagamento() {
	}

	/**
	 * 
	 */
	public void rifutaPrenotazione() {
	}

	/**
	 * 
	 */
	public void eliminaPrenotazione() {
	}

}