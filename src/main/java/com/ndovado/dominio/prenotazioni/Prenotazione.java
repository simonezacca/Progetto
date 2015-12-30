package com.ndovado.dominio.prenotazioni;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ndovado.dominio.core.Camera;
import com.ndovado.dominio.core.Locatario;
import com.ndovado.dominio.pagamenti.Pagamento;
import com.ndovado.dominio.prenotazioni.statiprenotazione.AStatoPrenotazione;
import com.ndovado.dominio.servizi.ServizioComune;
import com.ndovado.tecservices.persistenza.base.IPersistente;

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
	@GeneratedValue(strategy = IDENTITY)
	private Long idPrenotazione;

	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_ora_prenotazione")
	private Date dataOraPrenotazione;

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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_arrivo")
	private Date dataArrivo;

	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_partenza")
	private Date dataPartenza;


	/**
	 * 
	 */
	@OneToOne
	private AStatoPrenotazione statoPrenotazione;

	/**
	 * 
	 */
	@OneToMany(mappedBy="prenotazioneCorrente")
	private Set<LineaPrenotazione> lineePrenotazione;

	/**
	 * 
	 */
	@OneToMany(mappedBy = "prenotazioneSaldata")
	private Set<Pagamento> pagamentiAssociati;
	
	/**
	 * 
	 */
	@ManyToOne
	@PrimaryKeyJoinColumn
	private Locatario locatario;

	@SuppressWarnings("unused")
	private Prenotazione() {}

	/**
	 * Default constructor
	 */
	public Prenotazione(Locatario l) {
		if (l!=null) {
			this.locatario = l;
			l.addPrenotazione(this);
		}
		pagamentiAssociati = new HashSet<Pagamento>();
		lineePrenotazione = new HashSet<LineaPrenotazione>();
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
		}
	}

	/**
	 * @param aServizio
	 */
	public void addServizio(ServizioComune aServizio) {
		if (aServizio!=null) {
			LineaPrenotazione l = new LineaPrenotazione(this);
			l.addOggettoPrenotato((IPrenotabile) aServizio);
			lineePrenotazione.add(l);
		}
	}
	
	protected void addLineaPrenotazione(LineaPrenotazione l) {
		if (l!=null) {
			lineePrenotazione.add(l);
			l.setPrenotazione(this);
		}
	}

	/**
	 * 
	 */
	public Pagamento creaPagamento() {
		// instanzio un nuovo pagamento e lo associo alla prenotazione corrente
		Pagamento p = new Pagamento(this);
		// aggiungo il pagamento istanziato all'insieme dei pagamenti associati alla prenotazione corrente
		this.pagamentiAssociati.add(p);
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
		if (aStato!=null) {
			this.statoPrenotazione = aStato;
		}
	}

	/**
	 * @return
	 */
	public Date getDataArrivo() {
		return this.dataArrivo;
	}

	/**
	 * @param aDate
	 */
	public void setDataArrivo(Date aDate) {
		if (aDate!=null) {
			this.dataArrivo = aDate;
		}
	}

	/**
	 * @return
	 */
	public Date getDataPartenza() {
		return this.dataPartenza;
	}

	/**
	 * @param aDate
	 */
	public void setDataPartenza(Date aDate) {
		if (aDate!=null) {
			this.dataPartenza = aDate;
		}
	}

	/**
	 * @param da 
	 * @param dp 
	 * @return
	 */
	protected Boolean isDateSovrapposte(Date dataArrivo, Date dataPartenza) {
		return this.dataArrivo.before(dataPartenza) && dataArrivo.before(this.dataPartenza);
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
		result = prime * result + ((idPrenotazione == null) ? 0 : idPrenotazione.hashCode());
		result = prime * result + ((importoTotale == null) ? 0 : importoTotale.hashCode());
		result = prime * result + ((lineePrenotazione == null) ? 0 : lineePrenotazione.hashCode());
		result = prime * result + ((locatario == null) ? 0 : locatario.hashCode());
		result = prime * result + ((pagamentiAssociati == null) ? 0 : pagamentiAssociati.hashCode());
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
		if (idPrenotazione == null) {
			if (other.idPrenotazione != null)
				return false;
		} else if (!idPrenotazione.equals(other.idPrenotazione))
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
		if (locatario == null) {
			if (other.locatario != null)
				return false;
		} else if (!locatario.equals(other.locatario))
			return false;
		if (pagamentiAssociati == null) {
			if (other.pagamentiAssociati != null)
				return false;
		} else if (!pagamentiAssociati.equals(other.pagamentiAssociati))
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
		else if (this.dataArrivo.before(o.getDataArrivo())) {
			return -1;
		}
		else return 1;
	}

	@Override
	public Long getId() {
		return this.idPrenotazione;
	}
	
	protected void setId(Long idPrenotazione) {
		this.idPrenotazione = idPrenotazione;
	}

}