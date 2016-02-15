package com.ndovado.dominio.servizi;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.ndovado.tecservices.loggers.AppLogger;

/**
 * 
 */
@Entity
public class ServizioBase extends ATipologiaServizio {

	@Transient
	private final Float prezzo = new Float(0);
	/**
	 * Default constructor
	 */
	public ServizioBase(ServizioComune sc) {
		this.dettaglioServizio = new DettaglioServizio();
		this.dettaglioServizio.setServizio(sc);
		//this.setServizio(sc);
	}
	
	public ServizioBase() {
		AppLogger.debug("Istanzio nuovo: "+this.getClass().getName());
		
	}
	
	@Override
	public String toString() {
		return "Servizio base: "+this.dettaglioServizio.getServizio().getNomeServizio();
	}

}