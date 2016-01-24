package com.ndovado.dominio.prenotazioni.statiprenotazione;

/**
 * 
 */
public class StatoFactory {

	/**
	 * Default constructor
	 */
	public StatoFactory() {
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

}