package com.ndovado.dominio.servizi;

import com.ndovado.tecservices.persistenza.base.IIdentificabile;

/**
 * Implementare i metodi equals() and hasCode()
 */
public class Servizio implements IIdentificabile {

	/**
	 * Default constructor
	 */
	public Servizio() {
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
	 * 
	 */
	protected Float prezzoServizio = new Float(0);

	/**
	 * 
	 */
	public ATipologiaServizio tipologiaServizio;

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

	/**
	 * @return
	 */
	public Float getPrezzoServizio() {
		return this.prezzoServizio;
	}

	/**
	 * @param aPrezzoServizio
	 */
	public void setPrezzoServizio(Float aPrezzoServizio) {
		if(aPrezzoServizio!=null && aPrezzoServizio>=0) {
			this.prezzoServizio = aPrezzoServizio;
		}
	}

}