package com.ndovado.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.mysql.fabric.xmlrpc.base.Array;
import com.ndovado.helpers.core.TipoUtente;
import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.core.UtenteBean;

@ManagedBean(name="strutturaController")
public class GestioneStrutturaController {

	private UtenteBean gestore;
	
	public GestioneStrutturaController(UtenteBean gestore) {
		this.setGestore(gestore);
	}

	/**
	 * @return the gestore
	 */
	public UtenteBean getGestore() {
		return gestore;
	}

	/**
	 * @param gestore the gestore to set
	 */
	public void setGestore(UtenteBean gestore) {
		this.gestore = gestore;
	}
	
	public List<StrutturaBean> getElencoStruttureByBean(UtenteBean g) {
		List<StrutturaBean> strutture = new ArrayList<StrutturaBean>();
		if (g.getRuolo() == TipoUtente.GESTORE) {
			
		}
		return strutture;
	}
	
}
