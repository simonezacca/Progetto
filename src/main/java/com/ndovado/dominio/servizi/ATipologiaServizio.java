package com.ndovado.dominio.servizi;

/**
 * 
 */
public abstract class ATipologiaServizio {

	protected Float prezzo = new Float(0);
	
	protected ServizioComune servizio;
	/**
	 * Default constructor
	 */
	public ATipologiaServizio() {
	}
	
	public ServizioComune getServizioAssociato() {
		return this.servizio;
	}
	
	public Float getPrezzo() {
		return this.prezzo;
	}

}