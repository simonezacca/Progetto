package com.ndovado.dominio.servizi;

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
@Table(name="tipologia_servizio")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipologia")

public abstract class ATipologiaServizio {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	
	protected Float prezzo = new Float(0);
	
	@OneToOne
	protected DettaglioServizio dettaglioServizio;
	
	/**
	 * Default constructor
	 */
	public ATipologiaServizio() {
	}
	
	public final Float getPrezzo() {
		return this.prezzo;
	}


	/**
	 * @return the dettaglioServizio
	 */
	public DettaglioServizio getDettaglioServizio() {
		return dettaglioServizio;
	}




	/**
	 * @param dettaglioServizio the dettaglioServizio to set
	 */
	public void setDettaglioServizio(DettaglioServizio dettaglioServizio) {
		this.dettaglioServizio = dettaglioServizio;
	}
	
	@Override
	public abstract String toString();

}