package com.ndovado.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.mysql.fabric.xmlrpc.base.Array;
import com.ndovado.dominio.core.CatalogoStrutture;
import com.ndovado.dominio.core.CatalogoUtenti;
import com.ndovado.dominio.core.Struttura;
import com.ndovado.tecservices.mappers.StrutturaMapper;
import com.ndovado.tecservices.mappers.UserMapper;
import com.ndovado.webapp.beans.core.GestoreBean;
import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.core.UtenteBean;

public class GestioneStrutturaController {
	
	private StrutturaMapper smapeer = StrutturaMapper.getInstance();
	private CatalogoStrutture csmodel = CatalogoStrutture.getInstance();
	
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
			
		}
		return strutture;
	}
	
	public StrutturaBean doSalvaStruttura(StrutturaBean sb) {
		// converto il bean struttura in model struttura
		Struttura smodel = smapeer.getModelFromBean(sb);
		// persisto su DB il modello della struttura
		csmodel.salvaOAggiornaStruttura(smodel);
		// ripopolo il bean struttura con le informazioni del model gestito
		sb = smapeer.getBeanFromModel(smodel);
		// faccio tornare indietro il nuovo bean popolato
		return sb;
	}
	
}
