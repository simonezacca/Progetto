package com.ndovado.dominio.prenotazioni.statiprenotazione;

import javax.persistence.Entity;

import com.ndovado.tecservices.loggers.AppLogger;

/**
 * 
 */
@Entity
public class StatoPendente extends AStatoPrenotazione {

	/**
	 * Default constructor
	 */
	public StatoPendente() {
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