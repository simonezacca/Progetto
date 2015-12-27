package com.ndovado.dominio.prenotazioni;

import java.util.*;

import com.ndovado.dominio.core.Camera;
import com.ndovado.dominio.core.Struttura;

/**
 * 
 */
public class TableauPrenotazioni {

	/**
	 * Default constructor
	 */
	public TableauPrenotazioni() {
	}

	/**
	 * 
	 */
	private Map<Camera,TreeSet<Prenotazione>> elencoPrenotazioni = new HashMap<Camera,TreeSet<Prenotazione>>();

	/**
	 * 
	 */
	private Struttura struttura;


	/**
	 * @return
	 */
	public Prenotazione creaPrenotazione() {
		// TODO implement here
		return null;
	}

	/**
	 * @param aPrenotazione
	 */
	public void confermaPrenotazione(Prenotazione aPrenotazione) {
		if (aPrenotazione!=null) {
			aPrenotazione.getStatoPrenotazione().confermaPrenotazione();
		}
	}

	/**
	 * @param idPrenotazione 
	 * @return
	 */
	public Prenotazione getPrenotazione(String idPrenotazione) {
		// TODO implement here
		return null;
	}

	/**
	 * @param DataArrivo 
	 * @param DataPartenza 
	 * @return
	 */
	public List<RisultatoRicerca> getSoluzioniDisponibili(Date DataArrivo, Date DataPartenza,Integer npersone) {
		Integer totPax = 0;
		List<RisultatoRicerca> risultati = new ArrayList<RisultatoRicerca>();
		for (Camera c : struttura.getCamere()) {
			if (isCameraDisponibile(c, DataArrivo, DataPartenza)) {
				RisultatoRicerca rr = new RisultatoRicerca(struttura);
				rr.addCameraDisponibile(c);
				totPax = c.getPax();
			}
		}
		if (totPax<npersone) {
			// se la disponibilità delle camere non è sufficiente per il numero delle persone richieste
			// faccio tornare una lista vuota
			risultati.clear();
		}
		return risultati;
	}

	/**
	 * @param c 
	 * @param da 
	 * @param dp 
	 * @return
	 */
	private Boolean isCameraDisponibile(Camera c, Date da, Date dp) {
		Boolean risultato = false;
		Set<Prenotazione> sp = getElencoPrenotazioniFuturePerCamera(c); // navigable set ordinato per dataArrivo asc
		Iterator<Prenotazione> i = sp.iterator();
		while(i.hasNext() && !risultato) {
			Prenotazione p = i.next();
			risultato = p.isDateSovrapposte(da, dp);
		}
		return risultato;
	}

	/**
	 * @param c 
	 * @return
	 */
	protected Set<Prenotazione> getElencoPrenotazioniPerCamera(Camera c) {
		Set<Prenotazione> s = elencoPrenotazioni.get(c);
		return s;
	}

	/**
	 * scorri elenco prenotazioni ordinato per data asc
	 * trova p: prenotazione più vicina dopo di oggi
	 * return TailSet(p):NavigableSet di prenotazioni
	 * @param c 
	 * @return
	 */
	protected Set<Prenotazione> getElencoPrenotazioniFuturePerCamera(Camera c) {
		TreeSet<Prenotazione> s = elencoPrenotazioni.get(c);
		Date oggi = new Date();
		for (Prenotazione p : s)
			if (p.getDataArrivo().after(oggi))
				return s.tailSet(p);
		return new TreeSet<Prenotazione>(); // ritorno insieme vuoto in caso di mancanza prenotazioni future
	}
	
}