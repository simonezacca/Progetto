package com.ndovado.dominio.core;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.ndovado.tecservices.persistenza.base.IPersistente;

/**
 * 
 */
@Entity
//@Embeddable
public class Gestore extends ARuolo implements IPersistente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Default constructor
	 */

	
	public Gestore() {
		struttureGestite = new ArrayList<Struttura>();
	}

	/**
	 * 
	 */
	@OneToMany(cascade=CascadeType.ALL,mappedBy = "gestore")
	private List<Struttura> struttureGestite;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((struttureGestite == null) ? 0 : struttureGestite.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Gestore))
			return false;
		Gestore other = (Gestore) obj;
		if (struttureGestite == null) {
			if (other.struttureGestite != null)
				return false;
		} else if (!struttureGestite.equals(other.struttureGestite))
			return false;
		return true;
	}

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
	public List<Struttura> getStruttureGestite() {
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

}