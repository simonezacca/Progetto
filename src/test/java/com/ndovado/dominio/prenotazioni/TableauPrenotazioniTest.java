package com.ndovado.dominio.prenotazioni;

import java.util.List;

import org.joda.time.LocalDate;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ndovado.dominio.core.Camera;
import com.ndovado.dominio.core.Gestore;
import com.ndovado.dominio.core.Locatario;
import com.ndovado.dominio.core.Luogo;
import com.ndovado.dominio.core.Struttura;
import com.ndovado.dominio.core.Utente;
import com.ndovado.tecservices.persistence.base.LuogoDAO;
import com.ndovado.tecservices.persistence.base.StrutturaDAO;
import com.ndovado.tecservices.persistence.base.UtenteDAO;

//import static
import static org.junit.Assert.*;

public class TableauPrenotazioniTest {
	
	private static LuogoDAO ldao = new LuogoDAO();
	private static UtenteDAO udao = new UtenteDAO();
	private static StrutturaDAO sdao = new StrutturaDAO();
	
	private static Struttura stest;
	private static Utente ulocatario;
	private static Utente ugestore;

	
	private static Utente initUtenteLocatario() {
		ulocatario = new Utente("testUser", "testUser");
		ulocatario.setRuolo(new Locatario());
		
		udao.saveOrUpdate(ulocatario);
		
		return ulocatario;
	}
	
	private static Prenotazione creaPrenotazione(Locatario l, LocalDate daData, LocalDate aData, Camera c) {
		Prenotazione p = new Prenotazione(l);
		p.setDataArrivo(daData);
		p.setDataPartenza(daData);
		p.addCamera(c);
		
		return p;
	} 
	
	private static Struttura initStruttura() {
		Luogo l = ldao.get(new Long(2));
		
		ugestore = new Utente("testGestore", "testGestore");
		ugestore.setMail("gestore");
		ugestore.setPassword("gestore");
		ugestore.setRuolo(new Gestore());
		
		udao.saveOrUpdate(ugestore);
		
		Struttura stest = new Struttura();
		stest.setNomeStruttura("Struttura test 1");
		stest.setGestore((Gestore) ugestore.getRuolo());
		stest.setLuogoStruttura(l);
		
		LocalDate dataInizio = new LocalDate("2016-01-01");
		LocalDate dataFine = new LocalDate("2016-12-31");
		
		
		Camera c1 = new Camera(stest);
		c1.setNomeCamera("Camera1");
		c1.setDataIntervalloAffitto(dataInizio, dataFine);
		c1.setPax(new Integer(3));
		c1.setPrezzo(new Float(30));
		
		stest.addCamera(c1);
		
		Camera c2 = new Camera(stest);
		c2.setNomeCamera("Camera2");
		c2.setDataIntervalloAffitto(dataInizio, dataFine);
		c2.setPax(new Integer(2));
		c2.setPrezzo(new Float(40));
		
		stest.addCamera(c2);
		
		sdao.saveOrUpdate(stest);
		
		List<Camera> listaCamere = stest.getCamereInserite();
		Camera c1test = new Camera(stest);
		Camera c2test = new Camera(stest);
		for (Camera camera : listaCamere) {
			if (camera.getNomeCamera().equals("Camera1")) {
				c1test = camera;
			} else if(camera.getNomeCamera().equals("Camera2")) {
				c2test = camera;
			}
		}
		
		ulocatario = initUtenteLocatario();
		
		Prenotazione p1 = creaPrenotazione((Locatario) ulocatario.getRuolo(), new LocalDate("2016-01-19"), new LocalDate("2016-01-21"), c1test);
		Prenotazione p2 = creaPrenotazione((Locatario) ulocatario.getRuolo(), new LocalDate("2016-01-20"), new LocalDate("2016-01-21"), c2test);
		Prenotazione p3 = creaPrenotazione((Locatario) ulocatario.getRuolo(), new LocalDate("2016-01-21"), new LocalDate("2016-01-24"), c2test);
		Prenotazione p4 = creaPrenotazione((Locatario) ulocatario.getRuolo(), new LocalDate("2016-01-24"), new LocalDate("2016-01-25"), c1test);
		Prenotazione p5 = creaPrenotazione((Locatario) ulocatario.getRuolo(), new LocalDate("2016-01-26"), new LocalDate("2016-01-28"), c2test);
		Prenotazione p6 = creaPrenotazione((Locatario) ulocatario.getRuolo(), new LocalDate("2016-01-27"), new LocalDate("2016-01-29"), c1test);
		
		stest.getTableau().salvaOAggiornaPrenotazione(p1);
		stest.getTableau().salvaOAggiornaPrenotazione(p2);
		stest.getTableau().salvaOAggiornaPrenotazione(p3);
		stest.getTableau().salvaOAggiornaPrenotazione(p4);
		stest.getTableau().salvaOAggiornaPrenotazione(p5);
		stest.getTableau().salvaOAggiornaPrenotazione(p6);
		
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
		LocalDate dataArrivo = new LocalDate("");
		LocalDate dataPartenza = new LocalDate("");
		Integer npersone = new Integer(3);
		
		// risultato atteso
		Integer dimRisultato = new Integer(1);
		
		// eseguo la ricerca disponibilità
		rr = stest.getTableau().getSoluzioniDisponibili(dataArrivo, dataPartenza, npersone);
		
		assertTrue(rr.getCamereLibere().size()==dimRisultato);
		
	}

//	public void doTestDisponibilita2() {
//	}
//
//	public void doTestDisponibilita3() {
//	}
//
//	public void doTestDisponibilita4() {
//	}
//
//	public void doTestDisponibilita5() {
//	}
//
//	public void doTestDisponibilita6() {
//	}
//
//	public void doTestDisponibilita7() {
//	}
//	

	@AfterClass
	public static void disposeDataSet() {
		// rimuovo la struttura
		sdao.delete(stest.getId());
		// rimuovo il gestore
		udao.delete(ugestore.getId());
		// rimuovo l'utente
		udao.delete(ugestore.getId());
	}
}
