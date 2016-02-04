package com.ndovado.webapp.beans.prenotazioni;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.ndovado.webapp.beans.core.LocatarioBean;
import com.ndovado.webapp.beans.pagamenti.PagamentoBean;
import com.ndovado.webapp.beans.prenotazioni.statiprenotazione.AStatoPrenotazioneBean;

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
	private Long idPrenotazione;
	private Float importoTotale;
	private List<LineaPrenotazioneBean> lineePrenotazione;
	private LocatarioBean locatario;
	private String notePrenotazione;
	private List<PagamentoBean> pagamentiAssociati;
	private AStatoPrenotazioneBean statoPrenotazione;
	
	
	public PrenotazioneBean() {
		// TODO Auto-generated constructor stub
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
	public Long getIdPrenotazione() {
		return idPrenotazione;
	}
	/**
	 * @param idPrenotazione the idPrenotazione to set
	 */
	public void setIdPrenotazione(Long idPrenotazione) {
		this.idPrenotazione = idPrenotazione;
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
	public List<PagamentoBean> getPagamentiAssociati() {
		return pagamentiAssociati;
	}
	/**
	 * @param pagamentiAssociati the pagamentiAssociati to set
	 */
	public void setPagamentiAssociati(List<PagamentoBean> pagamentiAssociati) {
		this.pagamentiAssociati = pagamentiAssociati;
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
	

}
