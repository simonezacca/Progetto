package com.ndovado.webapp.beans.core;

import java.io.Serializable;


public abstract class ARuoloBean implements Identifiable, Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	
	protected Long id = null;
	protected UtenteBean utente;
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
	 * @return the utente
	 */
	public UtenteBean getUtente() {
		return utente;
	}
	/**
	 * @param utente the utente to set
	 */
	public void setUtente(UtenteBean utente) {
		this.utente = utente;
	}

	public Boolean isNewBean() {
		return this.id==0||this.id==null;
	}
	
	public abstract String toString();
}
