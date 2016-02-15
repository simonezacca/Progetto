package com.ndovado.dominio.servizi;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ndovado.dominio.core.Struttura;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistence.base.ServizioComuneDAO;
import com.ndovado.tecservices.persistence.base.StrutturaDAO;

@Entity
@Table(name = "dettaglio_servizio")
public class DettaglioServizio {
	
	@Override
	public String toString() {
		return "DettaglioServizio [id=" + id + ", struttura=" + struttura + ", servizio=" + servizio + ", note=" + note
				+ ", tipologia=" + tipologia + "]";
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Struttura struttura;
	
	@OneToOne
	private ServizioComune servizio;
	
	@Column(name = "note")
	private String note;
	
	
	@OneToOne(mappedBy="dettaglioServizio", cascade=CascadeType.ALL)
	private ATipologiaServizio tipologia;
	
	public DettaglioServizio() {
		AppLogger.debug("Istanzio nuovo: "+this.getClass().getName());
	}

	public DettaglioServizio(Struttura st, ServizioComune sc) {
		// imposto i riferimenti struttura e servzio della classe dettagli servizi
		this.setStruttura(st);
		this.setServizio(sc);
		
		//aggiungo all'elenco dei servizi offerti dalla struttura l'istanza del servizio
		ServizioBase sb = new ServizioBase(sc);
		sb.setDettaglioServizio(this);
		this.tipologia = sb;
		
	}
	
	public DettaglioServizio(Struttura st, ServizioComune sc,Float prezzo) {
		// imposto i riferimenti struttura e servzio della classe dettagli servizi
		this.setStruttura(st);
		this.setServizio(sc);
		
		//aggiungo all'elenco dei servizi offerti dalla struttura l'istanza del servizio
		ServizioAggiuntivo sa = new ServizioAggiuntivo(sc);
		sa.setDettaglioServizio(this);
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

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the struttura
	 */
	public Struttura getStruttura() {
		return struttura;
	}

	/**
	 * @param struttura the struttura to set
	 */
	public void setStruttura(Struttura struttura) {
		this.struttura = struttura;
	}

	/**
	 * @param servizio the servizio to set
	 */
	public void setServizio(ServizioComune servizio) {
		this.servizio = servizio;
	}
	
	
	public static void main(String[] args) {
		
		StrutturaDAO sdao = new StrutturaDAO();
		ServizioComuneDAO scdao = new ServizioComuneDAO();
		
		AppLogger.debug("Recupero struttura model con id 13");
		Struttura st = sdao.get(new Long(13));
		AppLogger.debug("Recupero servizio comune model con id 1");
		ServizioComune sc = scdao.get(new Long(1));
		
		AppLogger.debug("Creo dettaglio servzio");
		DettaglioServizio ds = new DettaglioServizio(st, sc);
		
		AppLogger.debug("Tipo servizio "+ds.getTipologia().toString());
				
		if (ds.getTipologia() instanceof ServizioBase) {
			AppLogger.debug("Classe ServizioBase con instanceof");
		}
		else if (ds.getTipologia() instanceof ServizioAggiuntivo) {
			AppLogger.debug("Classe ServizioBase con instanceof");
		}
		else AppLogger.debug("Classe non si sa");

		AppLogger.debug("Aggiungo dettaglio servizio alla struttura model");
		//st.addServizioAggiuntivo(sc, new Float(1));
		st.addDettaglioServizio(ds);
		
		
		DettaglioServizio ds2 = st.getServiziOfferti().get(0);
		AppLogger.debug("DettaglioServizio da get(0): "+ds2.toString());
		
		
		AppLogger.debug("Persisto la struttura model su DB");
		sdao.saveOrUpdate(st);
		
	}
	
}
