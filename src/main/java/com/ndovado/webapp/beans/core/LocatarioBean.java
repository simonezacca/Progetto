package com.ndovado.webapp.beans.core;

public class LocatarioBean extends ARuoloBean {

	private final Integer idRadio = 0;
	private final String nameRadio = "Locatario";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "LocatarioBean [id="+id+"]";
	}

	/**
	 * @return the idradio
	 */
	public Integer getIdradio() {
		return idRadio;
	}

	/**
	 * @return the nameradio
	 */
	public String getNameradio() {
		return nameRadio;
	}
}
