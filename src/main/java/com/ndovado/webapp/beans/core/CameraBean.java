package com.ndovado.webapp.beans.core;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;

import org.joda.time.LocalDate;

import com.ndovado.webapp.beans.prenotazioni.IPrenotabileBean;

@ManagedBean(name="cameraBean")
public class CameraBean implements Serializable, Identifiable, IPrenotabileBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nomeCamera;
	private StrutturaBean struttura;
	
	private LocalDate dataInizioAffitto = new LocalDate();
	private LocalDate dataFineAffitto = dataInizioAffitto.plusDays(1);
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
	public LocalDate getDataInizioAffitto() {
		return dataInizioAffitto;
	}

	/**
	 * @param dataInizioAffitto the dataInizioAffitto to set
	 */
	public void setDataInizioAffitto(LocalDate dataInizioAffitto) {
		this.dataInizioAffitto = dataInizioAffitto;
	}

	/**
	 * @return the dataFineAffitto
	 */
	public LocalDate getDataFineAffitto() {
		return dataFineAffitto;
	}

	/**
	 * @param dataFineAffitto the dataFineAffitto to set
	 */
	public void setDataFineAffitto(LocalDate dataFineAffitto) {
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
	@Override
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

	@Override
	public String getNomeOggettoPrenotabile() {
		return this.nomeCamera+" - Pax: "+this.pax;
	}
	
	
}
