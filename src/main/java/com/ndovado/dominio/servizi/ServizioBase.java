package com.ndovado.dominio.servizi;

/**
 * 
 */
public class ServizioBase extends ATipologiaServizio {

	@SuppressWarnings("unused")
	private final Float prezzo = new Float(0);
	/**
	 * Default constructor
	 */
	public ServizioBase(ServizioComune sc) {
		this.servizio = sc;
	}

}