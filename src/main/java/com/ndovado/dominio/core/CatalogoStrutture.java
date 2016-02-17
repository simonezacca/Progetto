package com.ndovado.dominio.core;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.joda.time.LocalDate;

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
	}

	/**
	 * 
	 */
	private static volatile CatalogoStrutture instance = null;

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static List<Struttura> elencoStrutture = new ArrayList<Struttura>();

	/**
	 * @return
	 */
	public static CatalogoStrutture getInstance() {
		if (instance==null) {
			synchronized (CatalogoStrutture.class) {
				if (instance==null)
					instance = new CatalogoStrutture();
			}
		}
		return instance;
	}

	/**
	 * @param idStruttura 
	 * @return
	 */
	public Struttura selezionaStruttura(Long idStruttura) {
		return sdao.get(idStruttura);
	}

	/**
	 * @param luogo 
	 * @param dataArrivo 
	 * @param dataPartenza 
	 * @param npersone 
	 * @return
	 */
	public List<RisultatoRicerca> cercaSoluzioni(Luogo luogo, LocalDate dataArrivo, LocalDate dataPartenza, Integer npersone) {
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
		return s;
	}
	
	public void rimuoviStruttura(Struttura s) {
		s.getLuogoStruttura().removeStruttura(s);
		s.getGestore().rimuoviGestioneStruttura(s);
		
		sdao.delete(s.getId());
	}
	
	public void salvaOAggiornaStruttura(Struttura smodel) {
		if (smodel!=null) {
			// salvo per la prima volta o aggiorno il model struttura su db
			sdao.saveOrUpdate(smodel);
		}
	} 
	
	public List<Struttura> getElencoStruttureByGestore(Gestore gmodel) {
		
		@SuppressWarnings("static-access")
		Session session = sdao.getSessionFactory().openSession();
		Query q = session.createQuery("from Struttura s where s.gestore = :gestore");
		q.setParameter("gestore", gmodel);
		
		@SuppressWarnings("unchecked")
		List<Struttura> smodels = q.list();
		session.close();
		return smodels;
	}
	

}