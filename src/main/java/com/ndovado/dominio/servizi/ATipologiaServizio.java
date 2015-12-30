package com.ndovado.dominio.servizi;

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
@Table(name="tipologia_servizio")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)

public abstract class ATipologiaServizio {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	protected Float prezzo = new Float(0);
	
	@OneToOne
	protected ServizioComune servizio;
	/**
	 * Default constructor
	 */
	public ATipologiaServizio() {
	}
	
	public ServizioComune getServizioAssociato() {
		return this.servizio;
	}
	
	public Float getPrezzo() {
		return this.prezzo;
	}

}