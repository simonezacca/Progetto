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

import com.ndovado.tecservices.persistence.base.IPersistente;

/**
 * Implementare i metodi equals() and hasCode()
 */
@Entity
@Table(name = "descrizione_camera")
public class DescrizioneCamera implements IPersistente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	/**
	 * 
	 */
	@Column(name = "descrizione", nullable = false)
	private String descrizioneCamera;

	/**
	 * 
	 */
	@Column(name = "pax")
	private Integer pax;

	/**
	 * 
	 */
	@Column(name = "prezzo")
	private Float prezzoCamera;

	/**
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "data_inizio")
	private Date dataInizioAffitto;

	/**
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "data_fine")
	private Date dataFineAffitto;

	/**
	 * 
	 */
	@ManyToOne
	@PrimaryKeyJoinColumn
	private Camera cameraAssociata;


	/**
	 * Default constructor
	 */
	protected DescrizioneCamera() {
	
	}
	/**
	 * 
	 * @param aCamera
	 */
	public DescrizioneCamera(Camera aCamera) {
		if (aCamera!=null) {
			this.setCameraAssociata(aCamera);
		}
	}
	/**
	 * @return
	 */
	
	public String getDescrizioneCamera() {
		return this.descrizioneCamera;
	}

	/**
	 * @param aDescrizioneCamera
	 */
	public void setDescrizioneCamera(String aDescrizioneCamera) {
		if (aDescrizioneCamera!=null) {
			this.descrizioneCamera = aDescrizioneCamera;
		}
	}

	/**
	 * @return
	 */
	public Integer getPax() {
		return this.pax;
	}

	/**
	 * @param aPax
	 */
	public void setPax(Integer aPax) {
		if (aPax!=null) {
			this.pax = aPax;
		}
	}

	/**
	 * @return
	 */
	public Float getPrezzoCamera() {
		return this.prezzoCamera;
	}

	/**
	 * @param aPrezzoCamera
	 */
	public void setPrezzoCamera(Float aPrezzoCamera) {
		if (aPrezzoCamera!=null) {
			this.prezzoCamera = aPrezzoCamera;
		}
	}

	/**
	 * @return
	 */
	public Date getDataInizioAffitto() {
		return this.dataInizioAffitto;
	}

	/**
	 * @param aDataInizioAffitto
	 */
	public void setDataInizioAffitto(Date aDataInizioAffitto) {
		if (aDataInizioAffitto!=null) {
			this.dataInizioAffitto = aDataInizioAffitto;
		}
	}

	/**
	 * @return
	 */
	public Date getDataFineAffitto() {
		return this.dataFineAffitto;
	}

	/**
	 * @param aDataFineAffitto
	 */
	public void setDataFineAffitto(Date aDataFineAffitto) {
		if (aDataFineAffitto!=null) {
			this.dataFineAffitto = aDataFineAffitto;
		}
	}

	/**
	 * @return the idCamera
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param idCamera the idCamera to set
	 */
	public void setId(Long idCamera) {
		this.id = idCamera;
	}
	/**
	 * @return the cameraAssociata
	 */
	public Camera getCameraAssociata() {
		return cameraAssociata;
	}
	/**
	 * @param cameraAssociata the cameraAssociata to set
	 */
	public void setCameraAssociata(Camera cameraAssociata) {
		if(cameraAssociata!=null) {
			this.cameraAssociata = cameraAssociata;
		}
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DescrizioneCamera))
			return false;
		DescrizioneCamera other = (DescrizioneCamera) obj;
		if (cameraAssociata == null) {
			if (other.cameraAssociata != null)
				return false;
		} else if (dataFineAffitto == null) {
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
		if (pax == null) {
			if (other.pax != null)
				return false;
		} else if (!pax.equals(other.pax))
			return false;
		if (prezzoCamera == null) {
			if (other.prezzoCamera != null)
				return false;
		} else if (!prezzoCamera.equals(other.prezzoCamera))
			return false;
		return true;
	}

}