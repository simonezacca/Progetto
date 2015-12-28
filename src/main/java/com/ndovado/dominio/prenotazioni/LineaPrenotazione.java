package com.ndovado.dominio.prenotazioni;

import com.ndovado.tecservices.persistenza.base.IIdentificabile;

/**
 * Implementare i metodi equals() and hasCode()
 */
public class LineaPrenotazione implements IIdentificabile {

	/**
	 * Default constructor
	 */
	@SuppressWarnings("unused")
	private LineaPrenotazione() {
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
	private Long id;
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

	@Override
	public Long getId() {
		return this.id;
	}
	
	protected void setId(Long id) {
		this.id = id;
	}
	
	protected void setPrenotazione(Prenotazione p) {
		if (p!=null) {
			this.prenotazioneCorrente = p;
		}
	}

}