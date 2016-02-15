package com.ndovado.webapp.backingbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.prenotazioni.RisultatoRicercaBean;

@ManagedBean(name="visualizzaStrutturaBB")
@ViewScoped
public class VisualizzaStrutturaBackingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RisultatoRicercaBean RRcorrente;
	
	private StrutturaBean strutturaCorrente;
	
	
	public VisualizzaStrutturaBackingBean() {
		
		RRcorrente = (RisultatoRicercaBean) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get("RRcorrente");
		if (RRcorrente!=null) {
			strutturaCorrente = RRcorrente.getStruttura();
		} else if (strutturaCorrente==null){
			strutturaCorrente = (StrutturaBean) FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.get("strutturaCorrente");
		}

	}
	
	
	/**
	 * @return the rRcorrente
	 */
	public RisultatoRicercaBean getRRcorrente() {
		return RRcorrente;
	}
	/**
	 * @param rRcorrente the rRcorrente to set
	 */
	public void setRRcorrente(RisultatoRicercaBean rRcorrente) {
		RRcorrente = rRcorrente;
	}


	/**
	 * @return the strutturaCorrente
	 */
	public StrutturaBean getStrutturaCorrente() {
		return strutturaCorrente;
	}


	/**
	 * @param strutturaCorrente the strutturaCorrente to set
	 */
	public void setStrutturaCorrente(StrutturaBean strutturaCorrente) {
		this.strutturaCorrente = strutturaCorrente;
	}

}
