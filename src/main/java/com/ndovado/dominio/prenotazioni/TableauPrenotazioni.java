package com.ndovado.dominio.prenotazioni;

import java.util.*;

import com.ndovado.dominio.core.Camera;
import com.ndovado.dominio.core.Locatario;
import com.ndovado.dominio.core.Struttura;
import com.ndovado.tecservices.persistenza.base.IPersistente;
import com.ndovado.tecservices.persistenza.base.PrenotazioneDAO;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * 
 */
//@Entity
public class TableauPrenotazioni implements IPersistente {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static PrenotazioneDAO pdao = new PrenotazioneDAO();

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	protected TableauPrenotazioni() {
	}

	/**
	 * Default constructor
	 */
	public TableauPrenotazioni(Struttura s) {
		elencoPrenotazioni = new HashMap<Camera,TreeSet<Prenotazione>>();
		// collego la struttura s al tablea prenotazioni appena istanziato
		this.struttura = s;
	}

	/**
	 * 
	 */
	//@OneToMany
	// TODO implementare
	@Transient
	private Map<Camera,TreeSet<Prenotazione>> elencoPrenotazioni;

	/**
	 * 
	 */
	@OneToOne
	private Struttura struttura;


	/**
	 * @return
	 */
	public Prenotazione creaPrenotazione(Locatario l) {
		return new Prenotazione(l);
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
	public Prenotazione getPrenotazione(Long idPrenotazione) {
		Prenotazione p = pdao.get(idPrenotazione);
		return p;
	}

	/**
	 * @param DataArrivo 
	 * @param DataPartenza 
	 * @return
	 */
	public List<RisultatoRicerca> getSoluzioniDisponibili(Date DataArrivo, Date DataPartenza,Integer npersone) {
		Integer totPax = 0;
		List<RisultatoRicerca> risultati = new ArrayList<RisultatoRicerca>();
		for (Camera c : struttura.getCamereInserite()) {
			if (isCameraDisponibile(c, DataArrivo, DataPartenza)) {
				RisultatoRicerca rr = new RisultatoRicerca(struttura);
				rr.addCameraDisponibile(c);
				totPax = c.getPax();
			}
		}
		if(totPax<npersone) {
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

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elencoPrenotazioni == null) ? 0 : elencoPrenotazioni.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((struttura == null) ? 0 : struttura.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TableauPrenotazioni))
			return false;
		TableauPrenotazioni other = (TableauPrenotazioni) obj;
		if (elencoPrenotazioni == null) {
			if (other.elencoPrenotazioni != null)
				return false;
		} //else if (!elencoPrenotazioni.equals(other.elencoPrenotazioni))
		//	return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (struttura == null) {
			if (other.struttura != null)
				return false;
		} //else if (!struttura.equals(other.struttura))
		//	return false;
		return true;
	}
	
}