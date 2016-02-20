package com.ndovado.dominio.prenotazioni;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import com.ndovado.dominio.core.Camera;
import com.ndovado.dominio.core.Locatario;
import com.ndovado.dominio.core.Struttura;
import com.ndovado.dominio.pagamenti.Pagamento;
import com.ndovado.dominio.prenotazioni.statiprenotazione.AStatoPrenotazione;
import com.ndovado.dominio.servizi.ServizioAggiuntivo;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistence.base.IPersistente;

/**
 * Implementare i metodi equals() and hasCode()
 */
@Entity
@Table(name = "prenotazione")
public class Prenotazione implements Comparable<Prenotazione>, IPersistente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "data_ora_prenotazione")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime dataOraPrenotazione;
	/**
	 * 
	 */
	@Column(name = "importo")
	private Float importoTotale;

	/**
	 * 
	 */
	@Column(name = "codice_prenotazione")
	private String codicePrenotazione;

	/**
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "data_arrivo")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dataArrivo;

	/**
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "data_partenza")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dataPartenza;


	/**
	 * 
	 */
	@OneToOne(mappedBy="prenotazione",cascade=CascadeType.ALL,fetch=FetchType.EAGER, orphanRemoval=true)
	@PrimaryKeyJoinColumn
	private AStatoPrenotazione statoPrenotazione;

	/**
	 * 
	 */
	@OneToMany(mappedBy="prenotazioneCorrente",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	//@OrderColumn
	@OrderBy("id ASC")
	private List<LineaPrenotazione> lineePrenotazione;

	/**
	 * 
	 */
	@OneToOne(mappedBy="prenotazioneSaldata",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private Pagamento pagamentoAssociato;
	
	/**
	 * 
	 */
	@ManyToOne
	@PrimaryKeyJoinColumn
	private Locatario locatario;
	
	@Column(name="note")
	private String notePrenotazione;
	
	@Column(name="nottiSoggiorno")
	private Integer nottiSoggiorno;
	
	@ManyToOne
	@PrimaryKeyJoinColumn
	private Struttura stutturaAssociatata;

	@Transient
	private Boolean cancellabile;
	
	public Prenotazione() {
		AppLogger.debug("Istanzio nuovo: "+this.getClass().getName());
	}

	/**
	 * Default constructor
	 */
	public Prenotazione(Locatario l) {
		if (l!=null) {
			this.locatario = l;
			l.addPrenotazione(this);
		}
		//pagamentoAssociato = new Pagamento();
		lineePrenotazione = new ArrayList<LineaPrenotazione>();
	}

	public void setLocatario(Locatario locatario) {
		this.locatario = locatario;
	}

	/**
	 * @param aCamera
	 */
	public void addCamera(Camera aCamera) {
		if (aCamera!=null) {
			LineaPrenotazione l = new LineaPrenotazione(this);
			l.addOggettoPrenotato((IPrenotabile) aCamera);
			lineePrenotazione.add(l);
			ricalcolaTotalePrenotazione();
		}
	}

	/**
	 * @param aServizio
	 */
	public void addServizioAggiuntivo(ServizioAggiuntivo aServizioAgg) {
		if (aServizioAgg!=null) {
			LineaPrenotazione l = new LineaPrenotazione(this);
			l.addOggettoPrenotato(aServizioAgg);
			lineePrenotazione.add(l);
			ricalcolaTotalePrenotazione();
		}
	}
	
	public void removeCamera(Camera aCamera) {
		
	}
	
	public void removeServizioAggiuntivo(ServizioAggiuntivo aServizioAgg) {
		
	}
	
	protected Float ricalcolaTotalePrenotazione() {
		Float localImporto = new Float(0);
		for (LineaPrenotazione lineaPrenotazione : lineePrenotazione) {
			localImporto+= lineaPrenotazione.getOggettoPrenotato().getPrezzo();
		}
		importoTotale = localImporto;
		return localImporto;
	}
	
	protected void addLineaPrenotazione(LineaPrenotazione l) {
		if (l!=null) {
			lineePrenotazione.add(l);
			l.setPrenotazioneCorrente(this);
		}
	}

	/**
	 * 
	 */
	public Pagamento creaPagamento() {
		// instanzio un nuovo pagamento e lo associo alla prenotazione corrente
		Pagamento p = new Pagamento(this);
		// aggiungo il pagamento istanziato all'insieme dei pagamenti associati alla prenotazione corrente
		this.pagamentoAssociato = p;
		return p;
	}
	

	/**
	 * @return
	 */
	public Float getImportoTotale() {
		return this.importoTotale;
	}

	/**
	 * @return
	 */
	public Locatario getLocatario() {
		return this.locatario;
	}

	/**
	 * @return
	 */
	public String getCodicePrenotazione() {
		return this.codicePrenotazione;
	}

	/**
	 * @return
	 */
	public AStatoPrenotazione getStatoPrenotazione() {
		return this.statoPrenotazione;
	}

	/**
	 * @param aStato
	 */
	public void setStatoPrenotazione(AStatoPrenotazione aStato) {
//		if (aStato!=null) {
			this.statoPrenotazione = aStato;
//		}
	}

	/**
	 * @return
	 */
	public LocalDate getDataArrivo() {
		return this.dataArrivo;
	}

	/**
	 * @param aDate
	 */
	public void setDataArrivo(LocalDate aDate) {
		if (aDate!=null) {
			this.dataArrivo = aDate;
		}
	}

	/**
	 * @return
	 */
	public LocalDate getDataPartenza() {
		return this.dataPartenza;
	}

	/**
	 * @param aDate
	 */
	public void setDataPartenza(LocalDate aDate) {
		if (aDate!=null) {
			this.dataPartenza = aDate;
		}
	}

	/**
	 * @param da 
	 * @param dp 
	 * @return
	 */
	protected Boolean isDateSovrapposte(LocalDate dataArrivo, LocalDate dataPartenza) {
		return this.dataArrivo.isBefore(dataPartenza) && dataArrivo.isBefore(this.dataPartenza);
//		/return this.dataArrivo.before(dataPartenza) && dataArrivo.before(this.dataPartenza);
	}

	/**
	 * @return
	 */
	protected Comparator<Prenotazione> comparatoreDataArrivo() {
		// TODO implement here
		return null;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codicePrenotazione == null) ? 0 : codicePrenotazione.hashCode());
		result = prime * result + ((dataArrivo == null) ? 0 : dataArrivo.hashCode());
		result = prime * result + ((dataOraPrenotazione == null) ? 0 : dataOraPrenotazione.hashCode());
		result = prime * result + ((dataPartenza == null) ? 0 : dataPartenza.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((importoTotale == null) ? 0 : importoTotale.hashCode());
		result = prime * result + ((lineePrenotazione == null) ? 0 : lineePrenotazione.hashCode());
		result = prime * result + ((notePrenotazione == null) ? 0 : notePrenotazione.hashCode());
		result = prime * result + ((pagamentoAssociato == null) ? 0 : pagamentoAssociato.hashCode());
		result = prime * result + ((statoPrenotazione == null) ? 0 : statoPrenotazione.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Prenotazione))
			return false;
		Prenotazione other = (Prenotazione) obj;
		if (codicePrenotazione == null) {
			if (other.codicePrenotazione != null)
				return false;
		} else if (!codicePrenotazione.equals(other.codicePrenotazione))
			return false;
		if (dataArrivo == null) {
			if (other.dataArrivo != null)
				return false;
		} else if (!dataArrivo.equals(other.dataArrivo))
			return false;
		if (dataOraPrenotazione == null) {
			if (other.dataOraPrenotazione != null)
				return false;
		} else if (!dataOraPrenotazione.equals(other.dataOraPrenotazione))
			return false;
		if (dataPartenza == null) {
			if (other.dataPartenza != null)
				return false;
		} else if (!dataPartenza.equals(other.dataPartenza))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (importoTotale == null) {
			if (other.importoTotale != null)
				return false;
		} else if (!importoTotale.equals(other.importoTotale))
			return false;
		if (lineePrenotazione == null) {
			if (other.lineePrenotazione != null)
				return false;
		} else if (!lineePrenotazione.equals(other.lineePrenotazione))
			return false;
		if (notePrenotazione == null) {
			if (other.notePrenotazione != null)
				return false;
		} else if (!notePrenotazione.equals(other.notePrenotazione))
			return false;
		if (pagamentoAssociato == null) {
			if (other.pagamentoAssociato != null)
				return false;
		} else if (!pagamentoAssociato.equals(other.pagamentoAssociato))
			return false;
		if (statoPrenotazione == null) {
			if (other.statoPrenotazione != null)
				return false;
		} else if (!statoPrenotazione.equals(other.statoPrenotazione))
			return false;
		return true;
	}

	public int compareTo(Prenotazione o) {
		if (this.dataArrivo.equals(o.getDataArrivo())) {
			return 0;
		}
		else if (this.dataArrivo.isBefore(o.getDataArrivo())) {
			return -1;
		}
		else return 1;
	}

	@Override
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long idPrenotazione) {
		this.id = idPrenotazione;
	}

	/**
	 * @return the notePrenotazione
	 */
	public String getNotePrenotazione() {
		return notePrenotazione;
	}

	/**
	 * @param notePrenotazione the notePrenotazione to set
	 */
	public void setNotePrenotazione(String notePrenotazione) {
		this.notePrenotazione = notePrenotazione;
	}

	/**
	 * @return the pagamentoAssociato
	 */
	public Pagamento getPagamentoAssociato() {
		return pagamentoAssociato;
	}

	/**
	 * @param pagamentoAssociato the pagamentoAssociato to set
	 */
	public void setPagamentoAssociato(Pagamento pagamentoAssociato) {
		this.pagamentoAssociato = pagamentoAssociato;
	}

	/**
	 * @return the lineePrenotazione
	 */
	public List<LineaPrenotazione> getLineePrenotazione() {
		return lineePrenotazione;
	}

	/**
	 * @param lineePrenotazione the lineePrenotazione to set
	 */
	public void setLineePrenotazione(List<LineaPrenotazione> lineePrenotazione) {
		this.lineePrenotazione = lineePrenotazione;
	}

	/**
	 * @return the dataOraPrenotazione
	 */
	public LocalDateTime getDataOraPrenotazione() {
		return dataOraPrenotazione;
	}

	/**
	 * @param dataOraPrenotazione the dataOraPrenotazione to set
	 */
	public void setDataOraPrenotazione(LocalDateTime dataOraPrenotazione) {
		this.dataOraPrenotazione = dataOraPrenotazione;
	}

	/**
	 * @param importoTotale the importoTotale to set
	 */
	public void setImportoTotale(Float importoTotale) {
		this.importoTotale = importoTotale;
	}

	@Override
	public String toString() {
		return "Prenotazione [id=" + id + ", dataOraPrenotazione=" + dataOraPrenotazione + ", importoTotale="
				+ importoTotale + ", codicePrenotazione=" + codicePrenotazione + ", dataArrivo=" + dataArrivo
				+ ", dataPartenza=" + dataPartenza
				+ ", notePrenotazione=" + notePrenotazione + "]";
	}

	/**
	 * @return the nottiSoggiorno
	 */
	public Integer getNottiSoggiorno() {
		return nottiSoggiorno;
	}

	/**
	 * @param nottiSoggiorno the nottiSoggiorno to set
	 */
	public void setNottiSoggiorno(Integer nottiSoggiorno) {
		this.nottiSoggiorno = nottiSoggiorno;
	}

	/**
	 * @return the stutturaAssociatata
	 */
	public Struttura getStutturaAssociatata() {
		return stutturaAssociatata;
	}

	/**
	 * @param stutturaAssociatata the stutturaAssociatata to set
	 */
	public void setStutturaAssociatata(Struttura stutturaAssociatata) {
		this.stutturaAssociatata = stutturaAssociatata;
	}

	public Boolean isCancellabile() {
		Integer giorniPerCancellazione = stutturaAssociatata.getGiorniPerCancellazione();
		LocalDate oggi = new LocalDate();
		
		Integer numGiorni = Days.daysBetween(oggi, this.dataArrivo).getDays();
		this.cancellabile = numGiorni>=giorniPerCancellazione;
		
		return  this.cancellabile;
	} 
	
	public void setCancellabile(Boolean cancellabile) {
		this.cancellabile = cancellabile;
	}

	

}