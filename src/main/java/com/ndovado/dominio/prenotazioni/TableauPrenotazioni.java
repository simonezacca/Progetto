package com.ndovado.dominio.prenotazioni;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ndovado.dominio.core.Camera;
import com.ndovado.dominio.core.DescrizioneCamera;
import com.ndovado.dominio.core.Locatario;
import com.ndovado.dominio.core.Struttura;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistenza.base.IPersistente;
import com.ndovado.tecservices.persistenza.base.PrenotazioneDAO;
import com.ndovado.tecservices.persistenza.base.StrutturaDAO;
import com.sun.org.apache.regexp.internal.recompile;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.apache.commons.collections.iterators.IteratorEnumeration;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * 
 */

public class TableauPrenotazioni {
	/**
	 * 
	 */

	private static PrenotazioneDAO pdao = new PrenotazioneDAO();

	protected TableauPrenotazioni() {
	}

	/**
	 * Default constructor
	 */
	
	public static void main (String [] args) {
		
		StrutturaDAO sdao = new StrutturaDAO();
		Struttura s = sdao.get(new Long(1));
		TableauPrenotazioni tp = new  TableauPrenotazioni(s);
		
		AppLogger.debug(tp.elencoPrenotazioni.toString());
		
		try {
			tp.doTest(tp, 1, "22/01/2016", "23/01/2016", 3);
			tp.doTest(tp, 2, "22/01/2016", "23/01/2016", 2);
			tp.doTest(tp, 3, "22/01/2016", "23/01/2016", 4);
			tp.doTest(tp, 4, "01/02/2016", "04/02/2016", 5);
			tp.doTest(tp, 5, "01/02/2016", "04/02/2016", 3);
			tp.doTest(tp, 6, "01/02/2016", "04/02/2016", 2);
			tp.doTest(tp, 7, "29/01/2016", "30/01/2016", 5);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	private Map<Long,TreeSet<Prenotazione>> elencoPrenotazioni;

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
		// numero massimo di persone che possono alloggiare nella struttura
		Integer totPax = 0;
		List<RisultatoRicerca> risultati = new ArrayList<RisultatoRicerca>();
		for (Camera c : struttura.getCamereInserite()) {
			if (isCameraDisponibile(c, DataArrivo, DataPartenza)) {
				RisultatoRicerca rr = new RisultatoRicerca(struttura);
				rr.addCameraDisponibile(c);
				risultati.add(rr);
				// incremento il numero dei possibili alloggiati
				totPax += c.getPax();
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
	private Boolean isCameraDisponibile(final Camera c, Date da, Date dp) {
		// controllare prima che nella descrizione camera corrente
		// sia disponibile il periodo di prenotazione
		DescrizioneCamera d = c.getDescrizioneCorrente();
		if(d.getDataInizioAffitto().after(da) || d.getDataFineAffitto().before(dp) )
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
		Set<Prenotazione> s = elencoPrenotazioni.get(c.getId());
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
		Date oggi = new Date();
		for (Prenotazione p : s)
			if (p.getDataPartenza().after(oggi))
				return s.tailSet(p);
		return new TreeSet<Prenotazione>(); // ritorno insieme vuoto in caso di mancanza prenotazioni future
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elencoPrenotazioni == null) ? 0 : elencoPrenotazioni.hashCode());
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
		else if (struttura == null) {
			if (other.struttura != null)
				return false;
		} //else if (!struttura.equals(other.struttura))
		//	return false;
		return true;
	}
	
	private void initMapTableau() {
		if (elencoPrenotazioni==null) {
			elencoPrenotazioni = new HashMap<Long,TreeSet<Prenotazione>>();
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
			elencoPrenotazioni.put(camera.getId(), pPerCamera);
		}
	}
	
	@SuppressWarnings("unchecked")
	private TreeSet<Prenotazione> getInsiemePrenotazioniPerCamera(Camera c) {
		//	Query esempio
				
		//	select p.* from Prenotazione p
		//	join LineaPrenotazione lp
		//		on p.idPrenotazione = lp.prenotazioneCorrente_idPrenotazione
		//	join Camera c
		//		on c.id = lp.oggetto_id
		//	where c.id = 2 and lp.tipo_oggetto = 1
		
		String queryElencoCamere = "select p.* from prenotazione p "
				+ "join linea_prenotazione lp "
				+ "on p.idPrenotazione = lp.prenotazioneCorrente_idPrenotazione "
				+ "join camera c	on c.id = lp.oggetto_id "
				+ "where c.id = "+c.getId().toString()+" and lp.tipo_oggetto = 1;";
		
		TreeSet<Prenotazione> insiemePrenotazioni = new TreeSet<Prenotazione>();
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

	@Override
	public String toString() {
		return "TableauPrenotazioni [struttura=" + struttura + ", elencoPrenotazioni=" + elencoPrenotazioni + "]";
	}
	
	private void doTest(TableauPrenotazioni tp,Integer ntest, String daString, String aString, Integer npersone) throws ParseException {
		
		DateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
		Date dataCI = formatter.parse(daString);
		Calendar calendar = Calendar.getInstance();
		Date dataCO = formatter.parse(aString);
		
		calendar.setTime(dataCI);
		dataCI = calendar.getTime();
		
		calendar.setTime(dataCO);
		dataCO = calendar.getTime();
		
		
//		Calendar cal = Calendar.getInstance();
//		cal.set(2016, 0, 27);
//		Date da = cal.getTime();
//		
//		cal.set(2016, 0, 30);
//		Date dp = cal.getTime();
		AppLogger.debug("TEST NUMERO "+ntest);
		List<RisultatoRicerca> rr = tp.getSoluzioniDisponibili(dataCI, dataCO, npersone);
		if (!rr.isEmpty()) {
			AppLogger.debug("Lista risultati ricerca non vuoto, dimensione:"+rr.size());
			for (RisultatoRicerca risultatoRicerca : rr) {
				AppLogger.debug("Elenco camere libere:");
				Set<Camera> sc = risultatoRicerca.getCamereLibere();
				for (Camera camera : sc) {
					AppLogger.debug("Camera id="+camera.getId()+", nome="+camera.getNomeCamera());
				}
			}
		} else {
			AppLogger.debug("Lista risultati ricerca VUOTA\n");
		}
		
	}
	
}