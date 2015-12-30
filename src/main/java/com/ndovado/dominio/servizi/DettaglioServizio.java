package com.ndovado.dominio.servizi;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ndovado.dominio.core.Struttura;

@Entity
@Table(name = "dettaglio_servizio")
public class DettaglioServizio {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long iddettaglio_servizio;
	
	@OneToOne
	private Struttura struttura;
	
	@OneToOne
	private ServizioComune servizio;
	
	@Column(name = "note")
	private String note;
	
	
	@OneToOne(mappedBy="servizio")
	private ATipologiaServizio tipologia;
	
	protected DettaglioServizio() {
	}

	public DettaglioServizio(Struttura st, ServizioComune sc) {
		// imposto i riferimenti struttura e servzio della classe dettagli servizi
		this.struttura = st;
		this.servizio = sc;
		
		//aggiungo all'elenco dei servizi offerti dalla struttura l'istanza del servizio
		ServizioBase sb = new ServizioBase(sc);
		this.tipologia = sb;
		
	}
	
	public DettaglioServizio(Struttura st, ServizioComune sc,Float prezzo) {
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
