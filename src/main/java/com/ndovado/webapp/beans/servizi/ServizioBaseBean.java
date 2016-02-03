package com.ndovado.webapp.beans.servizi;

public class ServizioBaseBean extends ATipologiaServizioBean {

	@SuppressWarnings("unused")
	private final Float prezzo = new Float(0);
	
	public ServizioBaseBean() {
		// TODO Auto-generated constructor stub
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
