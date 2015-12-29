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
		serviziDisponibili = new HashSet<ServizioComune>();
	}

	/**
	 * 
	 */
	private static CatalogoServizi istance;

	/**
	 * 
	 */
	private Set<ServizioComune> serviziDisponibili;

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
	public ServizioComune getServizio(Long idServizio) {
		for (ServizioComune servizio : serviziDisponibili) {
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
	public Set<ServizioComune> cercaServizioPerNome(String nomeServizio) {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public static ServizioComune creaNuovoServizio() {
		// TODO implement here
		return null;
	}

}