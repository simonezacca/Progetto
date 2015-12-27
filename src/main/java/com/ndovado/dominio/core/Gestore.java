package com.ndovado.dominio.core;

import java.util.*;

/**
 * 
 */
public class Gestore extends ARuolo {

	/**
	 * Default constructor
	 */
	public Gestore() {
	}

	/**
	 * 
	 */
	private Set<Struttura> struttureGestite;

	/**
	 * @param aStruttura
	 */
	public void gestisciStruttura(Struttura aStruttura) {
		if (aStruttura!=null) {
			this.struttureGestite.add(aStruttura);
		}
	}

	/**
	 * @param aStruttura
	 */
	public void rimuoviGestioneStruttura(Struttura aStruttura) {
		if(aStruttura!=null) {
			this.struttureGestite.remove(aStruttura);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Set<Struttura> getStruttureGestite() {
		return this.struttureGestite;
	}
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Gestore";
	}

}