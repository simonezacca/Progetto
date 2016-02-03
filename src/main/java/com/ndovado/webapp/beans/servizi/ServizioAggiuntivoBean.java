package com.ndovado.webapp.beans.servizi;

public class ServizioAggiuntivoBean extends ATipologiaServizioBean {

	public ServizioAggiuntivoBean() {
		// TODO Auto-generated constructor stub
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
