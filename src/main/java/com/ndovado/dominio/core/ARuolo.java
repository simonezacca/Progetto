package com.ndovado.dominio.core;

/**
 * 
 */

public abstract class ARuolo {

	/**
	 * Default constructor
	 */
	public ARuolo() {
	}

	/**
	 * @return
	 */
	public static Gestore getRuoloGestore() {
		return new Gestore();
	}

	/**
	 * @return
	 */
	public static Locatario getRuoloLocatario() {
		return new Locatario();
	}

}