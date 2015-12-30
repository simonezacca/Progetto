package com.ndovado.dominio.servizi;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ndovado.tecservices.persistenza.base.IPersistente;

/**
 * Implementare i metodi equals() and hasCode()
 */
@Entity
@Table(name = "servizio_comune")
public class ServizioComune implements IPersistente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long idServizio;

	/**
	 * 
	 */
	@Column(name = "nome")
	protected String nomeServizio;

	/**
	 * Default constructor
	 */
	public ServizioComune() {
	}

	/**
	 * @return the idServizio
	 */
	public Long getId() {
		return idServizio;
	}

	/**
	 * @param idServizio the idServizio to set
	 */
	protected void setId(Long idServizio) {
		this.idServizio = idServizio;
	}

	/**
	 * @return
	 */
	public String getNomeServizio() {
		return this.nomeServizio;
	}

	/**
	 * @param aNomeServizio
	 */
	public void setNomeServizio(String aNomeServizio) {
		if (aNomeServizio!=null) {
			this.nomeServizio = aNomeServizio;
		}
	}

}