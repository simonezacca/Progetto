package com.ndovado.webapp.beans.core;

import javax.faces.bean.ManagedBean;

import com.ndovado.dominio.core.Luogo;

@ManagedBean(name="luogoBean")
public class LuogoBean {
	
	private Long idLuogo = new Long(0);
	private String nomeComune;
	private String cap;
	private String provincia;
	
	public LuogoBean() {
		// TODO Auto-generated constructor stub
	}

	public LuogoBean(Luogo l) {
		fillBeanFromModel(l);
	}
	
	public void fillBeanFromModel(Luogo l) {
		if (l!=null) {
			this.idLuogo = l.getId();
			this.nomeComune = l.getNomeComune();
			this.cap = l.getCAP();
			this.provincia = l.getProvincia();
		}
	}
	/**
	 * @return the nomeComune
	 */
	public String getNomeComune() {
		return nomeComune;
	}
	/**
	 * @param nomeComune the nomeComune to set
	 */
	public void setNomeComune(String nomeComune) {
		this.nomeComune = nomeComune;
	}
	/**
	 * @return the idLuogo
	 */
	public Long getIdLuogo() {
		return idLuogo;
	}
	/**
	 * @param idLuogo the idLuogo to set
	 */
	public void setIdLuogo(Long idLuogo) {
		this.idLuogo = idLuogo;
	}
	/**
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}
	/**
	 * @param cap the cap to set
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}
	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}
	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
}
