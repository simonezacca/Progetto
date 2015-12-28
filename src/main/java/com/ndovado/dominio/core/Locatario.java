package com.ndovado.dominio.core;

import java.util.*;

import com.ndovado.dominio.prenotazioni.Prenotazione;

/**
 * 
 */
public class Locatario extends ARuolo {

	/**
	 * Default constructor
	 */
	public Locatario() {
		prenotazioni = new HashSet<Prenotazione>();
	}
	
	private Set<Prenotazione> prenotazioni;

	/**
	 * @return
	 */
	public Set<Prenotazione> getPrenotazioniEffettuate() {
		return prenotazioni;
	}

	public void addPrenotazione(Prenotazione p) {
		if (p!=null) {
			this.prenotazioni.add(p);
		}
	}
	@Override
	public String toString() {
		return "Locatario";
	}

}