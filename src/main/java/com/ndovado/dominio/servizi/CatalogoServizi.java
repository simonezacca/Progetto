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
		serviziDisponibili = new HashSet<Servizio>();
	}

	/**
	 * 
	 */
	private static CatalogoServizi istance;

	/**
	 * 
	 */
	private Set<Servizio> serviziDisponibili;

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
	public Servizio getServizio(Long idServizio) {
		for (Servizio servizio : serviziDisponibili) {
			if (servizio.getId() == idServizio) {
				return servizio;
			}
		}
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