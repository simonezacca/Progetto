package com.ndovado.dominio.prenotazioni.statiprenotazione;

import javax.persistence.Entity;

import org.hibernate.annotations.Proxy;

import com.ndovado.tecservices.loggers.AppLogger;

/**
 * 
 */
@Entity
@Proxy(lazy=false,proxyClass=StatoPendente.class)
public class StatoPendente extends AStatoPrenotazione {

	@Override
	public String toString() {
		return "StatoPendente []";
	}

	/**
	 * Default constructor
	 */
	public StatoPendente() {
		AppLogger.debug("Istanzio nuovo: "+this.getClass().getName());
	}

	/**
	 * 
	 */
	public void confermaPrenotazione() {
	}

	/**
	 * 
	 */
	public void completaPagamento() {
	}

	/**
	 * 
	 */
	public void rifutaPrenotazione() {
	}

	/**
	 * 
	 */
	public void eliminaPrenotazione() {
	}

}