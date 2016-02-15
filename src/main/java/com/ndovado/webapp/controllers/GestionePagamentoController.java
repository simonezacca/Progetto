package com.ndovado.webapp.controllers;

import com.ndovado.dominio.pagamenti.GWAutorizzazionePagamenti;
import com.ndovado.dominio.pagamenti.Pagamento;
import com.ndovado.dominio.prenotazioni.Prenotazione;
import com.ndovado.dominio.prenotazioni.statiprenotazione.StatoConfermata;
import com.ndovado.tecservices.mappers.PagamentoMapper;
import com.ndovado.tecservices.persistence.base.PagamentoDAO;
import com.ndovado.tecservices.persistence.base.PrenotazioneDAO;
import com.ndovado.webapp.beans.pagamenti.PagamentoBean;

public class GestionePagamentoController {

	GWAutorizzazionePagamenti authGW = GWAutorizzazionePagamenti.getIstance();
	
	private PagamentoDAO pgdao = new PagamentoDAO();
	private PrenotazioneDAO pdao = new PrenotazioneDAO();

	private PagamentoMapper pmapper = PagamentoMapper.getInstance();

	public GestionePagamentoController() {
		
	}

	public PagamentoBean doSalvaPagamento(PagamentoBean pbean) {
		

		Pagamento pmodel = pmapper.getModelFromBean(pbean);
		
		pmodel = authGW.creaRichiestaAuthPagamento(pmodel);
		if (pmodel.isAutorizzato()) {
			// pagamento autorizzato
			// salvo su db
			pgdao.create(pmodel);
			//pgdao.save(pmodel);
	
			
			// aggiorno lo stato della prenotazione
			Prenotazione pzmodel = pdao.get(pmodel.getPrenotazioneSaldata().getId());
			pzmodel.setStatoPrenotazione(new StatoConfermata());
			pdao.saveOrUpdate(pzmodel);
			
			pbean = pmapper.getBeanFromModel(pmodel);
		} else {
			Prenotazione pzmodel = pdao.get(pmodel.getPrenotazioneSaldata().getId());
			pzmodel.setPagamentoAssociato(null);
			pdao.saveOrUpdate(pzmodel);
			
			pbean = pmapper.getBeanFromModel(pmodel);
		}
		
		
		return pbean;
	}
	
}
