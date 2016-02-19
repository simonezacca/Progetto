package com.ndovado.exceptions.utente;

public class MailEsistenteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MailEsistenteException() {
	}
	
	public MailEsistenteException(String msg) {
		super(msg);
	}

}
