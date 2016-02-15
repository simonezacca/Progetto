package com.ndovado.webapp.beans.prenotazioni;

import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.ManagedBean;

import com.ndovado.webapp.beans.core.CameraBean;
import com.ndovado.webapp.beans.core.StrutturaBean;

@ManagedBean(name="risultatoRicercaBean")
public class RisultatoRicercaBean {

	private Set<CameraBean> camereLibere;
	private StrutturaBean struttura;
	
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
