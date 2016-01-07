package com.ndovado.controllers.utente;

import com.ndovado.dominio.core.ARuolo;
import com.ndovado.dominio.core.Utente;

public class TokenAutenticazioneUtente {
	private Utente utente;
	private ARuolo ruolo;
	private boolean tokenValido = false;
	
	public TokenAutenticazioneUtente(Utente aUtente, ARuolo aRuolo) {
		this.utente = aUtente;
		this.ruolo = aRuolo;
		this.tokenValido = true;
	}
	
	public Utente getUtente() { return this.utente; }
	
	public ARuolo getRuolo() { return this.ruolo; }
	
	public boolean isValido() { return this.tokenValido; }
	
	public void invalidaToken() { this.tokenValido = false; }
}
