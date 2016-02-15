package com.ndovado.webapp.beans.core;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="FAQStrutturaBean")
public class FAQStrutturaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String domanda;
	private String risposta;
	
	private StrutturaBean strutturaAssociata;
	
	public FAQStrutturaBean() {
		// TODO Auto-generated constructor stub
	}
	
	public FAQStrutturaBean(StrutturaBean sbean) {
		this.strutturaAssociata = sbean;
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
	 * @return the domanda
	 */
	public String getDomanda() {
		return domanda;
	}

	/**
	 * @param domanda the domanda to set
	 */
	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}

	/**
	 * @return the risposta
	 */
	public String getRisposta() {
		return risposta;
	}

	/**
	 * @param risposta the risposta to set
	 */
	public void setRisposta(String risposta) {
		this.risposta = risposta;
	}

	/**
	 * @return the strutturaAssociata
	 */
	public StrutturaBean getStrutturaAssociata() {
		return strutturaAssociata;
	}

	/**
	 * @param strutturaAssociata the strutturaAssociata to set
	 */
	public void setStrutturaAssociata(StrutturaBean strutturaAssociata) {
		this.strutturaAssociata = strutturaAssociata;
	}

}
