package com.ndovado.webapp.beans.core;

import java.util.ArrayList;
import java.util.List;

public class GestoreBean extends ARuoloBean {

	private final Long idRadio = new Long(1);
	private final String nameRadio = "Gestore";
	
	private String coordinatePagamento;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((struttureGestite == null) ? 0 : struttureGestite.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof GestoreBean))
			return false;
		GestoreBean other = (GestoreBean) obj;
		if (struttureGestite == null) {
			if (other.struttureGestite != null)
				return false;
		} else if (!struttureGestite.equals(other.struttureGestite))
			return false;
		return true;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<StrutturaBean> struttureGestite = new ArrayList<StrutturaBean>();
	/**
	 * @return the struttureGestite
	 */
	public List<StrutturaBean> getStruttureGestite() {
		return struttureGestite;
	}
	/**
	 * @param struttureGestite the struttureGestite to set
	 */
	public void setStruttureGestite(List<StrutturaBean> struttureGestite) {
		this.struttureGestite = struttureGestite;
	}
	@Override
	public String toString() {
		return "GestoreBean [id="+id+"]";
	}
	/**
	 * @return the idradio
	 */
	public Long getIdradio() {
		return idRadio;
	}
	/**
	 * @return the nameradio
	 */
	public String getNameradio() {
		return nameRadio;
	}
	
	public GestoreBean() {
	}
	/**
	 * @return the coordinatePagamento
	 */
	public String getCoordinatePagamento() {
		return coordinatePagamento;
	}
	/**
	 * @param coordinatePagamento the coordinatePagamento to set
	 */
	public void setCoordinatePagamento(String coordinatePagamento) {
		this.coordinatePagamento = coordinatePagamento;
	}
	
}
	
	
