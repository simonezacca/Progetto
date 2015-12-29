package com.ndovado.dominio.servizi;

import com.ndovado.tecservices.persistenza.base.IPersistente;

/**
 * Implementare i metodi equals() and hasCode()
 */
public class ServizioComune implements IPersistente {

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
	 * 
	 */
	private Long idServizio;

	/**
	 * 
	 */
	protected String nomeServizio;

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