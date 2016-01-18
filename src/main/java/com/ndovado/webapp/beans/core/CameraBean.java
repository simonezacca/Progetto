package com.ndovado.webapp.beans.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ndovado.dominio.core.Camera;
import com.ndovado.dominio.core.DescrizioneCamera;

public class CameraBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idCamera = new Long(0);
	private String nomeCamera;
	private Integer qtyCamera = new Integer(1);
	private DescrizioneCameraBean descCorrente;
	
	private List<DescrizioneCameraBean> descrizioni;
	
	public CameraBean() {
		setDescrizioni(new ArrayList<DescrizioneCameraBean>());
	}
	
	public CameraBean(Camera c) {
		setDescrizioni(new ArrayList<DescrizioneCameraBean>());
		fillBeanFromModel(c);
	}
	
	public void fillBeanFromModel(Camera c) {
		if (c!=null) {
			this.idCamera = c.getId();
			this.nomeCamera = c.getNomeCamera();
			this.qtyCamera = c.getQuantity();
			this.descCorrente = new DescrizioneCameraBean(c.getDescrizioneCameraCorrente());
			
			List<DescrizioneCamera> descsModel = c.getAllDescrizioniCamera();
			for (DescrizioneCamera descrizioneCamera : descsModel) {
				getDescrizioni().add(new DescrizioneCameraBean(descrizioneCamera));
			}
		}
	}

	/**
	 * @return the idCamera
	 */
	public Long getIdCamera() {
		return idCamera;
	}

	/**
	 * @param idCamera the idCamera to set
	 */
	public void setIdCamera(Long idCamera) {
		this.idCamera = idCamera;
	}

	/**
	 * @return the nomeCamera
	 */
	public String getNomeCamera() {
		return nomeCamera;
	}

	/**
	 * @param nomeCamera the nomeCamera to set
	 */
	public void setNomeCamera(String nomeCamera) {
		this.nomeCamera = nomeCamera;
	}

	/**
	 * @return the qtyCamera
	 */
	public Integer getQtyCamera() {
		return qtyCamera;
	}

	/**
	 * @param qtyCamera the qtyCamera to set
	 */
	public void setQtyCamera(Integer qtyCamera) {
		this.qtyCamera = qtyCamera;
	}

	/**
	 * @return the descCorrente
	 */
	public DescrizioneCameraBean getDescCorrente() {
		return descCorrente;
	}

	/**
	 * @param descCorrente the descCorrente to set
	 */
	public void setDescCorrente(DescrizioneCameraBean descCorrente) {
		this.descCorrente = descCorrente;
	}
	
	public boolean isNewBean() {
		return this.idCamera==0 || this.idCamera == null;
	}

	/**
	 * @return the descrizioni
	 */
	public List<DescrizioneCameraBean> getDescrizioni() {
		return descrizioni;
	}

	/**
	 * @param descrizioni the descrizioni to set
	 */
	public void setDescrizioni(List<DescrizioneCameraBean> descrizioni) {
		this.descrizioni = descrizioni;
	}
}
