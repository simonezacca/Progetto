package com.ndovado.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ndovado.dominio.core.CatalogoStrutture;
import com.ndovado.dominio.core.CatalogoUtenti;
import com.ndovado.dominio.core.Locatario;
import com.ndovado.dominio.core.Struttura;
import com.ndovado.dominio.core.Utente;
import com.ndovado.dominio.prenotazioni.Prenotazione;
import com.ndovado.tecservices.mappers.PrenotazioneMapper;
import com.ndovado.tecservices.mappers.StrutturaMapper;
import com.ndovado.tecservices.mappers.UserMapper;
import com.ndovado.tecservices.persistence.base.PrenotazioneDAO;
import com.ndovado.webapp.beans.core.LocatarioBean;
import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.core.UtenteBean;
import com.ndovado.webapp.beans.prenotazioni.PrenotazioneBean;

public class GestionePrenotazioneController {
	
	private CatalogoUtenti cumodel = CatalogoUtenti.getInstance();
	private CatalogoStrutture csmodel = CatalogoStrutture.getInstance();
	private PrenotazioneMapper pmapper = PrenotazioneMapper.getInstance();
	private UserMapper umapper = UserMapper.getInstance();
	private PrenotazioneDAO pdao = new PrenotazioneDAO();
	
	public GestionePrenotazioneController() {
		// TODO Auto-generated constructor stub
	}
	
	public List<PrenotazioneBean> getElencoPrenotazioniBeansByLocatario(LocatarioBean lbean) {
		List<PrenotazioneBean> pbeans = new ArrayList<PrenotazioneBean>();
		Long idLocatario = lbean.getId();
		
		Session session = pdao.getSessionFactory().openSession();
		Query q = session.createQuery("from Prenotazione as p where p.locatario.id = "+idLocatario);
		
		List<Prenotazione> pmodels = q.list();
		
//		UtenteBean ubean = lbean.getUtente();
//		//Utente umodel = umapper.getModelFromBean(ubean);
//		Utente umodel = cumodel.getUtenteById(ubean.getId());
		
//		List<Prenotazione> pmodels = ((Locatario)umodel.getRuolo()).getPrenotazioni();
		for (Prenotazione prenotazione : pmodels) {
			//Prenotazione newpmodel = pdao.get(prenotazione.getId());
			PrenotazioneBean pbean = pmapper.getBeanFromModel(prenotazione);
			pbeans.add(pbean);
		}
		
		session.close();
		
		return pbeans;
		
		
	}
	
	public PrenotazioneBean doSalvaPrenotazione(StrutturaBean sbean, PrenotazioneBean pbean) {
		
		Struttura smodel = csmodel.selezionaStruttura(sbean.getId());
		Prenotazione pmodel = pmapper.getModelFromBean(pbean);
		smodel.getTableau().salvaOAggiornaPrenotazione(pmodel);
		pbean = pmapper.getBeanFromModel(pmodel);
		
		return pbean;
		
	}

}
