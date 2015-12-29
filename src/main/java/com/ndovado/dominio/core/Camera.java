package com.ndovado.dominio.core;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ndovado.dominio.prenotazioni.IPrenotabile;
import com.ndovado.tecservices.persistenza.base.IPersistente;


/**
 * Modella l'entità di dominio Camera
 */
@Entity
@Table(name = "descrizionecamera")
public class Camera implements IPersistente, IPrenotabile {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrizioneCorrente == null) ? 0 : descrizioneCorrente.hashCode());
		result = prime * result + ((descrizioniCamera == null) ? 0 : descrizioniCamera.hashCode());
		result = prime * result + ((idCamera == null) ? 0 : idCamera.hashCode());
		result = prime * result + ((nomeCamera == null) ? 0 : nomeCamera.hashCode());
		result = prime * result + ((qtyCamera == null) ? 0 : qtyCamera.hashCode());
		result = prime * result + ((struttura == null) ? 0 : struttura.hashCode());
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
		if (descrizioneCorrente == null) {
			if (other.descrizioneCorrente != null)
				return false;
		} else if (!descrizioneCorrente.equals(other.descrizioneCorrente))
			return false;
		if (descrizioniCamera == null) {
			if (other.descrizioniCamera != null)
				return false;
		} else if (!descrizioniCamera.equals(other.descrizioniCamera))
			return false;
		if (idCamera == null) {
			if (other.idCamera != null)
				return false;
		} else if (!idCamera.equals(other.idCamera))
			return false;
		if (nomeCamera == null) {
			if (other.nomeCamera != null)
				return false;
		} else if (!nomeCamera.equals(other.nomeCamera))
			return false;
		if (qtyCamera == null) {
			if (other.qtyCamera != null)
				return false;
		} else if (!qtyCamera.equals(other.qtyCamera))
			return false;
		if (struttura == null) {
			if (other.struttura != null)
				return false;
		} else if (!struttura.equals(other.struttura))
			return false;
		return true;
	}

	/**
	 * Costruttore di default
	 */
	public Camera() {
		descrizioniCamera = new ArrayList<DescrizioneCamera>();
	}

	/**
	 * Riferimento ad un'istanza di <code>DescrizioneCameraz</code>
	 */
	@OneToOne
	@JoinColumn(name="descrizionecamera_id")
	private DescrizioneCamera descrizioneCorrente;
	
	/**
	 * Identificativo di tipo <code>Integer</code> utilizzato per il mapping ORM
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long idCamera;

	/**
	 * Nome della camera
	 */
	@Column(name = "nome")
	private String nomeCamera;

	/**
	 * Numero di camera disponibili della tipologia
	 */
	@Column(name = "qty")
	private Integer qtyCamera;

	/**
	 * Riferimento ad un'istanza della classe <code>Struttura</code> utilizzata per aggregare più camere
	 */
	@ManyToOne
	@JoinColumn(name = "struttura_id")
	private Struttura struttura;

	/**
	 * Lista ordinata di oggetti di tipo <code>DescrizioneCamera</code> utilizzata per mantenere una cronologia
	 * dei cambiamenti sulle date, prezzi e pax
	 */
	@OneToMany(mappedBy="cameraAssociata")
	private List<DescrizioneCamera> descrizioniCamera;

	/**
	 * @return l'identificativo della camera utilizzato per il mapping ORM
	 */
	public Long getId() {
		return this.idCamera;
	}

	protected void setId(Long idCamera) {
		this.idCamera = idCamera;
	}
	/**
	 * @return il nome della camera
	 */
	public String getNomeCamera() {
		return this.nomeCamera;
	}

	/**
	 * @param aNomeCamera nuovo nome della camera
	 */
	public void setNomeCamera(String aNomeCamera) {
		if (aNomeCamera!=null) {
			this.nomeCamera= aNomeCamera;
		}
	}

	/**
	 * @return la quantità di camere disponibili di quella tipologia
	 */
	public Integer getQuantity() {
		return qtyCamera;
	}

	/**
	 * @param aQtyCamera numero di camere disponibili di quella tipologia
	 */
	public void setQuantity(Integer aQtyCamera) {
		this.qtyCamera = aQtyCamera;
	}

	/**
	 * @return il numero massimo di persone per la capienza della camera
	 */
	public Integer getPax() {
		return this.descrizioneCorrente.getPax();
	}

	/**
	 * @param aPax il numero massimo di persone per la capienza della camera
	 */
	public void setPax(Integer aPax) {
		if (aPax!=null) {
			this.descrizioneCorrente.setPax(aPax);
		}
	}

	/**
	 * @param aDataInizioAffitto data di inizio disponibilità al soggiorno
	 * @param aDataFineAffitto data di fine disponibilità al soggiorno
	 */
	public void setDataIntervalloAffitto(Date aDataInizioAffitto, Date aDataFineAffitto) {
		if (aDataInizioAffitto!=null && aDataFineAffitto!=null) {
			// controllo che l'ordine delle date sia corretto
			if (aDataInizioAffitto.before(aDataFineAffitto)) {
				//aggiorno i valori per il riferimento corrente a descrizione camera
				this.descrizioneCorrente.setDataInizioAffitto(aDataInizioAffitto);
				this.descrizioneCorrente.setDataFineAffitto(aDataFineAffitto);
			}
		}
	}

	/**
	 * @param aPrezzo il prezzo della camera da associare alla descrizione camera corrente
	 */
	public void setPrezzo(Float aPrezzo) {
		if (aPrezzo!=null) {
			if (aPrezzo>=0) {
				this.descrizioneCorrente.setPrezzoCamera(aPrezzo);
			}
		}
	}

	/**
	 * @return il prezzo della camera associato alla descrizione camera corrente
	 */
	public Float getPrezzo() {
		return this.descrizioneCorrente.getPrezzoCamera();
	}

	/**
	 * @return la descrizione camera corrente
	 */
	public DescrizioneCamera getDescrizioneCameraCorrente() {
		return this.descrizioneCorrente;
	}

	/**
	 * @param aDescrizioneCamera Nuova descrizione camera da utilizzare come descrizione camera corrente
	 */
	public void addDescrizioneCamera(DescrizioneCamera aDescrizioneCamera) {
		if (aDescrizioneCamera!=null) {
			// aggiungo la descrizione camera all'elenco delle descrizioni della camera
			this.descrizioniCamera.add(aDescrizioneCamera);
			// collego la descrizione camera alla camera corrente
			aDescrizioneCamera.setCameraAssociata(this);
		}
	}

	/**
	 * @return Un elenco di oggetti di tipo <code>DescrizioneCamera</code> associata alla camera ordinati per data di aggiunta
	 */
	public List<DescrizioneCamera> getAllDescrizioniCamera() {
		return descrizioniCamera;
	}

	/**
	 * @return Una descrizione testuale contenente nome della camera e numero di persone
	 */
	public String getNomeOggettoPrenotabile() {
		return "Camera: "+this.nomeCamera+" - Pax("+this.getDescrizioneCameraCorrente().getPax()+")";
	}

	/**
	 * @return La struttura associata alla camera
	 */
	public Struttura getStruttura() {
		return this.struttura;
	}
	
	public void setStruttura(Struttura s) {
		if (s!=null) {
			// imposto la struttura s come struttura della camera
			this.struttura = s;
			// aggiungo la camera corrente all'elenco delle camere collegate alla struttura s
			s.addCamera(this);
		}
	}
	
	public void removeDescrizioneCamera(DescrizioneCamera dc) {
		if (this.descrizioniCamera.contains(dc)) {
			this.descrizioniCamera.remove(dc);
		}
	}
}