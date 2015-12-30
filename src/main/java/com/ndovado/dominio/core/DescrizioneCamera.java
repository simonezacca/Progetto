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

import com.ndovado.tecservices.persistenza.base.IPersistente;

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
	private Long idDescrizioneCamera;

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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inizio")
	private Date dataInizioAffitto;

	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
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
	public DescrizioneCamera() {
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
		return idDescrizioneCamera;
	}

	/**
	 * @param idCamera the idCamera to set
	 */
	protected void setId(Long idCamera) {
		this.idDescrizioneCamera = idCamera;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cameraAssociata == null) ? 0 : cameraAssociata.hashCode());
		result = prime * result + ((dataFineAffitto == null) ? 0 : dataFineAffitto.hashCode());
		result = prime * result + ((dataInizioAffitto == null) ? 0 : dataInizioAffitto.hashCode());
		result = prime * result + ((descrizioneCamera == null) ? 0 : descrizioneCamera.hashCode());
		result = prime * result + ((idDescrizioneCamera == null) ? 0 : idDescrizioneCamera.hashCode());
		result = prime * result + ((pax == null) ? 0 : pax.hashCode());
		result = prime * result + ((prezzoCamera == null) ? 0 : prezzoCamera.hashCode());
		return result;
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
		} else if (!cameraAssociata.equals(other.cameraAssociata))
			return false;
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
		if (idDescrizioneCamera == null) {
			if (other.idDescrizioneCamera != null)
				return false;
		} else if (!idDescrizioneCamera.equals(other.idDescrizioneCamera))
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