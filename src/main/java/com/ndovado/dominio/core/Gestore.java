package com.ndovado.dominio.core;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistence.base.IPersistente;

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
		AppLogger.debug("Istanzio nuovo: "+this.getClass().getName());
		setStruttureGestite(new ArrayList<Struttura>());
	}

	/**
	 * 
	 */
	@OneToMany(cascade=CascadeType.ALL,mappedBy = "gestore")
	private List<Struttura> struttureGestite;
	
	@Column(name="coordinatePagamento")
	private String coordinatePagamento;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getStruttureGestite() == null) ? 0 : getStruttureGestite().hashCode());
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
		if (getStruttureGestite() == null) {
			if (other.getStruttureGestite() != null)
				return false;
		} else if (!getStruttureGestite().equals(other.getStruttureGestite()))
			return false;
		return true;
	}

	/**
	 * @param aStruttura
	 */
	public void gestisciStruttura(Struttura aStruttura) {
		if (aStruttura!=null) {
			this.getStruttureGestite().add(aStruttura);
		}
	}

	/**
	 * @param aStruttura
	 */
	public void rimuoviGestioneStruttura(Struttura aStruttura) {
		if(aStruttura!=null) {
			this.getStruttureGestite().remove(aStruttura);
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

	/**
	 * @param struttureGestite the struttureGestite to set
	 */
	public void setStruttureGestite(List<Struttura> struttureGestite) {
		this.struttureGestite = struttureGestite;
	}

	/**
	 * @return the coordinatePagamento
	 */
	public String getCoordinatePagamento() {
		return coordinatePagamento;
	}

	/**
	 * @param coordinatePagamento the coordinatePagamento to set
	 */
	public void setCoordinatePagamento(String coordinatePagamento) {
		this.coordinatePagamento = coordinatePagamento;
	}

}