package com.ndovado.webapp.backingbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.ndovado.exceptions.struttura.CancellazioneStrutturaException;
import com.ndovado.tecservices.jsf.JSFHelper;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.webapp.beans.core.GestoreBean;
import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.core.UtenteBean;
import com.ndovado.webapp.controllers.GestioneStrutturaController;

@ManagedBean(name="elencoStruttureBB")
@ViewScoped
public class ElencoStruttureBackingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value="#{utenteBean}")
	private UtenteBean utenteCorrente;
	private GestoreBean gestoreCorrente;
	private List<StrutturaBean> elencoStrutture;
	
	private GestioneStrutturaController controller;
			
	public ElencoStruttureBackingBean() {
		
	}
	
	@PostConstruct
	private void initInjection() {
		gestoreCorrente = (GestoreBean) utenteCorrente.getRuolo();
		AppLogger.debug("gestoreCorrente: "+gestoreCorrente);
		controller = new GestioneStrutturaController(gestoreCorrente);
		elencoStrutture = controller.getElencoStruttureByBean(utenteCorrente);
		AppLogger.debug("elencoStrutturaBB - elencoStrutture.size()="+elencoStrutture.size());
	}
	
	
	public String visualizzaStruttura(StrutturaBean sb) {
		return null;
		
	}
	
	public String modificaStruttura(StrutturaBean sb) {
		JSFHelper.put("strutturaDaModificare", sb);
		//FacesContext.getCurrentInstance().getExternalContext().getFlash().put("strutturaDaModificare", sb);
		
		return "modificaStruttura?faces-redirect=true";
		
	}
	
	public String eliminaStruttura(StrutturaBean sb) {
		try {
			controller.doRimuoviStruttura(sb);
			elencoStrutture = controller.getElencoStruttureByBean(utenteCorrente);
		} catch (CancellazioneStrutturaException e) {
			return "errore/erroreCancellazioneStruttura?faces-redirect=true";
		}
		return "successo/successoCancellazioneStruttura?faces-redirect=true";
		
	}

	/**
	 * @return the elencoStrutture
	 */
	public List<StrutturaBean> getElencoStrutture() {
		return elencoStrutture;
	}

	/**
	 * @return the utenteCorrente
	 */
	public UtenteBean getUtenteCorrente() {
		return utenteCorrente;
	}

	/**
	 * @param utenteCorrente the utenteCorrente to set
	 */
	public void setUtenteCorrente(UtenteBean utenteCorrente) {
		this.utenteCorrente = utenteCorrente;
	}

	/**
	 * @return the gestoreCorrente
	 */
	public GestoreBean getGestoreCorrente() {
		return gestoreCorrente;
	}

	/**
	 * @param gestoreCorrente the gestoreCorrente to set
	 */
	public void setGestoreCorrente(GestoreBean gestoreCorrente) {
		this.gestoreCorrente = gestoreCorrente;
	}

	
	
}
