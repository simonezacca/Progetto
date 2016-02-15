package com.ndovado.dominio.servizi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistence.base.IPersistente;

/**
 * Implementare i metodi equals() and hasCode()
 */

@Entity
@Table(name = "servizio")

public class ServizioComune implements IPersistente {

	@Override
	public String toString() {
		return "ServizioComune [idServizio=" + id + ", nomeServizio=" + nomeServizio + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 
	 */
	@Column(name = "nome")
	private String nomeServizio;

	// necessario per hibernate
	public ServizioComune() {
		AppLogger.debug("Istanzio nuovo: "+this.getClass().getName());
		
	}
	/**
	 * Default constructor
	 */
	public ServizioComune(String aNomeServizio) {
		this.nomeServizio = aNomeServizio;
	}

	/**
	 * @return the idServizio
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param idServizio the idServizio to set
	 */
	public void setId(Long idServizio) {
		this.id = idServizio;
	}

	/**
	 * @return
	 */
	public String getNomeServizio() {
		return this.nomeServizio;
	}

	/**
	 * @param aNomeServizio
	 */
	public void setNomeServizio(String aNomeServizio) {
		if (aNomeServizio!=null) {
			this.nomeServizio = aNomeServizio;
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomeServizio == null) ? 0 : nomeServizio.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ServizioComune))
			return false;
		ServizioComune other = (ServizioComune) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeServizio == null) {
			if (other.nomeServizio != null)
				return false;
		} else if (!nomeServizio.equals(other.nomeServizio))
			return false;
		return true;
	}

}