package com.ndovado.webapp.beans.servizi;

import java.io.Serializable;

public class ServizioComuneBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id = null;
	private String nomeServizio;
	
	public ServizioComuneBean() {
		
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
	 * @return the nomeServizio
	 */
	public String getNomeServizio() {
		return nomeServizio;
	}
	/**
	 * @param nomeServizio the nomeServizio to set
	 */
	public void setNomeServizio(String nomeServizio) {
		this.nomeServizio = nomeServizio;
	}
	@Override
	public String toString() {
		return "ServizioComuneBean [id=" + id + ", nomeServizio=" + nomeServizio + "]";
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
		if (!(obj instanceof ServizioComuneBean))
			return false;
		ServizioComuneBean other = (ServizioComuneBean) obj;
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
