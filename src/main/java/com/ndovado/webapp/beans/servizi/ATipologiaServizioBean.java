package com.ndovado.webapp.beans.servizi;

public abstract class ATipologiaServizioBean {
	
	protected Long id = null;
	protected Float prezzo = new Float(0);
	
	protected DettaglioServizioBean dettaglioServizio;
	
	public ATipologiaServizioBean() {
		dettaglioServizio = new DettaglioServizioBean();
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the prezzo
	 */
	public Float getPrezzo() {
		return prezzo;
	}
	/**
	 * @param prezzo the prezzo to set
	 */
	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}
	
	public abstract String getStringLabel();
	/**
	 * @return the dettaglioServizio
	 */
	public DettaglioServizioBean getDettaglioServizio() {
		return dettaglioServizio;
	}
	/**
	 * @param dettaglioServizio the dettaglioServizio to set
	 */
	public void setDettaglioServizio(DettaglioServizioBean dettaglioServizio) {
		this.dettaglioServizio = dettaglioServizio;
	}


}
