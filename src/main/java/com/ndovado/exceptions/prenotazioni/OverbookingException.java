package com.ndovado.exceptions.prenotazioni;

public class OverbookingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OverbookingException() {
	
	}
	
	public OverbookingException(String msg) {
		super(msg);
	}

}
