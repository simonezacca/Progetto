package com.ndovado.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import com.ndovado.dominio.core.CatalogoStrutture;
import com.ndovado.dominio.core.Struttura;
import com.ndovado.dominio.prenotazioni.Prenotazione;
import com.ndovado.tecservices.mappers.PrenotazioneMapper;
import com.ndovado.tecservices.persistence.base.PrenotazioneDAO;
import com.ndovado.webapp.beans.core.LocatarioBean;
import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.prenotazioni.PrenotazioneBean;

public class GestionePrenotazioneController {
	
	private CatalogoStrutture csmodel = CatalogoStrutture.getInstance();
	private PrenotazioneMapper pmapper = PrenotazioneMapper.getInstance();
	private PrenotazioneDAO pdao = new PrenotazioneDAO();
	
	
	public GestionePrenotazioneController() {
		
	}
	
	public List<PrenotazioneBean> getElencoPrenotazioniBeansByLocatario(LocatarioBean lbean) {
		List<PrenotazioneBean> pbeans = new ArrayList<PrenotazioneBean>();
		Long idLocatario = lbean.getId();
		
		@SuppressWarnings("static-access")
		Session session = pdao.getSessionFactory().openSession();
		
		Query q = session.createQuery("from Prenotazione as p where p.locatario.id = "+idLocatario);
		
		@SuppressWarnings("unchecked")
		List<Prenotazione> pmodels = q.list();
		for (Prenotazione prenotazione : pmodels) {
			PrenotazioneBean pbean = pmapper.getBeanFromModel(prenotazione);
			
			// settare il flag per la cancellazione
			pbeans.add(pbean);
		}
		session.close();
		return pbeans;
		
		
	}
	
	public PrenotazioneBean doSalvaPrenotazione(StrutturaBean sbean, PrenotazioneBean pbean) {
		
		Struttura smodel = csmodel.selezionaStruttura(sbean.getId());
		Prenotazione pmodel = pmapper.getModelFromBean(pbean);
		pmodel = smodel.getTableau().salvaOAggiornaPrenotazione(pmodel);
		pbean = pmapper.getBeanFromModel(pmodel);
		return pbean;
	}
	
	public void doCancellaPrenotazione(PrenotazioneBean pbean) {
		Prenotazione pmodel = pmapper.getModelFromBean(pbean);
		Struttura smodel = pmodel.getStutturaAssociatata();
		
		smodel.getTableau().cancellaPrenotazione(pmodel);
	}
	
	public PrenotazioneBean doAggiornaPrenotazione(PrenotazioneBean pbean) {
		Prenotazione pmodel = pmapper.getModelFromBean(pbean);
		pdao.saveOrUpdate(pmodel);
		pbean = pmapper.getBeanFromModel(pmodel);
		return pbean;
	}

}
