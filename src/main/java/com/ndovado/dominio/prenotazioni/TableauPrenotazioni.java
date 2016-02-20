package com.ndovado.dominio.prenotazioni;

import java.util.*;

import com.ndovado.dominio.core.Camera;
import com.ndovado.dominio.core.Locatario;
import com.ndovado.dominio.core.Struttura;
import com.ndovado.exceptions.prenotazioni.OverbookingException;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistence.base.PrenotazioneDAO;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.LocalDate;

/**
 * 
 */

public class TableauPrenotazioni {
	/**
	 * 
	 */

	private static PrenotazioneDAO pdao = new PrenotazioneDAO();
	
	private Boolean test= false;

	public TableauPrenotazioni() {
		AppLogger.debug("Istanzio nuovo: "+this.getClass().getName());
	}

	public TableauPrenotazioni(Struttura s) {
		// collego la struttura s al tablea prenotazioni appena istanziato
		this.struttura = s;
		initMapTableau();
		initTableau();
	}

	/**
	 * 
	 */

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
	
	public Prenotazione salvaOAggiornaPrenotazione(Prenotazione pmodel) throws OverbookingException {
		// controllo overbooking
		checkOverBooking(pmodel);
		if (test) {
			// salvo solo nella mappa in ram
			for (Camera c : getCamereFromPrenotazione(pmodel)) {
				inserisciPrenotazioneInTableau(c, pmodel);
			}
		} else {
			// salvo solo nel DB
			pdao.saveOrUpdate(pmodel);
		}
		
		return pmodel;
	}
	
	private void checkOverBooking(Prenotazione pmodel) throws OverbookingException {
		synchronized (this) {
			List<Camera> camereDaInserire = getCamereFromPrenotazione(pmodel);
			for (Camera camera : camereDaInserire) {
				if (!isCameraDisponibile(camera, pmodel.getDataArrivo(), pmodel.getDataPartenza())) {
					throw new OverbookingException();
				}
			}
		}
	}

	
	private void inserisciPrenotazioneInTableau(Camera c, Prenotazione pmodel) {
		if (elencoPrenotazioni.containsKey(c)) {
			TreeSet<Prenotazione> elencoP = elencoPrenotazioni.get(c);
			elencoP.add(pmodel);
		} else {
			TreeSet<Prenotazione> elencoP = new TreeSet<Prenotazione>();
			elencoP.add(pmodel);
			elencoPrenotazioni.put(c, elencoP);
		}
	}
	
	private List<Camera> getCamereFromPrenotazione(Prenotazione pmodel) {
		List<LineaPrenotazione> list = pmodel.getLineePrenotazione();
		List<Camera> camere = new ArrayList<Camera>();
		for (LineaPrenotazione lineaPrenotazione : list) {
			if(lineaPrenotazione.getOggettoPrenotato() instanceof Camera) {
				Camera c = (Camera) lineaPrenotazione.getOggettoPrenotato();
				camere.add(c);
			}
		}
		return camere;
	}
	
	public void cancellaPrenotazione(Prenotazione pmodel) {
		pdao.delete(pmodel.getId());
	}

	/**
	 * @param DataArrivo 
	 * @param DataPartenza 
	 * @return
	 */
	public RisultatoRicerca getSoluzioniDisponibili(LocalDate DataArrivo, LocalDate DataPartenza,Integer npersone) {
		// numero massimo di persone che possono alloggiare nella struttura
		Integer totPax = 0;
		RisultatoRicerca rr = new RisultatoRicerca(struttura);
		for (Camera c : struttura.getCamereInserite()) {
			if (isCameraDisponibile(c, DataArrivo, DataPartenza)) {
				rr.addCameraDisponibile(c);
				// incremento il numero dei possibili alloggiati
				totPax += c.getPax();
			}
		}
		if(totPax<npersone) {
			// se la disponibilità delle camere non è sufficiente per il numero delle persone richieste
			// azzero l'elenco delle camere
			rr.getCamereLibere().clear();
		}
		return rr;
	}

	/**
	 * @param c 
	 * @param da 
	 * @param dp 
	 * @return
	 */
	private Boolean isCameraDisponibile(final Camera c, LocalDate da, LocalDate dp) {
		// controllare prima che nella descrizione camera corrente
		// sia disponibile il periodo di prenotazione
		if(c.getDataInizioAffitto().isAfter(da) || c.getDataFineAffitto().isBefore(dp) )
			return false;
		
		Boolean risultato = false;
		Set<Prenotazione> sp = getElencoPrenotazioniFuturePerCamera(c); // navigable set ordinato per dataArrivo asc
		Iterator<Prenotazione> i = sp.iterator();
		
		while(i.hasNext() && !risultato) {
			Prenotazione p = i.next();
			risultato = p.isDateSovrapposte(da, dp);
		}
		return !risultato;
	}

