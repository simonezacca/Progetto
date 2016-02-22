package com.ndovado.dominio.prenotazioni;

import org.joda.time.LocalDate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ndovado.dominio.core.Camera;
import com.ndovado.dominio.core.Gestore;
import com.ndovado.dominio.core.Locatario;
import com.ndovado.dominio.core.Struttura;
import com.ndovado.dominio.core.Utente;
import com.ndovado.exceptions.prenotazioni.OverbookingException;

//import static
import static org.junit.Assert.*;

public class TableauPrenotazioniTest {

	
	private static Struttura stest;
	private static Utente ulocatario;
	private static Utente ugestore;
	
	private static Camera cTest;

	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	private static Utente initUtenteLocatario() {
		ulocatario = new Utente("testUser", "testUser");
		ulocatario.setRuolo(new Locatario());

		
		return ulocatario;
	}
	
	private static Prenotazione creaPrenotazione(Locatario l, LocalDate daData, LocalDate aData, Camera c) {
		Prenotazione p = new Prenotazione(l);
		p.setDataArrivo(daData);
		p.setDataPartenza(aData);
		p.addCamera(c);
		
		return p;
	} 
	
	private static Struttura initStruttura() {
		
		
		ugestore = new Utente("testGestore", "testGestore");
		ugestore.setMail("gestore");
		ugestore.setPassword("gestore");
		ugestore.setRuolo(new Gestore());
		
		
		Struttura stest = new Struttura();
		
		// imposto il test per salvare le prenotazioni solo in RAM e non su Db
		stest.getTableau().setTest(true);
		
		
		stest.setNomeStruttura("Struttura test 1");
		stest.setGestore((Gestore) ugestore.getRuolo());
		
		LocalDate dataInizio = new LocalDate("2016-01-01");
		LocalDate dataFine = new LocalDate("2016-12-31");
		
		
		Camera c1 = new Camera(stest);
		c1.setNomeCamera("Camera1");
		c1.setDataIntervalloAffitto(dataInizio, dataFine);
		c1.setPax(new Integer(3));
		c1.setPrezzo(new Float(30));
		
		
		Camera c2 = new Camera(stest);
		c2.setNomeCamera("Camera2");
		c2.setDataIntervalloAffitto(dataInizio, dataFine);
		c2.setPax(new Integer(2));
		c2.setPrezzo(new Float(40));
		
		cTest = c1;
		
		
		
		ulocatario = initUtenteLocatario();
		
		Prenotazione p1 = creaPrenotazione((Locatario) ulocatario.getRuolo(), new LocalDate("2016-03-19"), new LocalDate("2016-03-21"), c1);
		Prenotazione p2 = creaPrenotazione((Locatario) ulocatario.getRuolo(), new LocalDate("2016-03-20"), new LocalDate("2016-03-21"), c2);
		Prenotazione p3 = creaPrenotazione((Locatario) ulocatario.getRuolo(), new LocalDate("2016-03-21"), new LocalDate("2016-03-24"), c2);
		Prenotazione p4 = creaPrenotazione((Locatario) ulocatario.getRuolo(), new LocalDate("2016-03-24"), new LocalDate("2016-03-25"), c1);
		Prenotazione p5 = creaPrenotazione((Locatario) ulocatario.getRuolo(), new LocalDate("2016-03-26"), new LocalDate("2016-03-28"), c2);
		Prenotazione p6 = creaPrenotazione((Locatario) ulocatario.getRuolo(), new LocalDate("2016-03-27"), new LocalDate("2016-03-29"), c1);
		
		try {
			stest.getTableau().salvaOAggiornaPrenotazione(p1);
			stest.getTableau().salvaOAggiornaPrenotazione(p2);
			stest.getTableau().salvaOAggiornaPrenotazione(p3);
			stest.getTableau().salvaOAggiornaPrenotazione(p4);
			stest.getTableau().salvaOAggiornaPrenotazione(p5);
			stest.getTableau().salvaOAggiornaPrenotazione(p6);
		} catch (OverbookingException e) {
			e.printStackTrace();
		}
		
		
		return stest;
	}
	
	
	
	@BeforeClass
	public static void initDataSet() {
		// creo una struttura e popolo il relativo tableau con un set di
		// prenotazioni
		stest = initStruttura();
	}

