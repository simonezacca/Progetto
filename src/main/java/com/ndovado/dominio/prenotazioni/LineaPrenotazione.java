package com.ndovado.dominio.prenotazioni;

/**
 * Implementare i metodi equals() and hasCode()
 */
public class LineaPrenotazione {

	/**
	 * Default constructor
	 */
	public LineaPrenotazione() {
	}
	
	public LineaPrenotazione(Prenotazione prenotazione) {
		if (prenotazione!=null) {
			this.prenotazioneCorrente = prenotazione;
		}
	}

	/**
	 * 
	 */
	private Prenotazione prenotazioneCorrente;

	/**
	 * 
	 */
	private IPrenotabile oggettoPrenotato;

	/**
	 * @param oggettoPrenotabile
	 */
	public void addOggettoPrenotato(IPrenotabile oggettoPrenotabile) {
		if (oggettoPrenotabile!=null) {
			this.oggettoPrenotato = oggettoPrenotabile;
		}
	}

	/**
	 * @return
	 */
	public Float getSubTotal() {
		return this.getOggettoPrenotato().getPrezzo();
	}

	/**
	 * @return the oggettoPrenotato
	 */
	public Prenotazione getPrenotazioneCorrente() {
		return this.prenotazioneCorrente;
	}

	/**
	 * @return the oggettoPrenotato
	 */
	public IPrenotabile getOggettoPrenotato() {
		return this.oggettoPrenotato;
	}

}