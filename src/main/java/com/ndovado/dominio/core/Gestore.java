package com.ndovado.dominio.core;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.ndovado.helpers.core.TipoUtente;
import com.ndovado.tecservices.persistenza.base.IPersistente;

/**
 * 
 */
@Entity
public class Gestore extends ARuolo implements IPersistente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Default constructor
	 */

	
	public Gestore() {
		struttureGestite = new HashSet<Struttura>();
	}

	/**
	 * 
	 */
	@OneToMany(mappedBy = "proprietario")
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

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public TipoUtente getEnumTypeRuolo() {
		return TipoUtente.GESTORE;
	}

}