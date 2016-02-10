package com.ndovado.dominio.core;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ndovado.dominio.prenotazioni.IPrenotabile;
import com.ndovado.tecservices.persistence.base.IPersistente;

/**
 * Modella l'entità di dominio Camera
 */
@Entity
@Table(name = "camera")
public class Camera implements IPersistente, IPrenotabile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Identificativo di tipo <code>Integer</code> utilizzato per il mapping ORM
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	/**
	 * Riferimento ad un'istanza di <code>DescrizioneCameraz</code>
	 */
	// @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER,
	// mappedBy="cameraAssociata")
	// @PrimaryKeyJoinColumn(name="descrizioneCorrente_id")
	// private DescrizioneCamera descrizioneCorrente;

	/**
	 * Nome della camera
	 */
	@Column(name = "nome", nullable = false, length = 45)
	private String nomeCamera;

	/**
	 * Riferimento ad un'istanza della classe <code>Struttura</code> utilizzata
	 * per aggregare più camere
	 */
	@ManyToOne
	@PrimaryKeyJoinColumn
	private Struttura struttura;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_inizio_affitto")
	private Date dataInizioAffitto;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_fine_affitto")
	private Date dataFineAffitto;

	@Column(name = "pax")
	private Integer pax = new Integer(1);

	@Column(name = "prezzo")
	private Float prezzo = new Float(0);

	@Column(name = "descrizione")
	private String descrizioneCamera;

	/**
	 * Lista ordinata di oggetti di tipo <code>DescrizioneCamera</code>
	 * utilizzata per mantenere una cronologia dei cambiamenti sulle date,
	 * prezzi e pax
	 */

	public Camera() {
	}

	/**
	 * Costruttore di default
	 */
	public Camera(Struttura s) {
		this.struttura = s;
		// aggiungo l'istanza corrente alla lista delle camere disponibili
		s.addCamera(this);
	}

	/**
	 * @return l'identificativo della camera utilizzato per il mapping ORM
	 */
	public Long getId() {
		return this.id;
	}

	public void setId(Long idCamera) {
		this.id = idCamera;
	}

	/**
	 * @return il nome della camera
	 */
	public String getNomeCamera() {
		return this.nomeCamera;
	}

	/**
	 * @param aNomeCamera
	 *            nuovo nome della camera
	 */
	public void setNomeCamera(String aNomeCamera) {
		if (aNomeCamera != null) {
			this.nomeCamera = aNomeCamera;
		}
	}

	/**
	 * @return il numero massimo di persone per la capienza della camera
	 */
	public Integer getPax() {
		return this.pax;
	}

	/**
	 * @param aPax
	 *            il numero massimo di persone per la capienza della camera
	 */
	public void setPax(Integer aPax) {
		if (aPax != null) {
			this.pax = aPax;
		}
	}

	/**
	 * @param aDataInizioAffitto
	 *            data di inizio disponibilità al soggiorno
	 * @param aDataFineAffitto
	 *            data di fine disponibilità al soggiorno
	 */
	public void setDataIntervalloAffitto(Date aDataInizioAffitto, Date aDataFineAffitto) {
		if (aDataInizioAffitto != null && aDataFineAffitto != null) {
			// controllo che l'ordine delle date sia corretto
			if (aDataInizioAffitto.before(aDataFineAffitto)) {
				// aggiorno i valori per il riferimento corrente a descrizione
				// camera
				this.dataInizioAffitto = aDataInizioAffitto;
				this.dataFineAffitto = aDataFineAffitto;
			}
		}
	}

	/**
	 * @param aPrezzo
	 *            il prezzo della camera da associare alla descrizione camera
	 *            corrente
	 */
	public void setPrezzo(Float aPrezzo) {
		if (aPrezzo != null) {
			if (aPrezzo >= 0) {
				this.prezzo = aPrezzo;
			}
		}
	}

	/**
	 * @return il prezzo della camera associato alla descrizione camera corrente
	 */
	public Float getPrezzo() {
		return this.prezzo;
	}

	/**
	 * @return Una descrizione testuale contenente nome della camera e numero di
	 *         persone
	 */
	public String getNomeOggettoPrenotabile() {
		return "Camera: " + this.nomeCamera + " - Pax(" + this.getPax() + ")";
	}

	/**
	 * @return La struttura associata alla camera
	 */
	public Struttura getStruttura() {
		return this.struttura;
	}

	public void setStruttura(Struttura s) {
		if (s != null) {
			// imposto la struttura s come struttura della camera
			this.struttura = s;
			// aggiungo la camera corrente all'elenco delle camere collegate
			// alla struttura s
			// s.addCamera(this);
		}
	}

	/**
	 * @param descrizioniCamera
	 *            the descrizioniCamera to set
	 */
	public void setDescrizioneCamera(String descrizioneCamera) {
		this.descrizioneCamera = descrizioneCamera;
	}

	/**
	 * @return the descrizioneCorrente
	 */
	public String getDescrizioneCamera() {
		return this.descrizioneCamera;
	}

	/**
	 * @return the dataInizioAffitto
	 */
	public Date getDataInizioAffitto() {
		return dataInizioAffitto;
	}

	/**
	 * @param dataInizioAffitto
	 *            the dataInizioAffitto to set
	 */
	public void setDataInizioAffitto(Date dataInizioAffitto) {
		this.dataInizioAffitto = dataInizioAffitto;
	}

	/**
	 * @return the dataFineAffitto
	 */
	public Date getDataFineAffitto() {
		return dataFineAffitto;
	}

	/**
	 * @param dataFineAffitto
	 *            the dataFineAffitto to set
	 */
	public void setDataFineAffitto(Date dataFineAffitto) {
		this.dataFineAffitto = dataFineAffitto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataFineAffitto == null) ? 0 : dataFineAffitto.hashCode());
		result = prime * result + ((dataInizioAffitto == null) ? 0 : dataInizioAffitto.hashCode());
		result = prime * result + ((descrizioneCamera == null) ? 0 : descrizioneCamera.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomeCamera == null) ? 0 : nomeCamera.hashCode());
		result = prime * result + ((pax == null) ? 0 : pax.hashCode());
		result = prime * result + ((prezzo == null) ? 0 : prezzo.hashCode());
		//result = prime * result + ((struttura == null) ? 0 : struttura.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Camera))
			return false;
		Camera other = (Camera) obj;
		if (dataFineAffitto == null) {
			if (other.dataFineAffitto != null)
				return false;
		} else if (!dataFineAffitto.equals(other.dataFineAffitto))
			return false;
		if (dataInizioAffitto == null) {
			if (other.dataInizioAffitto != null)
				return false;
		} else if (!dataInizioAffitto.equals(other.dataInizioAffitto))
			return false;
		if (descrizioneCamera == null) {
			if (other.descrizioneCamera != null)
				return false;
		} else if (!descrizioneCamera.equals(other.descrizioneCamera))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeCamera == null) {
			if (other.nomeCamera != null)
				return false;
		} else if (!nomeCamera.equals(other.nomeCamera))
			return false;
		if (pax == null) {
			if (other.pax != null)
				return false;
		} else if (!pax.equals(other.pax))
			return false;
		if (prezzo == null) {
			if (other.prezzo != null)
				return false;
		} else if (!prezzo.equals(other.prezzo))
			return false;
		if (struttura == null) {
			if (other.struttura != null)
				return false;
		} else if (!struttura.equals(other.struttura))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Camera [id=" + id + ", nomeCamera=" + nomeCamera + ", struttura=" + struttura + ", dataInizioAffitto="
				+ dataInizioAffitto + ", dataFineAffitto=" + dataFineAffitto + ", pax=" + pax + ", prezzo=" + prezzo
				+ ", descrizioneCamera=" + descrizioneCamera + "]";
	}

	
}