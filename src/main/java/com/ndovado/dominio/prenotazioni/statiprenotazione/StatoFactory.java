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
	public static AStatoPrenotazione creaStatoDaConfermare() {
		return new StatoDaConfermare();
	}

	/**
	 * @return
	 */
	public static AStatoPrenotazione creaStatoDaCompletarePagamento() {
		return new StatoDaCompletarePagamento();
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
	public static AStatoPrenotazione creaStatoRifiutata() {
		return new StatoRifiutata();
	}

	/**
	 * @return
	 */
	public static AStatoPrenotazione creaStatoConfermata() {
		return new StatoConfermata();
	}

}