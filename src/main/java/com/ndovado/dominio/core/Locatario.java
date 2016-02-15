package com.ndovado.dominio.core;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import com.ndovado.dominio.prenotazioni.Prenotazione;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistence.base.IPersistente;

import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 
 */
@Entity
public class Locatario extends ARuolo implements IPersistente {

	@Override
	public int hashCode() {
		@SuppressWarnings("unused")
		final int prime = 31;
		int result = 1;
//		result = prime * result + ((prenotazioni == null) ? 0 : prenotazioni.hashCode());
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
		@SuppressWarnings("unused")
		Locatario other = (Locatario) obj;
//		if (prenotazioni == null) {
//			if (other.prenotazioni != null)
//				return false;
//		} else if (!prenotazioni.equals(other.prenotazioni))
//			return false;
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
		AppLogger.debug("Istanzio nuovo: "+this.getClass().getName());
		prenotazioni = new ArrayList<Prenotazione>();
	}
	
	@OneToMany(mappedBy = "locatario", fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<Prenotazione> prenotazioni;

	/**
	 * @return
	 */
	public List<Prenotazione> getPrenotazioni() {
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

	/**
	 * @param prenotazioni the prenotazioni to set
	 */
	public void setPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}
	

}