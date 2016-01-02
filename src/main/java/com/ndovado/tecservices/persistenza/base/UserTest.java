package com.ndovado.tecservices.persistenza.base;

import java.util.List;

import com.ndovado.dominio.core.ARuolo;
import com.ndovado.dominio.core.Utente;
import com.ndovado.dominio.servizi.CatalogoServizi;
import com.ndovado.dominio.servizi.ServizioComune;
import com.ndovado.tecservices.loggers.EventLogger;

public class UserTest {

	public static void main(String[] args) {
		
		Utente u1 = new Utente("Schiazza","Antonio");
		u1.setMail("antonio.schiazza@gmail.com");
		u1.setRuolo(ARuolo.getRuoloLocatario());
		
		Utente u2 = new Utente("Zaccariello", "Simone");
		u2.setMail("simone.zaccariello@gmail.com");
		u2.setRuolo(ARuolo.getRuoloGestore()); 
		
		Utente u3 = new Utente("Cicchitto", "Giuseppe");
		u3.setMail("cicc@mail.com");
		
		ServizioPersistenzaBase.<Utente>create(u1);
		ServizioPersistenzaBase.<Utente>create(u2);
		ServizioPersistenzaBase.<Utente>create(u3);
		
		u1.setCognome("Orsini");
		ServizioPersistenzaBase.<Utente>update(u1);
		EventLogger.info("Nuovo cognome "+u1.getCognome());
		
		List<Utente> list = ServizioPersistenzaBase.<Utente>getAll(Utente.class);
		
		System.out.println("===== getAll ========");
		for (Utente u : list) {
			System.out.println(u.toString());
		}
		System.out.println("=================");
		
		ServizioPersistenzaBase.<Utente>delete(Utente.class, u3.getId());
		
		ServizioComune sc1 = CatalogoServizi.getIstance().creaNuovoServizio("prova1");
		ServizioComune sc2 = CatalogoServizi.getIstance().creaNuovoServizio("prova2");
		ServizioComune sc3 = CatalogoServizi.getIstance().creaNuovoServizio("prova3");
		
		
		List<ServizioComune> l = CatalogoServizi.getIstance().cercaServizioPerNome("prova");
		for (ServizioComune servizioComune : l) {
			System.out.println(servizioComune.getNomeServizio());
		}
		
	}

}
