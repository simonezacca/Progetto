package com.ndovado.dominio.prenotazioni.statiprenotazione;

/**
 * 
 */
public abstract class AStatoPrenotazione {

	/**
	 * Default constructor
	 */
	public AStatoPrenotazione() {
	}

	/**
	 * 
	 */
	public abstract void confermaPrenotazione();

	/**
	 * 
	 */
	public abstract void completaPagamento();

	/**
	 * 
	 */
	public abstract void rifutaPrenotazione();

	/**
	 * 
	 */
	public abstract void eliminaPrenotazione();

}