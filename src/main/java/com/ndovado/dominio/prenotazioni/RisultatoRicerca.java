package com.ndovado.dominio.prenotazioni;

import java.util.*;

import com.ndovado.dominio.core.Camera;
import com.ndovado.dominio.core.Struttura;

/**
 * 
 */
public class RisultatoRicerca {

	/**
	 * Default constructor
	 */
	public RisultatoRicerca(Struttura s) {
		if (s!=null) {
			struttura = s;
		}
		camereLibere = new HashSet<Camera>();
	}

	/**
	 * 
	 */
	private Struttura struttura;

	/**
	 * 
	 */
	private Set<Camera> camereLibere;

	/**
	 * @param aCamera
	 */
	public void addCameraDisponibile(Camera aCamera) {
		if (aCamera!= null) {
			getCamereLibere().add(aCamera);
		}
	}
	
	/**
	 * 
	 */
	public Struttura getStruttura() {
		return this.struttura;
	}

	/**
	 * @return the camereLibere
	 */
	public Set<Camera> getCamereLibere() {
		return camereLibere;
	}

	/**
	 * @param camereLibere the camereLibere to set
	 */
	public void setCamereLibere(Set<Camera> camereLibere) {
		this.camereLibere = camereLibere;
	}

}