	@Test
	public void doTestDisponibilita1() {
		// precondizioni
		RisultatoRicerca rr;
		LocalDate dataArrivo = new LocalDate("2016-03-22");
		LocalDate dataPartenza = new LocalDate("2016-03-23");
		Integer npersone = new Integer(3);
		
		// risultato atteso
		Integer dimRisultato = new Integer(1);
		
		// eseguo la ricerca disponibilità
		rr = stest.getTableau().getSoluzioniDisponibili(dataArrivo, dataPartenza, npersone);
		
		assertTrue(rr.getCamereLibere().size()==dimRisultato);
		
	}

	@Test
	public void doTestDisponibilita2() {
		// precondizioni
		RisultatoRicerca rr;
		LocalDate dataArrivo = new LocalDate("2016-03-22");
		LocalDate dataPartenza = new LocalDate("2016-03-23");
		Integer npersone = new Integer(2);
		
		// risultato atteso
		Integer dimRisultato = new Integer(1);
		
		// eseguo la ricerca disponibilità
		rr = stest.getTableau().getSoluzioniDisponibili(dataArrivo, dataPartenza, npersone);
		
		assertTrue(rr.getCamereLibere().size()==dimRisultato);
	}

	@Test
	public void doTestDisponibilita3() {
		// precondizioni
		RisultatoRicerca rr;
		LocalDate dataArrivo = new LocalDate("2016-03-22");
		LocalDate dataPartenza = new LocalDate("2016-03-23");
		Integer npersone = new Integer(4);
		
		// risultato atteso
		Integer dimRisultato = new Integer(0);
		
		// eseguo la ricerca disponibilità
		rr = stest.getTableau().getSoluzioniDisponibili(dataArrivo, dataPartenza, npersone);
		
		assertTrue(rr.getCamereLibere().size()==dimRisultato);
	}

	@Test
	public void doTestDisponibilita4() {
		// precondizioni
		RisultatoRicerca rr;
		LocalDate dataArrivo = new LocalDate("2016-04-01");
		LocalDate dataPartenza = new LocalDate("2016-04-04");
		Integer npersone = new Integer(5);
		
		// risultato atteso
		Integer dimRisultato = new Integer(2);
		
		// eseguo la ricerca disponibilità
		rr = stest.getTableau().getSoluzioniDisponibili(dataArrivo, dataPartenza, npersone);
		
		assertTrue(rr.getCamereLibere().size()==dimRisultato);
	}

	@Test
	public void doTestDisponibilita5() {
		// precondizioni
		RisultatoRicerca rr;
		LocalDate dataArrivo = new LocalDate("2016-04-01");
		LocalDate dataPartenza = new LocalDate("2016-04-04");
		Integer npersone = new Integer(3);
		
		// risultato atteso
		Integer dimRisultato = new Integer(2);
		
		// eseguo la ricerca disponibilità
		rr = stest.getTableau().getSoluzioniDisponibili(dataArrivo, dataPartenza, npersone);
		
		assertTrue(rr.getCamereLibere().size()==dimRisultato);
	}

	@Test
	public void doTestDisponibilita6() {
		// precondizioni
		RisultatoRicerca rr;
		LocalDate dataArrivo = new LocalDate("2016-04-01");
		LocalDate dataPartenza = new LocalDate("2016-04-04");
		Integer npersone = new Integer(2);
		
		// risultato atteso
		Integer dimRisultato = new Integer(2);
		
		// eseguo la ricerca disponibilità
		rr = stest.getTableau().getSoluzioniDisponibili(dataArrivo, dataPartenza, npersone);
		
		assertTrue(rr.getCamereLibere().size()==dimRisultato);
	}

	@Test
	public void doTestDisponibilita7() {
		// precondizioni
		RisultatoRicerca rr;
		LocalDate dataArrivo = new LocalDate("2016-03-28");
		LocalDate dataPartenza = new LocalDate("2016-04-02");
		Integer npersone = new Integer(5);
		
		// risultato atteso
		Integer dimRisultato = new Integer(0);
		
		// eseguo la ricerca disponibilità
		rr = stest.getTableau().getSoluzioniDisponibili(dataArrivo, dataPartenza, npersone);
		
		assertTrue(rr.getCamereLibere().size()==dimRisultato);
	}
	
	@Test(expected=OverbookingException.class)
	public void doTestDisponibilitaOverBooking() throws OverbookingException {
		
		Prenotazione pTest = creaPrenotazione((Locatario) ulocatario.getRuolo(), new LocalDate("2016-03-19"), new LocalDate("2016-03-21"), cTest);
		
		stest.getTableau().salvaOAggiornaPrenotazione(pTest);

	}
	

	@AfterClass
	public static void disposeDataSet() {
		
	}
}
