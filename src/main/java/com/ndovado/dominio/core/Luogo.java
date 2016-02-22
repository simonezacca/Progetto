package com.ndovado.dominio.core;


import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistence.base.IPersistente;

/**
 * Classe di entita luogo, modello il concetto di luogo fisico
 * @author antonio
 *
 */
@Entity
@Table(name = "comune")
public class Luogo implements IPersistente {

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
	@Column(name = "comune", nullable = false, length = 45)
	private String nomeComune;

	/**
	 * 
	 */
	@Column(name = "cap", length = 45)
	private String CAP;

	/**
	 * 
	 */
	@Column(name="provincia")
	private String provincia;
	
	@OneToMany(mappedBy = "luogoStruttura",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	//@Fetch(FetchMode.SELECT)
	private Set<Struttura> struttureInLuogo = new HashSet<Struttura>();

	public Luogo() {
		AppLogger.debug("Istanzio nuovo: "+this.getClass().getName());
	}
	/**
	 * Default constructor
	 */
	public Luogo(String nome) {
		this.nomeComune = nome;
	}

	/**
	 * @return
	 */
	public String getNomeComune() {
		return nomeComune;
	}

	/**
	 * @param aNomeComune
	 */
	public void setNomeComune(String aNomeComune) {
		if (aNomeComune!=null) {
			this.nomeComune = aNomeComune;
		}
	}

	/**
	 * @return
	 */
	public Set<Struttura> getStruttureInLuogo() {
		return struttureInLuogo;
	}

	/**
	 * @return
	 */
	public String getCAP() {
		return CAP;
	}

	/**
	 * @param aCap
	 */
	public void setCAP(String aCap) {
		if (aCap!=null) {
			this.CAP = aCap;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Luogo))
			return false;
		Luogo other = (Luogo) obj;
		if (CAP == null) {
			if (other.CAP != null)
				return false;
		} else if (!CAP.equals(other.CAP))
			return false;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		if (nomeComune == null) {
			if (other.nomeComune != null)
				return false;
		} else if (!nomeComune.equals(other.nomeComune))
			return false;
		if (getStruttureInLuogo() == null) {
			if (other.getStruttureInLuogo() != null)
				return false;
		} //else if (!getStruttureInLuogo().equals(other.getStruttureInLuogo()))
		//	return false;
		return true;
	}

	public void addStruttura(Struttura s) {
		this.getStruttureInLuogo().add(s);
	}
	
	public void removeStruttura(Struttura s) {
		if (getStruttureInLuogo().contains(s)) {
			getStruttureInLuogo().remove(s);
		}
	}
	@Override
	public String toString() {
		return "Luogo [idLuogo=" + getId() + ", nomeComune=" + nomeComune + ", CAP=" + CAP + ", provincia=" + provincia
				+ "]";
	}
	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}
	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @param struttureInLuogo the struttureInLuogo to set
	 */
	public void setStruttureInLuogo(Set<Struttura> struttureInLuogo) {
		this.struttureInLuogo = struttureInLuogo;
	}

}