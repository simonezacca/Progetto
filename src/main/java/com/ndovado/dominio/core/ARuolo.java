package com.ndovado.dominio.core;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 */

@Entity
@Table(name="ruolo")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipologia")

public abstract class ARuolo{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	/**
	 * 
	 */
	//@OneToOne(mappedBy="ruolo")
	@OneToOne
	protected Utente utente;

	/**
	 * Default constructor
	 */
	public ARuolo() {
	}

	/**
	 * @return
	 */
	public static Gestore getRuoloGestore() {
		return new Gestore();
	}

	/**
	 * @return
	 */
	public static Locatario getRuoloLocatario() {
		return new Locatario();
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
	 * @return the utente
	 */
	public Utente getUtente() {
		return utente;
	}

	/**
	 * @param utente the utente to set
	 */
	public void setUtente(Utente utente) {
		this.utente = utente;
	}

}