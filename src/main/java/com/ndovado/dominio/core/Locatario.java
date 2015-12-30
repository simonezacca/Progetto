package com.ndovado.dominio.core;

import java.util.*;

import javax.persistence.Entity;
import com.ndovado.dominio.prenotazioni.Prenotazione;
import com.ndovado.tecservices.persistenza.base.IPersistente;

import javax.persistence.OneToMany;

/**
 * 
 */
@Entity
public class Locatario extends ARuolo implements IPersistente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	
	public Locatario() {
		prenotazioni = new HashSet<Prenotazione>();
	}
	
	@OneToMany(mappedBy = "locatario")
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

	@Override
	public Long getId() {
		return this.id;
	}

}