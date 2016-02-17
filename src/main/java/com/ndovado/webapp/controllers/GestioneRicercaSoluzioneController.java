package com.ndovado.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import com.ndovado.dominio.core.CatalogoLuogo;
import com.ndovado.dominio.core.CatalogoStrutture;
import com.ndovado.dominio.core.Luogo;
import com.ndovado.dominio.prenotazioni.RisultatoRicerca;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.mappers.RisultatoRicercaMapper;
import com.ndovado.webapp.beans.core.LuogoBean;
import com.ndovado.webapp.beans.prenotazioni.RisultatoRicercaBean;

public class GestioneRicercaSoluzioneController {

	private CatalogoLuogo clmodel = CatalogoLuogo.getInstance();
	private CatalogoStrutture csmodel = CatalogoStrutture.getInstance();
	private RisultatoRicercaMapper rrmapper = RisultatoRicercaMapper.getInstance();
	
	public GestioneRicercaSoluzioneController() {
		
	}
	
	public List<RisultatoRicercaBean> doGetSoluzioniDisponibili(LuogoBean lb, LocalDate dataArrivo, LocalDate dataPartenza, Integer nPersone) {
		AppLogger.debug("doGetSoluzioniDisponibili()");
		List<RisultatoRicercaBean> rrbeans = new ArrayList<RisultatoRicercaBean>();
		Luogo lmodel = clmodel.getLuogoById(lb.getId());
		List<RisultatoRicerca> rrmodels = csmodel.cercaSoluzioni(lmodel, dataArrivo, dataPartenza, nPersone);
		AppLogger.debug("rrmodels.size = "+rrmodels.size());
		for (RisultatoRicerca risultatoRicerca : rrmodels) {
			RisultatoRicercaBean rrbean = rrmapper.getBeanFromModel(risultatoRicerca);
			rrbeans.add(rrbean);
		}
		return rrbeans;
	}


}
