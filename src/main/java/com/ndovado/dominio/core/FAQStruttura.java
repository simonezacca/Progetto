package com.ndovado.dominio.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ndovado.tecservices.persistence.base.IPersistente;

@Entity
@Table(name="faq")
public class FAQStruttura implements IPersistente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Struttura strutturaAssociata;
	
	@Column(name="domanda")
	private String domanda;
	
	@Column(name="risposta")
	private String risposta;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FAQStruttura() {
	
	}
	@Override
	public Long getId() {
		return this.id;
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
	public Struttura getStrutturaAssociata() {
		return strutturaAssociata;
	}

	/**
	 * @param strutturaAssociata the strutturaAssociata to set
	 */
	public void setStrutturaAssociata(Struttura strutturaAssociata) {
		this.strutturaAssociata = strutturaAssociata;
	}

}
