package com.ndovado.webapp.beans.servizi;

public abstract class ATipologiaServizioBean {
	
	private Long id = null;
	protected Float prezzo = new Float(0);
	protected ServizioComuneBean servizio;
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
	/**
	 * @return the servizio
	 */
	public ServizioComuneBean getServizio() {
		return servizio;
	}
	/**
	 * @param servizio the servizio to set
	 */
	public void setServizio(ServizioComuneBean servizio) {
		this.servizio = servizio;
	}
	
	public abstract String getStringLabel();


}
