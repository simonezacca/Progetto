package com.ndovado.tecservices.persistenza.base;

import com.ndovado.dominio.prenotazioni.Prenotazione;

public class PrenotazioneDAO extends GenericDAO<Prenotazione> {
	
	public PrenotazioneDAO() {
		super(Prenotazione.class);
	}
}
