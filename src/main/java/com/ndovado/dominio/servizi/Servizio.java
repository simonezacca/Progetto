package com.ndovado.dominio.servizi;

/**
 * Implementare i metodi equals() and hasCode()
 */
public class Servizio {

	/**
	 * Default constructor
	 */
	public Servizio() {
	}

	/**
	 * @return the idServizio
	 */
	public Integer getIdServizio() {
		return idServizio;
	}

	/**
	 * @param idServizio the idServizio to set
	 */
	public void setIdServizio(Integer idServizio) {
		this.idServizio = idServizio;
	}

	/**
	 * 
	 */
	private Integer idServizio;

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