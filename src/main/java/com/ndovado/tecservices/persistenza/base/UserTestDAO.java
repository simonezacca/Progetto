package com.ndovado.tecservices.persistenza.base;

import java.util.List;

import com.ndovado.dominio.core.ARuolo;
import com.ndovado.dominio.core.Camera;
import com.ndovado.dominio.core.Luogo;
import com.ndovado.dominio.core.Struttura;
import com.ndovado.dominio.core.Utente;
import com.ndovado.dominio.servizi.CatalogoServizi;
import com.ndovado.dominio.servizi.ServizioComune;
import com.ndovado.helpers.core.UtenteHelper;
import com.ndovado.tecservices.loggers.AppLogger;

public class UserTestDAO {

	public static void main(String[] args) {
		
		Utente u1 = new Utente("Schiazza","Antonio");
		u1.setMail("antonio.schiazza@gmail.com");
		u1.setRuolo(ARuolo.getRuoloLocatario());
		
		Utente u2 = new Utente("Zaccariello", "Simone");
		u2.setMail("simone.zaccariello@gmail.com");
		u2.setRuolo(ARuolo.getRuoloGestore()); 
		
		Utente u3 = new Utente("Cicchitto", "Giuseppe");
		u3.setMail("cicc@mail.com");
		
		UtenteDAO ud = new UtenteDAO();
		ud.saveOrUpdate(u1);
		ud.saveOrUpdate(u2);
		ud.saveOrUpdate(u3);
		
		u1.setCognome("Orsini");
		ud.saveOrUpdate(u1);
		AppLogger.info("Nuovo cognome "+u1.getCognome());
		
		List<Utente> list = ud.getAll();
		
		System.out.println("===== getAll ========");
		for (Utente u : list) {
			System.out.println(u.toString());
		}
		System.out.println("=================");
		
		ud.delete(u3.getId());
		
		ServizioComune sc1 = CatalogoServizi.getInstance().aggiungiServizio("prova1");
		ServizioComune sc2 = CatalogoServizi.getInstance().aggiungiServizio("prova2");
		ServizioComune sc3 = CatalogoServizi.getInstance().aggiungiServizio("prova3");
		
		List<ServizioComune> l = CatalogoServizi.getInstance().cercaServizioPerNome("prova");
		for (ServizioComune servizioComune : l) {
			System.out.println(servizioComune.getNomeServizio());
		}
		
		LuogoDAO ldao = new LuogoDAO();
		Luogo l1 = new Luogo("Chieti");
		ldao.saveOrUpdate(l1);
		
		Struttura s1 = new Struttura();
		s1.setNomeStruttura("Struttura prova");
		s1.setLuogoStruttura(l1);
		
		//s1.setGestore(u2.getRuolo());
		
		Camera c1 = s1.creaNuovaCamera();
		c1.setNomeCamera("Camera prova");
		
		s1.addCamera(c1);
		
		StrutturaDAO sdao = new StrutturaDAO();
		sdao.saveOrUpdate(s1);
		
		sdao.delete(s1.getId());
		
		String mail = "antonio.schiazza@gmail.com";
		if(UtenteHelper.esisteIndirizzoMail(mail)) {
			Utente u = ud.cercaUtentePerMail(mail);
			System.out.println(u.getCognome()+" "+u.getNome());
			System.out.println("Indirizzo "+mail+" esistente!");
		} else {
			System.out.println("Indirizzo "+mail+" NON esistente!");
		}
		
	}

}
