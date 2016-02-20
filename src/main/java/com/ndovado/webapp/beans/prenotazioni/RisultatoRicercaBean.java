package com.ndovado.webapp.beans.prenotazioni;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.ManagedBean;

import com.ndovado.webapp.beans.core.CameraBean;
import com.ndovado.webapp.beans.core.StrutturaBean;

@ManagedBean(name="risultatoRicercaBean")
public class RisultatoRicercaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Set<CameraBean> camereLibere;
	private StrutturaBean struttura;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((camereLibere == null) ? 0 : camereLibere.hashCode());
		result = prime * result + ((struttura == null) ? 0 : struttura.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof RisultatoRicercaBean))
			return false;
		RisultatoRicercaBean other = (RisultatoRicercaBean) obj;
		if (camereLibere == null) {
			if (other.camereLibere != null)
				return false;
		} else if (!camereLibere.equals(other.camereLibere))
			return false;
		if (struttura == null) {
			if (other.struttura != null)
				return false;
		} else if (!struttura.equals(other.struttura))
			return false;
		return true;
	}

	public RisultatoRicercaBean() {
		camereLibere = new HashSet<CameraBean>();
	}

	/**
	 * @return the camere
	 */
	public Set<CameraBean> getCamereLibere() {
		return camereLibere;
	}

	/**
	 * @param camere the camere to set
	 */
	public void setCamereLibere(Set<CameraBean> camere) {
		this.camereLibere = camere;
	}

	/**
	 * @return the struttura
	 */
	public StrutturaBean getStruttura() {
		return struttura;
	}

	/**
	 * @param struttura the struttura to set
	 */
	public void setStruttura(StrutturaBean struttura) {
		this.struttura = struttura;
	}
	
}
