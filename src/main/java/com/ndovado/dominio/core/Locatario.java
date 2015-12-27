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
	}

	/**
	 * @return
	 */
	public Set<Prenotazione> getPrenotazioniEffettuate() {
		// TODO implement here
		return null;
	}

	@Override
	public String toString() {
		return "Locatario";
	}

}