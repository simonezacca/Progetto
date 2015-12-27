package com.ndovado.dominio.servizi;

import java.util.*;

/**
 * 
 */
public class CatalogoServizi {

	/**
	 * Default constructor
	 */
	protected CatalogoServizi() {
	}

	/**
	 * 
	 */
	private static CatalogoServizi istance;

	/**
	 * 
	 */
	public Set<Servizio> serviziDisponibili;

	/**
	 * @return
	 */
	public static CatalogoServizi getIstance() {
		if (istance==null) {
			istance = new CatalogoServizi();
		}
		return istance;
	}

	/**
	 * @param idServizio 
	 * @return
	 */
	public Servizio getServizio(Integer idServizio) {
		// TODO implement here
		return null;
	}

	/**
	 * @param nomeServizio 
	 * @return
	 */
	public Set<Servizio> cercaServizioPerNome(String nomeServizio) {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public static Servizio creaNuovoServizio() {
		// TODO implement here
		return null;
	}

}