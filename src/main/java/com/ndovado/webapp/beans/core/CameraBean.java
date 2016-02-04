package com.ndovado.webapp.beans.core;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;

import com.ndovado.webapp.beans.prenotazioni.IPrenotabileBean;

@ManagedBean(name="cameraBean")
public class CameraBean implements Serializable, Identifiable, IPrenotabileBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id = null;
	private String nomeCamera;
	private StrutturaBean struttura;
	
	private Date dataInizioAffitto;
	private Date dataFineAffitto;
	private Integer pax = new Integer(1);
	private Float prezzo = new Float(0);
	private String descrizioneCamera;
	
	public CameraBean() {
	}
	
	public CameraBean(StrutturaBean s) {
		this.struttura = s;
		s.addCameraBean(this);
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
	 * @return the strutturaBean
	 */
	public StrutturaBean getStruttura() {
		return struttura;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public Boolean isNewBean() {
		return this.id==0 || this.id == null;
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
	 * @return the prezzo
	 */
	public Float getPrezzo() {
		return prezzo;
	}

	/**
	 * @param prezzo the prezzo to set
	 */
	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
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
}
