package com.ndovado.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import com.ndovado.dominio.core.Locatario;
import com.ndovado.dominio.core.Utente;
import com.ndovado.dominio.prenotazioni.Prenotazione;
import com.ndovado.tecservices.mappers.PrenotazioneMapper;
import com.ndovado.tecservices.mappers.UserMapper;
import com.ndovado.webapp.beans.core.LocatarioBean;
import com.ndovado.webapp.beans.core.UtenteBean;
import com.ndovado.webapp.beans.prenotazioni.PrenotazioneBean;

public class GestionePrenotazioneController {
	
	private PrenotazioneMapper pmapper = PrenotazioneMapper.getInstance();
	private UserMapper umapper = UserMapper.getInstance();
	
	public GestionePrenotazioneController() {
		// TODO Auto-generated constructor stub
	}
	
	public List<PrenotazioneBean> getElencoPrenotazioniBeansByLocatario(LocatarioBean lbean) {
		List<PrenotazioneBean> pbeans = new ArrayList<PrenotazioneBean>();
		UtenteBean ubean = lbean.getUtente();
		Utente umodel = umapper.getModelFromBean(ubean);
		Locatario lmodel = (Locatario) umodel.getRuolo();
		List<Prenotazione> pmodels = lmodel.getPrenotazioni();
		for (Prenotazione prenotazione : pmodels) {
			PrenotazioneBean pbean = pmapper.getBeanFromModel(prenotazione);
			pbeans.add(pbean);
		}
		return pbeans;
	} 

}
