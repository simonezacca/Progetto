package com.ndovado.dominio.servizi;

import com.ndovado.dominio.core.Struttura;

public class DettagliServizioOfferto {

	@SuppressWarnings("unused")
	private Struttura struttura;
	private ServizioComune servizio;
	
	private String note;
	
	private ATipologiaServizio tipologia;
	
	public DettagliServizioOfferto(Struttura st, ServizioComune sc) {
		// imposto i riferimenti struttura e servzio della classe dettagli servizi
		this.struttura = st;
		this.servizio = sc;
		
		//aggiungo all'elenco dei servizi offerti dalla struttura l'istanza del servizio
		ServizioBase sb = new ServizioBase(sc);
		this.tipologia = sb;
		
	}
	
	public DettagliServizioOfferto(Struttura st, ServizioComune sc,Float prezzo) {
		// imposto i riferimenti struttura e servzio della classe dettagli servizi
		this.struttura = st;
		this.servizio = sc;
		
		//aggiungo all'elenco dei servizi offerti dalla struttura l'istanza del servizio
		ServizioAggiuntivo sa = new ServizioAggiuntivo(sc);
		this.tipologia = sa;
		sa.setPrezzo(prezzo);
	}
	
	public ServizioComune getServizio() {
		return this.servizio;
	}
	/**
	 * @return the tipologia
	 */
	public ATipologiaServizio getTipologia() {
		return tipologia;
	}

	/**
	 * @param tipologia the tipologia to set
	 */
	public void setTipologia(ATipologiaServizio tipologia) {
		this.tipologia = tipologia;
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
	
	public Float getPrezzo() {
		return this.tipologia.getPrezzo();
	}
	
}
