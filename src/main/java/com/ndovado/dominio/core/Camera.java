package com.ndovado.dominio.core;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;

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
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="cameraAssociata")
	@PrimaryKeyJoinColumn(name="descrizioneCorrente_id")
	private DescrizioneCamera descrizioneCorrente;

	/**
	 * Nome della camera
	 */
	@Column(name = "nome", nullable = false, length = 45)
	private String nomeCamera;

	/**
	 * Numero di camera disponibili della tipologia
	 */
	@Column(name = "qta")
	private Integer qtyCamera;

	/**
	 * Riferimento ad un'istanza della classe <code>Struttura</code> utilizzata per aggregare più camere
	 */
	@ManyToOne
	@PrimaryKeyJoinColumn
	private Struttura struttura;

	/**
	 * Lista ordinata di oggetti di tipo <code>DescrizioneCamera</code> utilizzata per mantenere una cronologia
	 * dei cambiamenti sulle date, prezzi e pax
	 */
	@OneToMany(mappedBy = "cameraAssociata",cascade=CascadeType.ALL)
	@OrderBy(clause="id")
	private List<DescrizioneCamera> descrizioniCamera;

	
	public Camera() {}
	/**
	 * Costruttore di default
	 */
	public Camera(Struttura s) {
		setDescrizioniCamera(new ArrayList<DescrizioneCamera>());
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

	protected void setId(Long idCamera) {
		this.id = idCamera;
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
	public Integer getQtyCamera() {
		return qtyCamera;
	}

	/**
	 * @param aQtyCamera numero di camere disponibili di quella tipologia
	 */
	public void setQtyCamera(Integer aQtyCamera) {
		this.qtyCamera = aQtyCamera;
	}

	/**
	 * @return il numero massimo di persone per la capienza della camera
	 */
	public Integer getPax() {
		return this.getDescrizioneCorrente().getPax();
	}

	/**
	 * @param aPax il numero massimo di persone per la capienza della camera
	 */
	public void setPax(Integer aPax) {
		if (aPax!=null) {
			this.getDescrizioneCorrente().setPax(aPax);
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
				this.getDescrizioneCorrente().setDataInizioAffitto(aDataInizioAffitto);
				this.getDescrizioneCorrente().setDataFineAffitto(aDataFineAffitto);
			}
		}
	}

	/**
	 * @param aPrezzo il prezzo della camera da associare alla descrizione camera corrente
	 */
	public void setPrezzo(Float aPrezzo) {
		if (aPrezzo!=null) {
			if (aPrezzo>=0) {
				this.getDescrizioneCorrente().setPrezzoCamera(aPrezzo);
			}
		}
	}

	/**
	 * @return il prezzo della camera associato alla descrizione camera corrente
	 */
	public Float getPrezzo() {
		return this.getDescrizioneCorrente().getPrezzoCamera();
	}

	/**
	 * @param aDescrizioneCamera Nuova descrizione camera da utilizzare come descrizione camera corrente
	 */
	public void addDescrizioneCamera(DescrizioneCamera aDescrizioneCamera) {
		if (aDescrizioneCamera!=null) {
			// aggiungo la descrizione camera all'elenco delle descrizioni della camera
			this.getDescrizioniCamera().add(aDescrizioneCamera);
			// collego la descrizione camera alla camera corrente
			aDescrizioneCamera.setCameraAssociata(this);
		}
	}

	/**
	 * @return Un elenco di oggetti di tipo <code>DescrizioneCamera</code> associata alla camera ordinati per data di aggiunta
	 */
	public List<DescrizioneCamera> getDescrizioniCamera() {
		return descrizioniCamera;
	}

	/**
	 * @return Una descrizione testuale contenente nome della camera e numero di persone
	 */
	public String getNomeOggettoPrenotabile() {
		return "Camera: "+this.nomeCamera+" - Pax("+this.getDescrizioneCorrente().getPax()+")";
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
			//s.addCamera(this);
		}
	}
	
	public void removeDescrizioneCamera(DescrizioneCamera dc) {
		if (this.getDescrizioniCamera().contains(dc)) {
			this.getDescrizioniCamera().remove(dc);
		}
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
		if (getDescrizioneCorrente() == null) {
			if (other.getDescrizioneCorrente() != null)
				return false;
		} else if (!getDescrizioneCorrente().equals(other.getDescrizioneCorrente()))
			return false;
		if (getDescrizioniCamera() == null) {
			if (other.getDescrizioniCamera() != null)
				return false;
		} else if (!getDescrizioniCamera().equals(other.getDescrizioniCamera()))
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
	 * @param descrizioniCamera the descrizioniCamera to set
	 */
	public void setDescrizioniCamera(List<DescrizioneCamera> descrizioniCamera) {
		this.descrizioniCamera = descrizioniCamera;
	}
	/**
	 * @return the descrizioneCorrente
	 */
	public DescrizioneCamera getDescrizioneCorrente() {
		return descrizioneCorrente;
	}
	/**
	 * @param descrizioneCorrente the descrizioneCorrente to set
	 */
	public void setDescrizioneCorrente(DescrizioneCamera descrizioneCorrente) {
		this.descrizioneCorrente = descrizioneCorrente;
	}
}