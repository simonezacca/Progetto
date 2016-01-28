package com.ndovado.webapp.backingbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.webapp.beans.core.ARuoloBean;
import com.ndovado.webapp.beans.core.CameraBean;
import com.ndovado.webapp.beans.core.GestoreBean;
import com.ndovado.webapp.beans.core.LuogoBean;
import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.core.UtenteBean;
import com.ndovado.webapp.controllers.GestioneStrutturaController;
 
@ManagedBean(name="StrutturaBB")
@ViewScoped
public class InserimentoStrutturaBackingBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codProvinciaCorrente;
	
	// collego il bean utente contenuto in sessione  con il gestore corrente
	@ManagedProperty(value="#{utenteBean.ruolo}")
	private GestoreBean gestoreCorrente;
	
	private boolean addingStruttura = true;
	
	private boolean completed = false;
	
	private StrutturaBean strutturaCorrente;
	
	private CameraBean cameraInAggiunta;
	
	private List<String> provinceDisponibili;
	
	private List<LuogoBean> luoghiDisponibili;
	
	private GestioneStrutturaController controller;
	
	public InserimentoStrutturaBackingBean() {
		
	}
	
	@PostConstruct
	protected void initBB() {
		strutturaCorrente = new StrutturaBean(gestoreCorrente);
		cameraInAggiunta = new CameraBean();
		luoghiDisponibili = new ArrayList<LuogoBean>();
		controller = new GestioneStrutturaController(gestoreCorrente);
		provinceDisponibili = controller.getListaTutteProvinceStrings();
		
		AppLogger.debug("Ruolo utente corrente "+gestoreCorrente);
	}
	
	public boolean isStrutturaSalvabile() {
		return !getStrutturaCorrente().getCamereInserite().isEmpty();
	}
	
	public String aggiungiCamera() {		
		strutturaCorrente.addCameraBean(cameraInAggiunta);
		cameraInAggiunta = new CameraBean();
		return null;
	}
	
	public String modificaCamera(CameraBean camera) {
		cameraInAggiunta = camera;
		strutturaCorrente.getCamereInserite().remove(camera);
		return null;
	}
 
	public String eliminaCamera(CameraBean camera) {
		if (camera!=null) {
			strutturaCorrente.getCamereInserite().remove(camera);
		}
		return null;
	}
    
	public String salvaStruttura() {
		if (strutturaCorrente!=null) {
			// chiamo il controllore del caso d'uso per salvare la nuova struttura
			controller.doSalvaStruttura(strutturaCorrente);
			// imposto il flag completed a true per visualizzare il riepilogo delle informazioni
			completed = true;
			addingStruttura = false;
		}
		return "/locatario/index?faces-redirect=true";
	}

	/**
	 * @return the addingStruttura
	 */
	public boolean isAddingStruttura() {
		return addingStruttura;
	}

	/**
	 * @param addingStruttura the addingStruttura to set
	 */
	public void setAddingStruttura(boolean addingStruttura) {
		this.addingStruttura = addingStruttura;
	}

	/**
	 * @return the completed
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	/**
	 * @return the luoghiDisponibili
	 */
	public List<LuogoBean> getLuoghiDisponibili() {
		return luoghiDisponibili;
	}

	/**
	 * @param luoghiDisponibili the luoghiDisponibili to set
	 */
	public void setLuoghiDisponibili(List<LuogoBean> luoghiDisponibili) {
		this.luoghiDisponibili = luoghiDisponibili;
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

	/**
	 * @return the cameraInAggiunta
	 */
	public CameraBean getCameraInAggiunta() {
		return cameraInAggiunta;
	}

	/**
	 * @param cameraInAggiunta the cameraInAggiunta to set
	 */
	public void setCameraInAggiunta(CameraBean cameraInAggiunta) {
		this.cameraInAggiunta = cameraInAggiunta;
	}

	/**
	 * @return the codProvinciaCorrente
	 */
	public String getCodProvinciaCorrente() {
		return codProvinciaCorrente;
	}

	/**
	 * @param codProvinciaCorrente the codProvinciaCorrente to set
	 */
	public void setCodProvinciaCorrente(String codProvinciaCorrente) {
		this.codProvinciaCorrente = codProvinciaCorrente;
	}
	
	public void settaListaComuniBeans(final AjaxBehaviorEvent event) {
		luoghiDisponibili = controller.getListaLuogoBeanPerProvincia(codProvinciaCorrente);
	}

	/**
	 * @return the provinceDisponibili
	 */
	public List<String> getProvinceDisponibili() {
		return provinceDisponibili;
	}

}
