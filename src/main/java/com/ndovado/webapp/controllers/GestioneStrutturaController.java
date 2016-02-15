package com.ndovado.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import com.ndovado.dominio.core.CatalogoStrutture;
import com.ndovado.dominio.core.Gestore;
import com.ndovado.dominio.core.Struttura;
import com.ndovado.dominio.servizi.CatalogoServizi;
import com.ndovado.dominio.servizi.ServizioComune;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.mappers.ServizioComuneMapper;
import com.ndovado.tecservices.mappers.StrutturaMapper;
import com.ndovado.tecservices.mappers.UserMapper;
import com.ndovado.webapp.beans.core.GestoreBean;
import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.core.UtenteBean;
import com.ndovado.webapp.beans.servizi.ServizioComuneBean;

public class GestioneStrutturaController {
	
	private StrutturaMapper smapper = StrutturaMapper.getInstance();
	private CatalogoStrutture csmodel = CatalogoStrutture.getInstance();
	private CatalogoServizi cscmodel = CatalogoServizi.getInstance();
	private ServizioComuneMapper scmapper = ServizioComuneMapper.getInstance();
	
	
	private UserMapper umapper = UserMapper.getInstance();
	
	private GestoreBean gestore;
	
	
	public GestioneStrutturaController(GestoreBean gestore) {
		this.setGestore(gestore);
	}

	/**
	 * @return the gestore
	 */
	public GestoreBean getGestore() {
		return gestore;
	}

	/**
	 * @param gestore the gestore to set
	 */
	public void setGestore(GestoreBean gestore) {
		this.gestore = gestore;
	}
	
	public List<StrutturaBean> getElencoStruttureByBean(UtenteBean g) {
		List<StrutturaBean> strutture = new ArrayList<StrutturaBean>();
		if (g.getRuolo() instanceof GestoreBean) {
			
			
			Gestore gmodel = (Gestore) umapper.getModelFromBean(g).getRuolo();
			AppLogger.debug("Recupero gestore model da utente bean: "+gmodel);
					
			List<Struttura> smodels = csmodel.getElencoStruttureByGestore(gmodel);
			AppLogger.debug("Recupero elenco strutture models da gestore model");
			for (Struttura struttura : smodels) {
				AppLogger.debug("Converto struttura model in struttura bean");
				StrutturaBean sbean = smapper.getBeanFromModel(struttura);
				strutture.add(sbean);
			}
		}
		return strutture;
	}
	
	public StrutturaBean doSalvaStruttura(StrutturaBean sb) {
		// converto il bean struttura in model struttura
		Struttura smodel = smapper.getModelFromBean(sb);
		// persisto su DB il modello della struttura
		csmodel.salvaOAggiornaStruttura(smodel);
		// ripopolo il bean struttura con le informazioni del model gestito
		sb = smapper.getBeanFromModel(smodel);
		// faccio tornare indietro il nuovo bean popolato
		return sb;
	}
	
	public void doRimuoviStruttura(StrutturaBean sb) {
		Struttura smodel = smapper.getModelFromBean(sb);
		csmodel.rimuoviStruttura(smodel);
	}
	
	public List<ServizioComuneBean> getElencoServizioComuneBeans() {
		List<ServizioComune> smodels = cscmodel.getServiziDisponibili();
		List<ServizioComuneBean> sbeans = new ArrayList<ServizioComuneBean>();
		for (ServizioComune servizioComune : smodels) {
			ServizioComuneBean sb = scmapper.getBeanFromModel(servizioComune);
			sbeans.add(sb);
		}
		return sbeans;
	}
	
}
