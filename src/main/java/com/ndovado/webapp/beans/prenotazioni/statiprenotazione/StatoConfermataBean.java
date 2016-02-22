package com.ndovado.webapp.beans.prenotazioni.statiprenotazione;

public class StatoConfermataBean extends AStatoPrenotazioneBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Stato confermata";
	}

	public StatoConfermataBean() {
	}
	
	public static StatoConfermataBean creaBean() {
		return new StatoConfermataBean();
	}
}
