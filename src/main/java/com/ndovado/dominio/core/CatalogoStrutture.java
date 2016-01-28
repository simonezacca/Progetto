package com.ndovado.dominio.core;

import java.util.*;

import com.ndovado.dominio.prenotazioni.RisultatoRicerca;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistence.base.StrutturaDAO;
import com.ndovado.tecservices.persistence.base.UtenteDAO;

/**
 * 
 */
public class CatalogoStrutture {

	private static StrutturaDAO sdao = new StrutturaDAO();
	/**
	 * Default constructor
	 */
	protected CatalogoStrutture() {
		AppLogger.debug("Instazione set per elenco strutture.");
		//elencoStrutture = new HashSet<Struttura>();
		populateSetStrutture();
	}
	
	private void populateSetStrutture() {
		elencoStrutture = sdao.getAll();
		AppLogger.debug("Lista popolata con "+elencoStrutture.size()+" strutture.");
	}

	/**
	 * 
	 */
	private static CatalogoStrutture instance = null;

	/**
	 * 
	 */
	private static List<Struttura> elencoStrutture = new ArrayList<Struttura>();

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
			s.getGestore().rimuoviGestioneStruttura(s);
			// infine elimino la struttura dal catalogo
			elencoStrutture.remove(s);
			// rimuovo la struttura dal DB
			sdao.delete(s.getId());
		}
	}
	
	public void salvaOAggiornaStruttura(Struttura smodel) {
		if (smodel!=null) {
			// salvo per la prima volta o aggiorno il model struttura su db
			sdao.saveOrUpdate(smodel);
			// aggiungo il model struttura alla lista delle strutture in elenco
			AppLogger.debug("Aggiungo struttura salvata nel catalogo strutture");
			elencoStrutture.add(smodel);
		}
	} 

}