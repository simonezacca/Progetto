package com.ndovado.dominio.core;

import java.util.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import com.ndovado.dominio.prenotazioni.Prenotazione;
import com.ndovado.helpers.core.TipoUtente;
import com.ndovado.tecservices.persistenza.base.IPersistente;

import javax.persistence.OneToMany;

/**
 * 
 */
@Entity
//@Embeddable
public class Locatario extends ARuolo implements IPersistente {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prenotazioni == null) ? 0 : prenotazioni.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Locatario))
			return false;
		Locatario other = (Locatario) obj;
		if (prenotazioni == null) {
			if (other.prenotazioni != null)
				return false;
		} else if (!prenotazioni.equals(other.prenotazioni))
			return false;
		return true;
	}

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

	@Override
	public TipoUtente getEnumTypeRuolo() {
		return TipoUtente.LOCATARIO;
	}
	
	

}