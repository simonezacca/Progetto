package com.ndovado.dominio.prenotazioni;

import com.ndovado.tecservices.persistenza.base.IPersistente;

/**
 * Implementare i metodi equals() and hasCode()
 */
public class LineaPrenotazione implements IPersistente {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((prenotazioneCorrente == null) ? 0 : prenotazioneCorrente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LineaPrenotazione))
			return false;
		LineaPrenotazione other = (LineaPrenotazione) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (prenotazioneCorrente == null) {
			if (other.prenotazioneCorrente != null)
				return false;
		} else if (!prenotazioneCorrente.equals(other.prenotazioneCorrente))
			return false;
		return true;
	}

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