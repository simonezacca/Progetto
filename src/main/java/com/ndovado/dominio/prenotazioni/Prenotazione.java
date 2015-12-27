package com.ndovado.dominio.prenotazioni;

import java.util.*;

import com.ndovado.dominio.core.Camera;
import com.ndovado.dominio.core.Locatario;
import com.ndovado.dominio.pagamenti.Pagamento;
import com.ndovado.dominio.prenotazioni.statiprenotazione.AStatoPrenotazione;
import com.ndovado.dominio.servizi.Servizio;

/**
 * Implementare i metodi equals() and hasCode()
 */
public class Prenotazione implements Comparable<Prenotazione> {

	/**
	 * Default constructor
	 */
	public Prenotazione() {
	}

	/**
	 * 
	 */
	private Integer idPrenotazione;

	/**
	 * 
	 */
	private Date dataOraPrenotazione;

	/**
	 * 
	 */
	private Float importoTotale;

	/**
	 * 
	 */
	private String codicePrenotazione;

	/**
	 * 
	 */
	private Date dataArrivo;

	/**
	 * 
	 */
	private Date dataPartenza;


	/**
	 * 
	 */
	private AStatoPrenotazione statoPrenotazione;

	/**
	 * 
	 */
	private Set<LineaPrenotazione> lineePrenotazione;

	/**
	 * 
	 */
	private Set<Pagamento> pagamentiAssociati;
	
	/**
	 * 
	 */
	private Locatario locatario;

	public void setLocatario(Locatario locatario) {
		this.locatario = locatario;
	}

	/**
	 * @param aCamera
	 */
	public void addCamera(Camera aCamera) {
		if (aCamera!=null) {
			LineaPrenotazione l = new LineaPrenotazione();
			l.addOggettoPrenotato((IPrenotabile) aCamera);
			lineePrenotazione.add(l);
		}
	}

	/**
	 * @param aServizio
	 */
	public void addServizio(Servizio aServizio) {
		if (aServizio!=null) {
			LineaPrenotazione l = new LineaPrenotazione();
			l.addOggettoPrenotato((IPrenotabile) aServizio);
			lineePrenotazione.add(l);
		}
	}

	/**
	 * 
	 */
	public void creaPagamento() {
		// TODO implement here
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

}