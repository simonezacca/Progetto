package com.ndovado.webapp.beans.core;

import java.util.List;

import com.ndovado.webapp.beans.prenotazioni.PrenotazioneBean;

public class LocatarioBean extends ARuoloBean {

	private final Long idRadio = new Long(0);
	private final String nameRadio = "Locatario";
	
	private List<PrenotazioneBean> prenotazioni;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LocatarioBean() {
		
	}

	public String toString() {
		return "LocatarioBean [id="+id+"]";
	}

	/**
	 * @return the idradio
	 */
	public Long getIdradio() {
		return idRadio;
	}

	/**
	 * @return the nameradio
	 */
	public String getNameradio() {
		return nameRadio;
	}

	/**
	 * @return the prenotazioni
	 */
	public List<PrenotazioneBean> getPrenotazioni() {
		return prenotazioni;
	}

	/**
	 * @param prenotazioni the prenotazioni to set
	 */
	public void setPrenotazioni(List<PrenotazioneBean> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}
}
