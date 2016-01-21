package com.ndovado.dominio.core;

import java.util.*;

import com.ndovado.dominio.prenotazioni.RisultatoRicerca;

/**
 * 
 */
public class CatalogoStrutture {

	/**
	 * Default constructor
	 */
	protected CatalogoStrutture() {
		elencoStrutture = new HashSet<Struttura>();
	}

	/**
	 * 
	 */
	private static CatalogoStrutture instance = null;

	/**
	 * 
	 */
	private static Set<Struttura> elencoStrutture;

	/**
	 * @return
	 */
	public static CatalogoStrutture getInstance() {
		if (instance==null) {
			instance = new CatalogoStrutture();
		}
		return instance;
	}

	/**
	 * @param idStruttura 
	 * @return
	 */
	public Struttura selezionaStruttura(Integer idStruttura) {
		for (Struttura s : elencoStrutture) {
			if (s.getId().equals(idStruttura)) {
				return s;
			}
		}
		return null;
	}

	/**
	 * @param luogo 
	 * @param dataArrivo 
	 * @param dataPartenza 
	 * @param npersone 
	 * @return
	 */
	public List<List<RisultatoRicerca>> cercaSoluzioni(Luogo luogo, Date dataArrivo, Date dataPartenza, Integer npersone) {
		List<Struttura> strutture = luogo.getStruttureInLuogo();
		List<List<RisultatoRicerca>> soluzioni = new ArrayList<List<RisultatoRicerca>>();
		for (Struttura s : strutture) {
			List<RisultatoRicerca> sd = s.getTableauPrenotazioni().getSoluzioniDisponibili(dataArrivo, dataPartenza, npersone);
			if (sd.size()>0) {
				soluzioni.add(sd);
			}
		}
		return soluzioni;
	}

	/**
	 * @return
	 */
	public static Struttura creaNuovaStruttura() {
		Struttura s = new Struttura();
		// aggiungo s all'elenco delle strutture esistenti
		elencoStrutture.add(s);
		return s;
	}
	
	public static void removeStruttura(Struttura s) {
		if (elencoStrutture.contains(s)) {
			// rimuovo tutte le associazioni con s prima della rimozione dal catalogo strutture
			s.getLuogoStruttura().removeStruttura(s);
			s.getProprietario().rimuoviGestioneStruttura(s);
			// infine elimino la struttura dal catalogo
			elencoStrutture.remove(s);
		}
	}

}