package com.ndovado.dominio.prenotazioni.statiprenotazione;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name="stato_prenotazione")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)

public abstract class AStatoPrenotazione {

//	@OneToOne
//	protected Prenotazione prenotazione;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	/**
	 * Default constructor
	 */
	public AStatoPrenotazione() {
	}

	/**
	 * 
	 */
	public abstract void confermaPrenotazione();

	/**
	 * 
	 */
	public abstract void completaPagamento();

	/**
	 * 
	 */
	public abstract void rifutaPrenotazione();

	/**
	 * 
	 */
	public abstract void eliminaPrenotazione();

}