package com.ndovado.webapp.beans.servizi;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import com.ndovado.webapp.beans.core.StrutturaBean;

@ManagedBean(name="dettaglioServizioBean")
public class DettaglioServizioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id = null;
	private String note;
	private ServizioComuneBean servizio;
	private StrutturaBean struttura;
	private ATipologiaServizioBean tipologia;
	
	public DettaglioServizioBean() {
		
	}
	
	public DettaglioServizioBean(StrutturaBean sb, ServizioComuneBean scb) {
		this.struttura = sb;
		this.servizio = scb;
		this.tipologia = new ServizioBaseBean(servizio,this);
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the servizio
	 */
	public ServizioComuneBean getServizio() {
		return servizio;
	}
	/**
	 * @param servizio the servizio to set
	 */
	public void setServizio(ServizioComuneBean servizio) {
		this.servizio = servizio;
	}
	/**
	 * @return the struttura
	 */
	public StrutturaBean getStruttura() {
		return struttura;
	}
	/**
	 * @param struttura the struttura to set
	 */
	public void setStruttura(StrutturaBean struttura) {
		this.struttura = struttura;
	}
	/**
	 * @return the tipologia
	 */
	public ATipologiaServizioBean getTipologia() {
		return tipologia;
	}
	/**
	 * @param tipologia the tipologia to set
	 */
	public void setTipologia(ATipologiaServizioBean tipologia) {
		this.tipologia = tipologia;
	}
}
