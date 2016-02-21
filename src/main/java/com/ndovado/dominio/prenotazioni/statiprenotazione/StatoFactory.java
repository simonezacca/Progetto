package com.ndovado.dominio.prenotazioni.statiprenotazione;

import com.ndovado.tecservices.loggers.AppLogger;

/**
 * 
 */
public class StatoFactory {

	/**
	 * Default constructor
	 */
	public StatoFactory() {
		AppLogger.debug("Istanzio nuovo: "+this.getClass().getName());
	}

	/**
	 * @return
	 */
	public static AStatoPrenotazione creaStatoPendente() {
		return new StatoPendente();
	}

	/**
	 * @return
	 */
	public static AStatoPrenotazione creaStatoEliminata() {
		return new StatoEliminata();
	}

	/**
	 * @return
	 */
	public static AStatoPrenotazione creaStatoConfermata() {
		return new StatoConfermata();
	}
	
	public static void main(String[] args) {}

}