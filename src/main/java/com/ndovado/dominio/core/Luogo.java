package com.ndovado.dominio.core;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ndovado.tecservices.persistenza.base.IPersistente;

/**
 * Implementare i metodi equals() and hasCode()
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
	@GeneratedValue(strategy = IDENTITY)
	private Long idLuogo;

	/**
	 * 
	 */
	@Column(name = "nome", nullable = false, length = 45)
	private String nomeComune;

	/**
	 * 
	 */
	@Column(name = "cap", length = 45)
	private String CAP;

	/**
	 * 
	 */
	@OneToMany(mappedBy = "luogoStruttura",cascade=CascadeType.ALL)
	private List<Struttura> struttureInLuogo;

	protected Luogo() {
		
	}
	/**
	 * Default constructor
	 */
	public Luogo(String nome) {
		struttureInLuogo = new ArrayList<Struttura>();
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
	public List<Struttura> getStruttureInLuogo() {
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

	/**
	 * @return the idLuogo
	 */
	public Long getId() {
		return idLuogo;
	}

	/**
	 * @param idLuogo the idLuogo to set
	 */
	protected void setId(Long idLuogo) {
		this.idLuogo = idLuogo;
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
		if (idLuogo == null) {
			if (other.idLuogo != null)
				return false;
		} else if (!idLuogo.equals(other.idLuogo))
			return false;
		if (nomeComune == null) {
			if (other.nomeComune != null)
				return false;
		} else if (!nomeComune.equals(other.nomeComune))
			return false;
		if (struttureInLuogo == null) {
			if (other.struttureInLuogo != null)
				return false;
		} else if (!struttureInLuogo.equals(other.struttureInLuogo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Luogo [idLuogo=" + idLuogo + ", nomeComune=" + nomeComune + ", CAP=" + CAP + "]";
	}
	
	public void addStruttura(Struttura s) {
		this.getStruttureInLuogo().add(s);
	}
	
	public void removeStruttura(Struttura s) {
		if (struttureInLuogo.contains(s)) {
			struttureInLuogo.remove(s);
		}
	}

}