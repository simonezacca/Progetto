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
			camereLibere.add(aCamera);
		}
	}
	
	/**
	 * 
	 */
	public Struttura getStruttura() {
		return this.struttura;
	}

}