	/**
	 * @param c 
	 * @return
	 */
	protected Set<Prenotazione> getElencoPrenotazioniPerCamera(Camera c) {
		Set<Prenotazione> s = getInsiemePrenotazioniPerCamera(c);
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
		TreeSet<Prenotazione> s = (TreeSet<Prenotazione>) getElencoPrenotazioniPerCamera(c);
		LocalDate oggi = new LocalDate();
		for (Prenotazione p : s)
			if (p.getDataPartenza().isAfter(oggi))
				return s.tailSet(p);
		return new TreeSet<Prenotazione>(); // ritorno insieme vuoto in caso di mancanza prenotazioni future
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elencoPrenotazioni == null) ? 0 : elencoPrenotazioni.hashCode());
		//result = prime * result + ((struttura == null) ? 0 : struttura.hashCode());
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
		}
		else if (struttura == null) {
			if (other.struttura != null)
				return false;
		}
		return true;
	}
	
	private void initMapTableau() {
		if (elencoPrenotazioni==null) {
			elencoPrenotazioni = new HashMap<Camera,TreeSet<Prenotazione>>();
		}
	}
	
	private void initTableau() {
		// passo 1: ottengo lista camere della struttura
		// passo 2: instanziare la map e usare la camera come chiave
		// passo 3: per ogni camera ottenere l'elenco delle prenotazioni
		// 			tramite query SQL o HQL se DIO vuole
		// passo 4: inserisco coppia <K,V> come <Camera,Set<Prenotazione>>
		List<Camera> elencoCamere = struttura.getCamereInserite();
		initMapTableau();
		for (Camera camera : elencoCamere) {
			TreeSet<Prenotazione> pPerCamera = getInsiemePrenotazioniPerCamera(camera);
			elencoPrenotazioni.put(camera, pPerCamera);
		}
	}
	
	@SuppressWarnings("unchecked")
	private TreeSet<Prenotazione> getInsiemePrenotazioniPerCamera(Camera c) {
		// caso test junit senza persistenza su DB
		if (test) {
			return getInsiemePrenotazioniPerCameraDaMap(c);
		}
		// --------------------------------------------------
		//	Query esempio
				
		//	select p.* from Prenotazione p
		//	join LineaPrenotazione lp
		//		on p.idPrenotazione = lp.prenotazioneCorrente_idPrenotazione
		//	join Camera c
		//		on c.id = lp.oggetto_id
		//	where c.id = 2 and lp.tipo_oggetto = 1
		
		String queryElencoCamere = "select p.* from prenotazione p "
				+ "join linea_prenotazione lp "
				+ "on p.id = lp.prenotazioneCorrente_id "
				+ "join camera c	on c.id = lp.oggetto_id "
				+ "where c.id = "+c.getId().toString()+" and lp.tipo_oggetto = 1;";
		
		TreeSet<Prenotazione> insiemePrenotazioni = new TreeSet<Prenotazione>();
		@SuppressWarnings("static-access")
		SessionFactory sf = pdao.getSessionFactory();
		Session session = sf.openSession();
		
		Query q = session.createSQLQuery(queryElencoCamere).addEntity(Prenotazione.class);
		List<Prenotazione> lista = q.list();
		for (Prenotazione prenotazione : lista) {
			insiemePrenotazioni.add(prenotazione);
		}
		
		session.close();
		
		return insiemePrenotazioni;
	}
	
	private TreeSet<Prenotazione> getInsiemePrenotazioniPerCameraDaMap(Camera c) {
		if (elencoPrenotazioni.containsKey(c)) {
			return elencoPrenotazioni.get(c);
		}
		return new TreeSet<Prenotazione>();
	}

	@Override
	public String toString() {
		return "TableauPrenotazioni [struttura=" + struttura + ", elencoPrenotazioni=" + elencoPrenotazioni + "]";
	}

	/**
	 * @return the test
	 */
	public Boolean getTest() {
		return test;
	}

	/**
	 * @param test the test to set
	 */
	public void setTest(Boolean test) {
		this.test = test;
	}
	
}