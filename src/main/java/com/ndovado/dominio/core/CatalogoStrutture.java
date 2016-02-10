package com.ndovado.dominio.core;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ndovado.dominio.prenotazioni.RisultatoRicerca;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistence.base.StrutturaDAO;

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
//		populateSetStrutture();
	}
	
//	private void populateSetStrutture() {
//		elencoStrutture = sdao.getAll();
//		AppLogger.debug("Lista popolata con "+elencoStrutture.size()+" strutture.");
//	}

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
	public Struttura selezionaStruttura(Long idStruttura) {
		return sdao.get(idStruttura);
//		for (Struttura s : elencoStrutture) {
//			if (s.getId().equals(idStruttura)) {
//				return s;
//			}
//		}
//		return null;
	}

	/**
	 * @param luogo 
	 * @param dataArrivo 
	 * @param dataPartenza 
	 * @param npersone 
	 * @return
	 */
	public List<RisultatoRicerca> cercaSoluzioni(Luogo luogo, Date dataArrivo, Date dataPartenza, Integer npersone) {
		Set<Struttura> strutture = luogo.getStruttureInLuogo();
		List<RisultatoRicerca> soluzioni = new ArrayList<RisultatoRicerca>();
		for (Struttura s : strutture) {
			RisultatoRicerca sd = s.getTableau().getSoluzioniDisponibili(dataArrivo, dataPartenza, npersone);
			if (sd.esistonoRisultati()) {
				soluzioni.add(sd);
			}
		}
		return soluzioni;
	}

	/**
	 * @return
	 */
	public Struttura creaNuovaStruttura() {
		Struttura s = new Struttura();
		// aggiungo s all'elenco delle strutture esistenti
		//elencoStrutture.add(s);
		return s;
	}
	
	public void rimuoviStruttura(Struttura s) {
		s.getLuogoStruttura().removeStruttura(s);
		s.getGestore().rimuoviGestioneStruttura(s);
		
		sdao.delete(s.getId());
		
//		if (elencoStrutture.contains(s)) {
//			// rimuovo tutte le associazioni con s prima della rimozione dal catalogo strutture
//			s.getLuogoStruttura().removeStruttura(s);
//			s.getGestore().rimuoviGestioneStruttura(s);
//			// infine elimino la struttura dal catalogo
//			elencoStrutture.remove(s);
//			// rimuovo la struttura dal DB
//			sdao.delete(s.getId());
//		}
	}
	
	public void salvaOAggiornaStruttura(Struttura smodel) {
		if (smodel!=null) {
			// salvo per la prima volta o aggiorno il model struttura su db
			sdao.saveOrUpdate(smodel);
			// aggiungo il model struttura alla lista delle strutture in elenco
//			AppLogger.debug("Aggiungo struttura salvata nel catalogo strutture");
//			elencoStrutture.add(smodel);
		}
	} 
	
	public List<Struttura> getElencoStruttureByGestore(Gestore gmodel) {
		
		Session session = sdao.getSessionFactory().openSession();
		Query q = session.createQuery("from Struttura s where s.gestore = :gestore");
		q.setParameter("gestore", gmodel);
		
		List<Struttura> smodels = q.list();
		session.close();
		return smodels;
//		for (Struttura struttura : elencoStrutture) {
//			if (struttura.getGestore().getId().equals(gmodel.getId())) {
//				smodels.add(struttura);
//			}
//		}
//		return smodels;
	}
	

}