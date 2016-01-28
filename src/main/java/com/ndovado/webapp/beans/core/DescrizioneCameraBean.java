package com.ndovado.webapp.beans.core;

import java.io.Serializable;
import java.util.Date;

import com.ndovado.dominio.core.DescrizioneCamera;


public class DescrizioneCameraBean implements Serializable, Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id = null;
	private String descrizioneCamera;
	private Integer pax = new Integer(1);
	private Float prezzoCamera = new Float(0);
	private Date dataInizioAffitto;
	private Date dataFineAffitto;
	
	private CameraBean cameraAssociata;
	
	
	public DescrizioneCameraBean() {
		// TODO Auto-generated constructor stub
	}
	
	public DescrizioneCameraBean(CameraBean camera) {
		this.cameraAssociata = camera;
	}
	
	/**
	 * @param idDescrizioneCamera the idDescrizioneCamera to set
	 */
	public void setId(Long idDescrizioneCamera) {
		this.id = idDescrizioneCamera;
	}
	/**
	 * @return the descrizioneCamera
	 */
	public String getDescrizioneCamera() {
		return descrizioneCamera;
	}
	/**
	 * @param descrizioneCamera the descrizioneCamera to set
	 */
	public void setDescrizioneCamera(String descrizioneCamera) {
		this.descrizioneCamera = descrizioneCamera;
	}
	/**
	 * @return the pax
	 */
	public Integer getPax() {
		return pax;
	}
	/**
	 * @param pax the pax to set
	 */
	public void setPax(Integer pax) {
		this.pax = pax;
	}
	/**
	 * @return the prezzoCamera
	 */
	public Float getPrezzoCamera() {
		return prezzoCamera;
	}
	/**
	 * @param prezzoCamera the prezzoCamera to set
	 */
	public void setPrezzoCamera(Float prezzoCamera) {
		this.prezzoCamera = prezzoCamera;
	}
	/**
	 * @return the dataInizioAffitto
	 */
	public Date getDataInizioAffitto() {
		return dataInizioAffitto;
	}
	/**
	 * @param dataInizioAffitto the dataInizioAffitto to set
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
	 * @param dataFineAffitto the dataFineAffitto to set
	 */
	public void setDataFineAffitto(Date dataFineAffitto) {
		this.dataFineAffitto = dataFineAffitto;
	}
	

	/**
	 * @return the cameraAssociata
	 */
	public CameraBean getCameraAssociata() {
		return cameraAssociata;
	}

	/**
	 * @param cameraAssociata the cameraAssociata to set
	 */
	public void setCameraAssociata(CameraBean cameraAssociata) {
		this.cameraAssociata = cameraAssociata;
	}
	
	@Override
	public String toString() {
		return "DescrizioneCameraBean [id:"+this.id+
				", dataInizio:"+this.dataInizioAffitto+
				", dataFine:"+this.dataFineAffitto+
				", descrizione:"+this.descrizioneCamera+
				", pax:"+this.pax+
				", prezzo:"+this.prezzoCamera+"]";
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public Boolean isNewBean() {
		return this.id==0 || this.id == null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cameraAssociata == null) ? 0 : cameraAssociata.hashCode());
		result = prime * result + ((dataFineAffitto == null) ? 0 : dataFineAffitto.hashCode());
		result = prime * result + ((dataInizioAffitto == null) ? 0 : dataInizioAffitto.hashCode());
		result = prime * result + ((descrizioneCamera == null) ? 0 : descrizioneCamera.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof DescrizioneCameraBean))
			return false;
		DescrizioneCameraBean other = (DescrizioneCameraBean) obj;
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
