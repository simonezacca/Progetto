package com.ndovado.webapp.backingbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ndovado.tecservices.jsf.JSFHelper;
import com.ndovado.webapp.beans.prenotazioni.RisultatoRicercaBean;

@ManagedBean(name="confrontoStruttureBB")
@ViewScoped
public class ConfrontoStuttureBackingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<RisultatoRicercaBean> listaPerRicerca;
	
	@SuppressWarnings("unchecked")
	public ConfrontoStuttureBackingBean() {
		listaPerRicerca = (List<RisultatoRicercaBean>) JSFHelper.get("listaPerConfronto");
		if(listaPerRicerca==null)
			JSFHelper.redirectTo("/ricercaSoluzioni.xhtml");
	}

	/**
	 * @return the listaPerRicerca
	 */
	public List<RisultatoRicercaBean> getListaPerRicerca() {
		return listaPerRicerca;
	}

	/**
	 * @param listaPerRicerca the listaPerRicerca to set
	 */
	public void setListaPerRicerca(List<RisultatoRicercaBean> listaPerRicerca) {
		this.listaPerRicerca = listaPerRicerca;
	}
	
	public String selezionaStruttura(RisultatoRicercaBean rrbean) {
		JSFHelper.put("RRcorrente", rrbean);
		return "/visualizzaStruttura?faces-redirect=true";
	}

}
