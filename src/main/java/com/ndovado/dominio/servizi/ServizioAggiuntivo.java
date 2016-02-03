package com.ndovado.dominio.servizi;


import javax.persistence.Entity;

import com.ndovado.dominio.prenotazioni.IPrenotabile;

/**
 * 
 */
@Entity
public class ServizioAggiuntivo extends ATipologiaServizio implements IPrenotabile {

	/**
	 * Default constructor
	 */
	public ServizioAggiuntivo(ServizioComune sc) {
		this.dettaglioServizio.setServizio(sc);
		//this.setServizio(sc);
	}

	public String getNomeOggettoPrenotabile() {
		return this.dettaglioServizio.getServizio().getNomeServizio();
		//return this.getServizio().getNomeServizio();
	}
	
	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}
	public ServizioAggiuntivo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Servizio aggiuntivo: "+getNomeOggettoPrenotabile();
	}

}