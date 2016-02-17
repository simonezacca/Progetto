package com.ndovado.webapp.backingbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.joda.time.LocalDate;

import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.webapp.beans.core.CameraBean;
import com.ndovado.webapp.beans.core.GestoreBean;
import com.ndovado.webapp.beans.core.LuogoBean;
import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.servizi.ATipologiaServizioBean;
import com.ndovado.webapp.beans.servizi.DettaglioServizioBean;
import com.ndovado.webapp.beans.servizi.ServizioAggiuntivoBean;
import com.ndovado.webapp.beans.servizi.ServizioBaseBean;
import com.ndovado.webapp.beans.servizi.ServizioComuneBean;
import com.ndovado.webapp.controllers.GestioneLuogoController;
import com.ndovado.webapp.controllers.GestioneStrutturaController;

@ManagedBean(name = "StrutturaBB")
@ViewScoped
public class ModificaStrutturaBackingBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// collego il bean utente contenuto in sessione con il gestore corrente
	@ManagedProperty(value = "#{utenteBean.ruolo}")
	private GestoreBean gestoreCorrente;

	private GestioneStrutturaController controller;
	
	private GestioneLuogoController luogoController;
	

	private boolean addingStruttura = true;
	private boolean completed = false;

	private StrutturaBean strutturaCorrente;
	private CameraBean cameraInAggiunta;

	private List<ServizioComuneBean> serviziComuniDisponibili;
	private ServizioComuneBean sccorrente;
	private DettaglioServizioBean dsbcorrente;
	private Float prezzoDSB = new Float(0);

	public ModificaStrutturaBackingBean() {
		// initBB verrà invocato con il @PostConstruct
	}

	// serve per il corretto funzionamento dell'injecting del ruolo bean
	@PostConstruct
	protected void initBB() {
		luogoController = new GestioneLuogoController();
		LuogoBean luogoDefault = luogoController.getListaLuogoBeanPerProvincia("CH").get(0);
		
		
		// caso modifica
		strutturaCorrente = (StrutturaBean) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get("strutturaDaModificare");
		if (strutturaCorrente == null) {
			strutturaCorrente = new StrutturaBean(gestoreCorrente);
			strutturaCorrente.setLuogoStruttura(luogoDefault);
		}

		cameraInAggiunta = new CameraBean();

		controller = new GestioneStrutturaController(gestoreCorrente);
		
		serviziComuniDisponibili = controller.getElencoServizioComuneBeans();

		sccorrente = new ServizioComuneBean();
		dsbcorrente = new DettaglioServizioBean(strutturaCorrente, sccorrente);

		AppLogger.debug("Ruolo utente corrente " + gestoreCorrente);
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
		if (camera != null) {
			strutturaCorrente.getCamereInserite().remove(camera);
		}
		return null;
	}

	public String salvaStruttura() {
		if (strutturaCorrente != null) {
			// chiamo il controllore del caso d'uso per salvare la nuova
			// struttura
			controller.doSalvaStruttura(strutturaCorrente);
			// imposto il flag completed a true per visualizzare il riepilogo
			// delle informazioni
			completed = true;
			addingStruttura = false;
		}
		return "/gestore/index?faces-redirect=true";
	}

	/**
	 * @return the addingStruttura
	 */
	public boolean isAddingStruttura() {
		return addingStruttura;
	}

	/**
	 * @param addingStruttura
	 *            the addingStruttura to set
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
	 * @param completed
	 *            the completed to set
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	/**
	 * @return the gestoreCorrente
	 */
	public GestoreBean getGestoreCorrente() {
		return gestoreCorrente;
	}

	/**
	 * @param gestoreCorrente
	 *            the gestoreCorrente to set
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
	 * @param strutturaCorrente
	 *            the strutturaCorrente to set
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
	 * @param cameraInAggiunta
	 *            the cameraInAggiunta to set
	 */
	public void setCameraInAggiunta(CameraBean cameraInAggiunta) {
		this.cameraInAggiunta = cameraInAggiunta;
	}

	/**
	 * @return the serviziComuniDisponibili
	 */
	public List<ServizioComuneBean> getServiziComuniDisponibili() {
		return serviziComuniDisponibili;
	}

	/**
	 * @param serviziComuniDisponibili
	 *            the serviziComuniDisponibili to set
	 */
	public void setServiziComuniDisponibili(List<ServizioComuneBean> serviziComuniDisponibili) {
		this.serviziComuniDisponibili = serviziComuniDisponibili;
	}

	public String aggiungiDettaglioServizioBase() {
		// aggiorno il servizio collegato
		dsbcorrente.setServizio(sccorrente);
		// aggiungo il dettaglio servizio bean appena creato alla lista dei
		// servizi bean offerti dalla struttura bean
		strutturaCorrente.getServiziOfferti().add(dsbcorrente);
		// creo un nuovo servizio comune
		sccorrente = new ServizioComuneBean();
		// creo un nuovo dettaglio servizio bean
		dsbcorrente = new DettaglioServizioBean(strutturaCorrente, sccorrente);
		return null;
	}

	public String aggiungiDettaglioServizioPlus() {
		// creo una descrizione servizio bean e la associo con la struttura e il
		// servizio comune selezionato nel listmenu
		dsbcorrente.setServizio(sccorrente);
		// setto la tipologia di servizio
		ServizioAggiuntivoBean sab = new ServizioAggiuntivoBean(sccorrente, dsbcorrente);
		sab.setPrezzo(prezzoDSB);
		dsbcorrente.setTipologia(sab);
		// aggiungo il dettaglio servizio bean appena creato alla lista dei
		// servizi bean offerti dalla struttura bean
		strutturaCorrente.getServiziOfferti().add(dsbcorrente);

		// reset dsb
		// creo un nuovo servizio comune
		sccorrente = new ServizioComuneBean();
		// creo un nuovo dettaglio servizio bean
		dsbcorrente = new DettaglioServizioBean(strutturaCorrente, sccorrente);
		// azzera prezzo corrente
		prezzoDSB = new Float(0);
		return null;
	}

	public String eliminaDettaglioServizio(DettaglioServizioBean dsb) {
		if (dsb != null) {
			strutturaCorrente.getServiziOfferti().remove(dsb);
		}
		return null;
	}

	public String modificaDettaglioServizio(DettaglioServizioBean dsb) {
		if (dsb != null) {
			setDsbcorrente(dsb);
			strutturaCorrente.getServiziOfferti().remove(dsb);
		}
		return null;
	}

	/**
	 * @return the dsbcorrente
	 */
	public DettaglioServizioBean getDsbcorrente() {
		return dsbcorrente;
	}

	/**
	 * @param dsbcorrente
	 *            the dsbcorrente to set
	 */
	public void setDsbcorrente(DettaglioServizioBean dsbcorrente) {
		this.dsbcorrente = dsbcorrente;
	}

	/**
	 * @return the sccorrente
	 */
	public ServizioComuneBean getSccorrente() {
		return sccorrente;
	}

	/**
	 * @param sccorrente
	 *            the sccorrente to set
	 */
	public void setSccorrente(ServizioComuneBean sccorrente) {
		this.sccorrente = sccorrente;
	}

	public List<ATipologiaServizioBean> getTipologieSevizioBeanDisponibili() {
		List<ATipologiaServizioBean> lista = new ArrayList<ATipologiaServizioBean>();
		ServizioBaseBean baseBean = new ServizioBaseBean(sccorrente, dsbcorrente);
		ServizioAggiuntivoBean aggiuntivoBean = new ServizioAggiuntivoBean(sccorrente, dsbcorrente);
		lista.add(baseBean);
		lista.add(aggiuntivoBean);
		return lista;
	}

	/**
	 * @return the prezzoDSB
	 */
	public Float getPrezzoDSB() {
		return prezzoDSB;
	}

	/**
	 * @param prezzoDSB
	 *            the prezzoDSB to set
	 */
	public void setPrezzoDSB(Float prezzoDSB) {
		this.prezzoDSB = prezzoDSB;
	}

	
	public List<LuogoBean> completaLuoghiDisponibili(String query) {
		return luogoController.completaLuoghiDisponibili(query);
	}
	
	public void settaDataCamera() {
		LocalDate dataInizio = this.cameraInAggiunta.getDataInizioAffitto();
		this.cameraInAggiunta.setDataFineAffitto(dataInizio.plusDays(1));
	}
}
