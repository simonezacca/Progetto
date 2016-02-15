package com.ndovado.webapp.beans.prenotazioni;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.webapp.backingbean.RicercaSoluzioniBackingBean;
import com.ndovado.webapp.beans.core.CameraBean;
import com.ndovado.webapp.beans.core.LocatarioBean;
import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.core.UtenteBean;

@ManagedBean(name="carrelloPrenotazioneBean")
@SessionScoped
public class CarrelloPrenotazioneBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StrutturaBean strutturaCorrente;
	private PrenotazioneBean prenotazioneCorrente;
	
	@ManagedProperty(value="#{utenteBean}")
	private UtenteBean utenteCorrente;
	
	@ManagedProperty(value="#{ricercaSoluzioniBB}")
	private RicercaSoluzioniBackingBean ricercaSoluzioniBB;
	
	public CarrelloPrenotazioneBean() {
		// creo una nuova prenotazione bean
		prenotazioneCorrente = new PrenotazioneBean();
		
	}
	
	@PostConstruct
	private void postCost() {
		// estraggo il ruolo locatario dall'utente bean
		LocatarioBean lb = (LocatarioBean) utenteCorrente.getRuolo();
		// lego la prenotazione bean al locatario bean
		prenotazioneCorrente.setLocatario(lb);
		
		Date dataArrivo = ricercaSoluzioniBB.getDataArrivo();
		Date dataPartenza = ricercaSoluzioniBB.getDataPartenza();
			
		prenotazioneCorrente.setDataArrivo(dataArrivo);
		prenotazioneCorrente.setDataPartenza(dataPartenza);
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
	 * @return the prenotazioneCorrente
	 */
	public PrenotazioneBean getPrenotazioneCorrente() {
		return prenotazioneCorrente;
	}

	/**
	 * @param prenotazioneCorrente the prenotazioneCorrente to set
	 */
	public void setPrenotazioneCorrente(PrenotazioneBean prenotazioneCorrente) {
		this.prenotazioneCorrente = prenotazioneCorrente;
	}
	
	public void addOggettoPrenotabile(IPrenotabileBean op) {
		if (op!=null) {
			if (op instanceof CameraBean) {
				strutturaCorrente = ((CameraBean) op).getStruttura();
			}
			AppLogger.debug("Aggiungo oggetto prenotabile: "+op.getNomeOggettoPrenotabile());
			prenotazioneCorrente.addOggettoPrenotabile(op);
			prenotazioneCorrente.setStutturaAssociatata(strutturaCorrente);
		}
	}
	
	public void removeOggettoPrenotabile(IPrenotabileBean op) {
		if (op!=null) {
			AppLogger.debug("Rimuovo oggetto prenotabile: "+op.getNomeOggettoPrenotabile());
			prenotazioneCorrente.removeOggettoPrenotabile(op);
		}
	}
	
	public Boolean isPrenotazioneVuota() {
		return prenotazioneCorrente.getLineePrenotazione().isEmpty();
	}
	
	public Boolean isOggettoPrenotato(IPrenotabileBean op) {
		return prenotazioneCorrente.contieneOggettoPrenotabile(op);
	}
	
	public String svuotaCarrello() {
		prenotazioneCorrente = new PrenotazioneBean();
		strutturaCorrente = null;
		return "/ndovado/";
	}

	/**
	 * @return the ricercaSoluzioniBB
	 */
	public RicercaSoluzioniBackingBean getRicercaSoluzioniBB() {
		return ricercaSoluzioniBB;
	}

	/**
	 * @param ricercaSoluzioniBB the ricercaSoluzioniBB to set
	 */
	public void setRicercaSoluzioniBB(RicercaSoluzioniBackingBean ricercaSoluzioniBB) {
		this.ricercaSoluzioniBB = ricercaSoluzioniBB;
	}
	
}
