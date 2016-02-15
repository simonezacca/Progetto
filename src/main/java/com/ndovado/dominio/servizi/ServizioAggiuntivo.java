package com.ndovado.dominio.servizi;

import javax.persistence.Entity;

import org.hibernate.annotations.Proxy;

import com.ndovado.dominio.prenotazioni.IPrenotabile;
import com.ndovado.tecservices.loggers.AppLogger;

/**
 * 
 */
@Entity
@Proxy(lazy=false,proxyClass=ServizioAggiuntivo.class)
public class ServizioAggiuntivo extends ATipologiaServizio implements IPrenotabile {

	public ServizioAggiuntivo() {
		AppLogger.debug("Istanzio nuovo: "+this.getClass().getName());
		this.dettaglioServizio = null;

	}

	/**
	 * Default constructor
	 */
	public ServizioAggiuntivo(ServizioComune sc) {
		this.dettaglioServizio.setServizio(sc);
		//this.setServizio(sc);
	}

	public String getNomeOggettoPrenotabile() {
		return this.dettaglioServizio.getServizio().getNomeServizio();
		// return this.getServizio().getNomeServizio();
	}

	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}

	@Override
	public String toString() {
		return "Servizio aggiuntivo: " + getNomeOggettoPrenotabile();
	}

}