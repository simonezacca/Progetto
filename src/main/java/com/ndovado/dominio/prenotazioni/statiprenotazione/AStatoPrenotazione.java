package com.ndovado.dominio.prenotazioni.statiprenotazione;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ndovado.dominio.prenotazioni.Prenotazione;

/**
 * 
 */
@Entity
@Table(name="stato_prenotazione")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="stato")
//@Embeddable
public abstract class AStatoPrenotazione {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	
	@OneToOne
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

}