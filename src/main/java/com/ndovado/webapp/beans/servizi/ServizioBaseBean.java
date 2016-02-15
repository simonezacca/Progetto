package com.ndovado.webapp.beans.servizi;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="servizioBaseBean")
public class ServizioBaseBean extends ATipologiaServizioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private final Float prezzo = new Float(0);
	
	public ServizioBaseBean() {
		
	}
	
	public ServizioBaseBean(ServizioComuneBean scb,DettaglioServizioBean dsb) {
		this.dettaglioServizio = dsb;
		this.dettaglioServizio.setServizio(scb);
		//this.servizio = scb;
	}

	@Override
	public String getStringLabel() {
		return "Servizio Base";
	}
}
