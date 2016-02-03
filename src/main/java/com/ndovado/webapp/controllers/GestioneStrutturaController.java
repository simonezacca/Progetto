package com.ndovado.webapp.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ndovado.dominio.core.CatalogoLuogo;
import com.ndovado.dominio.core.CatalogoStrutture;
import com.ndovado.dominio.core.Luogo;
import com.ndovado.dominio.core.Struttura;
import com.ndovado.dominio.servizi.CatalogoServizi;
import com.ndovado.dominio.servizi.ServizioComune;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.mappers.LuogoMapper;
import com.ndovado.tecservices.mappers.ServizioComuneMapper;
import com.ndovado.tecservices.mappers.StrutturaMapper;
import com.ndovado.webapp.beans.core.GestoreBean;
import com.ndovado.webapp.beans.core.LuogoBean;
import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.core.UtenteBean;
import com.ndovado.webapp.beans.servizi.ServizioComuneBean;

public class GestioneStrutturaController {
	
	private StrutturaMapper smapper = StrutturaMapper.getInstance();
	private CatalogoStrutture csmodel = CatalogoStrutture.getInstance();
	private CatalogoServizi cscmodel = CatalogoServizi.getInstance();
	private ServizioComuneMapper scmapper = ServizioComuneMapper.getInstance();
	private LuogoMapper lmapper;
	private CatalogoLuogo clmodel; 
	
	private GestoreBean gestore;
	
	private void initLuogoUtils() {
		lmapper = LuogoMapper.getInstance();
		clmodel = CatalogoLuogo.getInstance();
	}
	
	
	public GestioneStrutturaController(GestoreBean gestore) {
		this.setGestore(gestore);
		initLuogoUtils();
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
	
	public List<LuogoBean> getListaLuogoBeanPerProvincia(String codProvincia) {
		List<LuogoBean> lbeans = new ArrayList<LuogoBean>();
		if (codProvincia!=null && !codProvincia.isEmpty() && codProvincia.length()==2) {
			AppLogger.debug("Popolo lista luoghi bean da codProvincia= "+codProvincia);
			List<Luogo> lmodels = clmodel.getListaLuoghiPerProvincia(codProvincia);
			for (Luogo luogo : lmodels) {
				LuogoBean lb = lmapper.getBeanFromModel(luogo);
				lbeans.add(lb);
			}
		}
		return lbeans;
	}
	
	public List<String> getListaTutteProvinceStrings() {
		List<String> strings = clmodel.getListaTutteProvinceStrings();
		Collections.sort(strings);
		return strings;
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
