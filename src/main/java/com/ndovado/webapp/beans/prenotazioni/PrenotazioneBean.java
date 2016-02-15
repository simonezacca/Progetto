package com.ndovado.webapp.beans.prenotazioni;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import com.ndovado.webapp.beans.core.CameraBean;
import com.ndovado.webapp.beans.core.LocatarioBean;
import com.ndovado.webapp.beans.core.StrutturaBean;
import com.ndovado.webapp.beans.pagamenti.PagamentoBean;
import com.ndovado.webapp.beans.prenotazioni.statiprenotazione.AStatoPrenotazioneBean;
import com.ndovado.webapp.beans.prenotazioni.statiprenotazione.StatoPendenteBean;
import com.ndovado.webapp.beans.servizi.ServizioAggiuntivoBean;

@ManagedBean(name="prenotazioneBean")
public class PrenotazioneBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codicePrenotazione;
	private Date dataArrivo;
	private Date dataOraPrenotazione;
	private Date dataPartenza;
	private Long id = null;
	private Float importoTotale;
	private List<LineaPrenotazioneBean> lineePrenotazione;
	@Override
	public String toString() {
		return "PrenotazioneBean [codicePrenotazione=" + codicePrenotazione + ", dataArrivo=" + dataArrivo
				+ ", dataOraPrenotazione=" + dataOraPrenotazione + ", dataPartenza=" + dataPartenza + ", id=" + id
				+ ", importoTotale=" + importoTotale + ", lineePrenotazione=" + lineePrenotazione + ", locatario="
				+ locatario + ", notePrenotazione=" + notePrenotazione + ", pagamentiAssociati=" + pagamentoAssociato
				+ ", statoPrenotazione=" + statoPrenotazione + "]";
	}

	private LocatarioBean locatario;
	private String notePrenotazione;
	private PagamentoBean pagamentoAssociato;
	private AStatoPrenotazioneBean statoPrenotazione;
	
	private Boolean cancellabile = true;
	private Integer nottiSoggiorno;
	
	private StrutturaBean stutturaAssociatata;
	
	
	public PrenotazioneBean() {
		dataArrivo = new Date();
		dataPartenza = new Date();
		dataOraPrenotazione = new Date();
		lineePrenotazione = new ArrayList<LineaPrenotazioneBean>();
		//pagamentoAssociato = new PagamentoBean();
		importoTotale = ricalcolaImportoTotale();
		statoPrenotazione = new StatoPendenteBean();
	}
	
	private Float ricalcolaImportoTotale() {
	
		LocalDate dataArrivoJ = new  LocalDate(dataArrivo.getTime());
		LocalDate dataPartenzaJ = new LocalDate(dataPartenza.getTime());
		Period diff = new Period(dataArrivoJ, dataPartenzaJ);
		Integer numNotti = diff.getDays();
		this.nottiSoggiorno = numNotti;
		Float newTotale = new Float(0);
		for (LineaPrenotazioneBean lpb : lineePrenotazione) {
			IPrenotabileBean oggettoPrenotato = lpb.getOggettoPrenotato();
			Float prezzoOggettoPrenotato = oggettoPrenotato.getPrezzo();
			
			if (oggettoPrenotato instanceof CameraBean) {
				// moltiplico il prezzo per notte per il numero di notti
				prezzoOggettoPrenotato *= numNotti;
			}
			newTotale += prezzoOggettoPrenotato;
		}
		return newTotale;
	}
	
	public void addOggettoPrenotabile(IPrenotabileBean op) {
		LineaPrenotazioneBean lpb = new LineaPrenotazioneBean();
		lpb.setOggettoPrenotato(op);
		lpb.setPrenotazioneCorrente(this);
		
		lineePrenotazione.add(lpb);
		importoTotale = ricalcolaImportoTotale();
	}
	
	public void removeOggettoPrenotabile(IPrenotabileBean op) {
		Iterator<LineaPrenotazioneBean> i = lineePrenotazione.iterator();
		while (i.hasNext()) {
			LineaPrenotazioneBean lpb = (LineaPrenotazioneBean) i.next();
			IPrenotabileBean opCorrente = lpb.getOggettoPrenotato();
			if (op.equals(opCorrente)) {
				// rimuovo la linea prenotazione dalla lista delle linee prenotazioni
				i.remove();
			}
		}
		importoTotale = ricalcolaImportoTotale();
	}
	
	
	/**
	 * @return the codicePrenotazione
	 */
	public String getCodicePrenotazione() {
		return codicePrenotazione;
	}
	/**
	 * @param codicePrenotazione the codicePrenotazione to set
	 */
	public void setCodicePrenotazione(String codicePrenotazione) {
		this.codicePrenotazione = codicePrenotazione;
	}
	/**
	 * @return the dataArrivo
	 */
	public Date getDataArrivo() {
		return dataArrivo;
	}
	/**
	 * @param dataArrivo the dataArrivo to set
	 */
	public void setDataArrivo(Date dataArrivo) {
		this.dataArrivo = dataArrivo;
	}
	/**
	 * @return the dataOraPrenotazione
	 */
	public Date getDataOraPrenotazione() {
		return dataOraPrenotazione;
	}
	/**
	 * @param dataOraPrenotazione the dataOraPrenotazione to set
	 */
	public void setDataOraPrenotazione(Date dataOraPrenotazione) {
		this.dataOraPrenotazione = dataOraPrenotazione;
	}
	/**
	 * @return the dataPartenza
	 */
	public Date getDataPartenza() {
		return dataPartenza;
	}
	/**
	 * @param dataPartenza the dataPartenza to set
	 */
	public void setDataPartenza(Date dataPartenza) {
		this.dataPartenza = dataPartenza;
	}
	/**
	 * @return the idPrenotazione
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param idPrenotazione the idPrenotazione to set
	 */
	public void setId(Long idPrenotazione) {
		this.id = idPrenotazione;
	}
	/**
	 * @return the importoTotale
	 */
	public Float getImportoTotale() {
		return importoTotale;
	}
	/**
	 * @param importoTotale the importoTotale to set
	 */
	public void setImportoTotale(Float importoTotale) {
		this.importoTotale = importoTotale;
	}
	/**
	 * @return the lineePrenotazione
	 */
	public List<LineaPrenotazioneBean> getLineePrenotazione() {
		return lineePrenotazione;
	}
	/**
	 * @param lineePrenotazione the lineePrenotazione to set
	 */
	public void setLineePrenotazione(List<LineaPrenotazioneBean> lineePrenotazione) {
		this.lineePrenotazione = lineePrenotazione;
	}
	/**
	 * @return the locatario
	 */
	public LocatarioBean getLocatario() {
		return locatario;
	}
	/**
	 * @param locatario the locatario to set
	 */
	public void setLocatario(LocatarioBean locatario) {
		this.locatario = locatario;
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
	 * @return the pagamentiAssociati
	 */
	public PagamentoBean getPagamentoAssociato() {
		return pagamentoAssociato;
	}
	/**
	 * @param pagamentiAssociati the pagamentiAssociati to set
	 */
	public void setPagamentoAssociato(PagamentoBean pagamentoAssociato) {
		this.pagamentoAssociato = pagamentoAssociato;
		pagamentoAssociato.setPrenotazioneSaldata(this);
	}
	/**
	 * @return the statoPrenotazione
	 */
	public AStatoPrenotazioneBean getStatoPrenotazione() {
		return statoPrenotazione;
	}
	/**
	 * @param statoPrenotazione the statoPrenotazione to set
	 */
	public void setStatoPrenotazione(AStatoPrenotazioneBean statoPrenotazione) {
		this.statoPrenotazione = statoPrenotazione;
	}
	
	public Boolean contieneOggettoPrenotabile(IPrenotabileBean op) {
		Iterator<LineaPrenotazioneBean> i = lineePrenotazione.iterator();
		while (i.hasNext()) {
			LineaPrenotazioneBean lpb = (LineaPrenotazioneBean) i.next();
			IPrenotabileBean opCorrente = lpb.getOggettoPrenotato();
			if (op.equals(opCorrente)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the cancellabile
	 */
	public Boolean getCancellabile() {
		return cancellabile;
	}
	
	public List<CameraBean> getCamereInPrenotazione() {
		List<CameraBean> camere = new ArrayList<CameraBean>();
		for (LineaPrenotazioneBean lineaPrenotazioneBean : lineePrenotazione) {
			IPrenotabileBean oggettoPrenotato = lineaPrenotazioneBean.getOggettoPrenotato();
			if (oggettoPrenotato instanceof CameraBean) {
				camere.add((CameraBean) oggettoPrenotato);
			}
		}
		return camere;
	}
	
	public List<ServizioAggiuntivoBean> getServiziAggiuntiviInPrenotazione() {
		List<ServizioAggiuntivoBean> servizi = new ArrayList<ServizioAggiuntivoBean>();
		for (LineaPrenotazioneBean lineaPrenotazioneBean : lineePrenotazione) {
			IPrenotabileBean oggettoPrenotato = lineaPrenotazioneBean.getOggettoPrenotato();
			if (oggettoPrenotato instanceof ServizioAggiuntivoBean) {
				servizi.add((ServizioAggiuntivoBean) oggettoPrenotato);
			}
		}
		return servizi;
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
	public StrutturaBean getStutturaAssociatata() {
		return stutturaAssociatata;
	}

	/**
	 * @param stutturaAssociatata the stutturaAssociatata to set
	 */
	public void setStutturaAssociatata(StrutturaBean stutturaAssociatata) {
		this.stutturaAssociatata = stutturaAssociatata;
	}


}
