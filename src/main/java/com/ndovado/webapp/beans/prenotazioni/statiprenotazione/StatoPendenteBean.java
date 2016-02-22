package com.ndovado.webapp.beans.prenotazioni.statiprenotazione;

public class StatoPendenteBean extends AStatoPrenotazioneBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Stato pendente";
	}
	
	public StatoPendenteBean() {
	}

	public static StatoPendenteBean creaBean() {
		return new StatoPendenteBean();
	}
}
