package com.ndovado.webapp.beans.core;

import java.io.Serializable;
import java.util.Date;

import com.ndovado.dominio.core.DescrizioneCamera;


public class DescrizioneCameraBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idDescrizioneCamera = new Long(0);
	private String descrizioneCamera;
	private Integer pax = new Integer(0);
	private Float prezzoCamera = new Float(0);
	private Date dataInizioAffitto;
	private Date dataFineAffitto;
	
	private CameraBean cameraAssociata;
	
	
	public DescrizioneCameraBean() {
		// TODO Auto-generated constructor stub
	}
	
	public DescrizioneCameraBean(DescrizioneCamera desc) {
		fillBeanFromModel(desc);
	}
	
	public void fillBeanFromModel(DescrizioneCamera desc) {
		if (desc !=null) {
			this.idDescrizioneCamera = desc.getId();
			this.descrizioneCamera = desc.getDescrizioneCamera();
			this.pax = desc.getPax();
			this.prezzoCamera = desc.getPrezzoCamera();
			this.dataInizioAffitto = desc.getDataInizioAffitto();
			this.dataFineAffitto = desc.getDataFineAffitto();
		}
	}
	
	/**
	 * @return the idDescrizioneCamera
	 */
	public Long getIdDescrizioneCamera() {
		return idDescrizioneCamera;
	}
	/**
	 * @param idDescrizioneCamera the idDescrizioneCamera to set
	 */
	public void setIdDescrizioneCamera(Long idDescrizioneCamera) {
		this.idDescrizioneCamera = idDescrizioneCamera;
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
	
	public boolean isNewBean() {
		return this.idDescrizioneCamera==0 || this.idDescrizioneCamera == null;
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
		
	
}
