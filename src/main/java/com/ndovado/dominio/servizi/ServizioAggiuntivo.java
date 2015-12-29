package com.ndovado.dominio.servizi;

import com.ndovado.dominio.prenotazioni.IPrenotabile;

/**
 * 
 */
public class ServizioAggiuntivo extends ATipologiaServizio implements IPrenotabile {

	/**
	 * Default constructor
	 */
	public ServizioAggiuntivo(ServizioComune sc) {
		this.servizio = sc;
	}

	public String getNomeOggettoPrenotabile() {
		return this.servizio.getNomeServizio();
	}
	
	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}

}