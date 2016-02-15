package com.ndovado.webapp.beans.servizi;

import javax.faces.bean.ManagedBean;

import com.ndovado.webapp.beans.prenotazioni.IPrenotabileBean;

@ManagedBean(name="servizioAggiuntivoBean")
public class ServizioAggiuntivoBean extends ATipologiaServizioBean implements IPrenotabileBean {

	public ServizioAggiuntivoBean() {
		
	}
	
	public ServizioAggiuntivoBean(ServizioComuneBean scb, DettaglioServizioBean dsb) {
		this.dettaglioServizio = dsb;
		this.dettaglioServizio.setServizio(scb);
		//this.servizio = scb;
	}
	
	public String getNomeOggettoPrenotabile() {
		return this.dettaglioServizio.getServizio().getNomeServizio();
		//return this.servizio.getNomeServizio();
	}
	
	public void setPrezzo(Float aPrezzo) {
		this.prezzo = aPrezzo;
	}

	@Override
	public String getStringLabel() {
		return "Servizio aggiuntivo";
	}
	
}
