package com.ndovado.dominio.core;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ndovado.tecservices.persistenza.base.IIdentificabile;

/**
 * Implementare i metodi equals() and hasCode()
 */
@Entity
@Table(name = "descrizionecamera")
public class DescrizioneCamera implements IIdentificabile{

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
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long idDescrizioneCamera;

	/**
	 * 
	 */
	@Column(name = "descrizione")
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
	@Column(name = "data_inizio_affitto")
	private Date dataInizioAffitto;

	/**
	 * 
	 */
	@Column(name = "data_fine_affitto")
	private Date dataFineAffitto;

	/**
	 * 
	 */
	@ManyToOne
    @JoinColumn(name="camera_id")
	private Camera cameraAssociata;


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

}