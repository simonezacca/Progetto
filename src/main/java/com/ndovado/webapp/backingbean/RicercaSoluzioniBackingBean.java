package com.ndovado.webapp.backingbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.webapp.beans.core.LuogoBean;
import com.ndovado.webapp.beans.prenotazioni.RisultatoRicercaBean;
import com.ndovado.webapp.controllers.GestioneLuogoController;
import com.ndovado.webapp.controllers.GestioneRicercaSoluzioneController;

@ManagedBean(name="ricercaSoluzioniBB")
@ViewScoped
public class RicercaSoluzioniBackingBean {
	
	private GestioneLuogoController luogoController;
	private List<String> provinceDisponibili;
	private String codProvinciaCorrente;
	private List<LuogoBean> luoghiDisponibili;
	
	private LuogoBean luogoCorrente;
	private Date dataArrivo;
	private Date dataPartenza;
	private Integer numPersone = new Integer(1);
	
	private List<RisultatoRicercaBean> risultatiRicerca;
	private GestioneRicercaSoluzioneController ricercaController;
	
	
	
	
	
	public RicercaSoluzioniBackingBean() {
		luogoCorrente = new LuogoBean();
		risultatiRicerca = new ArrayList<RisultatoRicercaBean>();
		dataArrivo = new Date();
		dataPartenza = new Date();
		
		luogoController = new GestioneLuogoController();
		provinceDisponibili = luogoController.getListaTutteProvinceStrings();
		
		ricercaController = new GestioneRicercaSoluzioneController();
	}
	
	
	/**
	 * @return the luogoCorrente
	 */
	public LuogoBean getLuogoCorrente() {
		return luogoCorrente;
	}
	/**
	 * @param luogoCorrente the luogoCorrente to set
	 */
	public void setLuogoCorrente(LuogoBean luogoCorrente) {
		this.luogoCorrente = luogoCorrente;
	}
	/**
	 * @return the dataArrivo
	 */
	public Date getDataArrivo() {
		return dataArrivo;
	}
	/**
	 * @param dataArrivo the dataArrivo to set
	 */
	public void setDataArrivo(Date dataArrivo) {
		this.dataArrivo = dataArrivo;
	}
	/**
	 * @return the dataPartenza
	 */
	public Date getDataPartenza() {
		return dataPartenza;
	}
	/**
	 * @param dataPartenza the dataPartenza to set
	 */
	public void setDataPartenza(Date dataPartenza) {
		this.dataPartenza = dataPartenza;
	}
	/**
	 * @return the numPersone
	 */
	public Integer getNumPersone() {
		return numPersone;
	}
	/**
	 * @param numPersone the numPersone to set
	 */
	public void setNumPersone(Integer numPersone) {
		this.numPersone = numPersone;
	}
	/**
	 * @return the risultatiRicerca
	 */
	public List<RisultatoRicercaBean> getRisultatiRicerca() {
		return risultatiRicerca;
	}
	/**
	 * @param risultatiRicerca the risultatiRicerca to set
	 */
	public void setRisultatiRicerca(List<RisultatoRicercaBean> risultatiRicerca) {
		this.risultatiRicerca = risultatiRicerca;
	}


	/**
	 * @return the provinceDisponibili
	 */
	public List<String> getProvinceDisponibili() {
		return provinceDisponibili;
	}


	/**
	 * @param provinceDisponibili the provinceDisponibili to set
	 */
	public void setProvinceDisponibili(List<String> provinceDisponibili) {
		this.provinceDisponibili = provinceDisponibili;
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
	
	
	public String avviaRicercaSoluzioniDisponibili() {
		AppLogger.debug("avviaRicercaSoluzioniDisponibili()");
		risultatiRicerca = ricercaController.doGetSoluzioniDisponibili(luogoCorrente, dataArrivo, dataPartenza, numPersone);
		return null;
	}
	
	public void settaListaComuniBeans(final AjaxBehaviorEvent event) {
		luoghiDisponibili = luogoController.getListaLuogoBeanPerProvincia(codProvinciaCorrente);
	}

}
