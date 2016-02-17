package com.ndovado.webapp.backingbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.joda.time.LocalDate;

import com.ndovado.tecservices.jsf.JSFHelper;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.webapp.beans.core.LuogoBean;
import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.prenotazioni.RisultatoRicercaBean;
import com.ndovado.webapp.controllers.GestioneLuogoController;
import com.ndovado.webapp.controllers.GestioneRicercaSoluzioneController;

@ManagedBean(name = "ricercaSoluzioniBB",eager=true)
@SessionScoped
public class RicercaSoluzioniBackingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GestioneLuogoController luogoController;
	private List<String> provinceDisponibili;
	private String codProvinciaCorrente;

	private LuogoBean luogoCorrente;
	private LocalDate dataArrivo;
	private LocalDate dataPartenza;
	private Integer numPersone = new Integer(1);

	private List<RisultatoRicercaBean> risultatiRicerca;
	private GestioneRicercaSoluzioneController ricercaController;
	
	private List<RisultatoRicercaBean> listaPerConfronto;

	private boolean primaRicerca = true;

	public RicercaSoluzioniBackingBean() {
		luogoCorrente = new LuogoBean();
		risultatiRicerca = new ArrayList<RisultatoRicercaBean>();
		dataArrivo = new LocalDate();
		dataPartenza = dataArrivo.plusDays(1);

		luogoController = new GestioneLuogoController();
		//provinceDisponibili = luogoController.getListaTutteProvinceStrings();

		ricercaController = new GestioneRicercaSoluzioneController();

		luogoCorrente = luogoController.getListaLuogoBeanPerProvincia("CH").get(0);
		
		listaPerConfronto = new ArrayList<RisultatoRicercaBean>();

	}

	/**
	 * @return the luogoCorrente
	 */
	public LuogoBean getLuogoCorrente() {
		return luogoCorrente;
	}

	/**
	 * @param luogoCorrente
	 *            the luogoCorrente to set
	 */
	public void setLuogoCorrente(LuogoBean luogoCorrente) {
		this.luogoCorrente = luogoCorrente;
	}

	/**
	 * @return the dataArrivo
	 */
	public LocalDate getDataArrivo() {
		return dataArrivo;
	}

	/**
	 * @param dataArrivo
	 *            the dataArrivo to set
	 */
	public void setDataArrivo(LocalDate dataArrivo) {
		this.dataArrivo = dataArrivo;
	}

	/**
	 * @return the dataPartenza
	 */
	public LocalDate getDataPartenza() {
		return dataPartenza;
	}

	/**
	 * @param dataPartenza
	 *            the dataPartenza to set
	 */
	public void setDataPartenza(LocalDate dataPartenza) {
		this.dataPartenza = dataPartenza;
	}

	/**
	 * @return the numPersone
	 */
	public Integer getNumPersone() {
		return numPersone;
	}

	/**
	 * @param numPersone
	 *            the numPersone to set
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
	 * @param risultatiRicerca
	 *            the risultatiRicerca to set
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
	 * @param provinceDisponibili
	 *            the provinceDisponibili to set
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
	 * @param codProvinciaCorrente
	 *            the codProvinciaCorrente to set
	 */
	public void setCodProvinciaCorrente(String codProvinciaCorrente) {
		this.codProvinciaCorrente = codProvinciaCorrente;
	}

	public List<LuogoBean> completaLuoghiDisponibili(String query) {
		return luogoController.completaLuoghiDisponibili(query);
	}

	public String avviaRicercaSoluzioniDisponibili() {
		this.primaRicerca = false;
		AppLogger.debug("avviaRicercaSoluzioniDisponibili()");
		risultatiRicerca = ricercaController.doGetSoluzioniDisponibili(luogoCorrente, dataArrivo, dataPartenza,
				numPersone);
		return null;
	}

	/**
	 * @return the primaRicerca
	 */
	public boolean isPrimaRicerca() {
		return primaRicerca;
	}

	/**
	 * @param primaRicerca
	 *            the primaRicerca to set
	 */
	public void setPrimaRicerca(boolean primaRicerca) {
		this.primaRicerca = primaRicerca;
	}

	public void settaDataPartenza() {
		this.dataPartenza = this.dataArrivo.plusDays(1);
	}
	
	public String visualizzaStruttura(StrutturaBean sb) {
		JSFHelper.put("strutturaCorrente", sb);
		return "visualizzaStruttura?faces-redirect=true";
	}
	
	public String visualizzaStrutturaDaRisultatoRicerca(RisultatoRicercaBean rr) {
		JSFHelper.put("RRcorrente", rr);
		return "visualizzaStruttura?faces-redirect=true";
	}
	
	public String addStrutturaPerConfronto(RisultatoRicercaBean rrbean) {
		// aggiungo il rrbean alla lista per il confronto
		this.listaPerConfronto.add(rrbean);
		if (this.listaPerConfronto.size()==2) {
			// aggiungo la lista al falsh context
			List<RisultatoRicercaBean> nuovaLista = new ArrayList<RisultatoRicercaBean>();
			nuovaLista.addAll(listaPerConfronto);
			JSFHelper.put("listaPerConfronto", nuovaLista);
			// azzero la lista nel backing bean
			this.listaPerConfronto.clear();
			// redirect a pagina confronto
			return "/locatario/confrontoStrutture.xhtml?faces-redirect=true";
			//JSFHelper.redirectTo("confrontoStrutture.xhtml");
		}
		return null;
	}
	
	public Boolean isStrutturaInConfronto(RisultatoRicercaBean rrbean) {
		return this.listaPerConfronto.contains(rrbean);
	}


}
