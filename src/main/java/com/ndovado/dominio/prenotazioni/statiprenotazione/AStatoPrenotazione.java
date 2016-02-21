package com.ndovado.dominio.prenotazioni.statiprenotazione;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.ndovado.dominio.prenotazioni.Prenotazione;

/**
 * 
 */
@Entity
@Table(name="stato_prenotazione")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="stato")

public abstract class AStatoPrenotazione {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	protected Prenotazione prenotazione;
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


	/**
	 * @return the prenotazione
	 */
	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	/**
	 * @param prenotazione the prenotazione to set
	 */
	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}

}