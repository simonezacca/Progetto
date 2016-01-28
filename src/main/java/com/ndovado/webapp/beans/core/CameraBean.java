package com.ndovado.webapp.beans.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="cameraBean")
public class CameraBean implements Serializable, Identifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id = null;
	private String nomeCamera;
	private Integer qtyCamera = new Integer(1);
	private StrutturaBean struttura;
	
	private DescrizioneCameraBean descrizioneCorrente;
	private List<DescrizioneCameraBean> descrizioniCamera = new ArrayList<DescrizioneCameraBean>();
	
	public CameraBean() {
		descrizioneCorrente = new DescrizioneCameraBean(this);
		getDescrizioniCamera().add(descrizioneCorrente);
	}
	
	public CameraBean(StrutturaBean s) {
		this.struttura = s;
		s.addCameraBean(this);
		descrizioneCorrente = new DescrizioneCameraBean(this);
		getDescrizioniCamera().add(descrizioneCorrente);
	}
	
	public void setStruttura(StrutturaBean s) {
		this.struttura= s;
	}

	/**
	 * @param idCamera the idCamera to set
	 */
	public void setId(Long idCamera) {
		this.id = idCamera;
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
	public DescrizioneCameraBean getDescrizioneCorrente() {
		return descrizioneCorrente;
	}

	/**
	 * @param descCorrente the descCorrente to set
	 */
	public void setDescrizioneCorrente(DescrizioneCameraBean descCorrente) {
		this.descrizioneCorrente = descCorrente;
		this.getDescrizioniCamera().add(descCorrente);
	}
	
	/**
	 * @return the strutturaBean
	 */
	public StrutturaBean getStruttura() {
		return struttura;
	}
	
	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		
		risultato.append("CameraBean [id:"+this.id+
						", nomeCamera"+this.nomeCamera+
						", qtyCamera"+this.qtyCamera+"]\n\t");
		
		List<DescrizioneCameraBean> descCamBeans = this.getDescrizioniCamera();
		for (DescrizioneCameraBean dcb : descCamBeans) {
			risultato.append(dcb+"\n\t\t");
		}
		return risultato.toString();
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
		result = prime * result + ((descrizioneCorrente == null) ? 0 : descrizioneCorrente.hashCode());
		result = prime * result + ((getDescrizioniCamera() == null) ? 0 : getDescrizioniCamera().hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof CameraBean))
			return false;
		CameraBean other = (CameraBean) obj;
		if (descrizioneCorrente == null) {
			if (other.descrizioneCorrente != null)
				return false;
		} else if (!descrizioneCorrente.equals(other.descrizioneCorrente))
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
	 * @return the descrizioniCamera
	 */
	public List<DescrizioneCameraBean> getDescrizioniCamera() {
		return descrizioniCamera;
	}

	/**
	 * @param descrizioniCamera the descrizioniCamera to set
	 */
	public void setDescrizioniCamera(List<DescrizioneCameraBean> descrizioniCamera) {
		this.descrizioniCamera = descrizioniCamera;
	}
}
