package com.ndovado.webapp.beans.prenotazioni.statiprenotazione;

public class StatoEliminataBean extends AStatoPrenotazioneBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Stato eliminata";
	}

	public StatoEliminataBean() {
	}
	
	public static StatoEliminataBean creaBean() {
		return new StatoEliminataBean();
	}
}
