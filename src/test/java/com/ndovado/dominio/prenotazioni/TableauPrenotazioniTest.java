package com.ndovado.dominio.prenotazioni;

//import static
import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ndovado.dominio.core.Camera;
import com.ndovado.dominio.core.Locatario;
import com.ndovado.dominio.core.Struttura;
import com.ndovado.dominio.core.Utente;

public class TableauPrenotazioniTest {

	
	private static Utente initUtente() {
		Utente utest = new Utente("testUser", "testUser");
		utest.setRuolo(new Locatario());
		return utest;
	}
	
	private static Struttura initStruttura() {
		Struttura stest = new Struttura();
		
		LocalDate dataInizio = new LocalDate("2016-01-01");
		LocalDate dataFine = new LocalDate("2016-12-31");
		
		
		Camera c1 = new Camera(stest);
		c1.setNomeCamera("Camera1");
		c1.setDataIntervalloAffitto(dataInizio, dataFine);
		c1.setPax(new Integer(3));
		c1.setPrezzo(new Float(30));
		
		Camera c2 = new Camera(stest);
		c2.setNomeCamera("Camera2");
		c2.setDataIntervalloAffitto(aDataInizioAffitto, aDataFineAffitto);
		c2.setPax(aPax);
		c2.setPrezzo(aPrezzo);
		
				
		return stest;
	}
	
	private Prenotazione creaPrenotazione(Locatario l, )
	
	
	@BeforeClass
	public static void initDataSet() {
		// creo una struttura e popolo il relativo tableau con un set di
		// prenotazioni
		Utente utest = initUtente();
		Struttura stest = initStruttura();
		
		
		

	}

	
	public void doTestDisponibilita() {

	}
	

	@AfterClass
	public static void disposeDataSet() {

	}
}
