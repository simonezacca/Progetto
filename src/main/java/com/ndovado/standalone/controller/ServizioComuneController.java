package com.ndovado.standalone.controller;

import java.util.List;

import com.ndovado.dominio.servizi.ServizioComune;
import com.ndovado.exceptions.servizi.CancellazioneServizioException;
import com.ndovado.exceptions.servizi.SalvataggioServizioException;
import com.ndovado.tecservices.persistence.base.ServizioComuneDAO;

public class ServizioComuneController {
	
	private ServizioComuneDAO scdao = new ServizioComuneDAO();
	
	
	public List<ServizioComune> getElencoServiziComune() {
		return scdao.getAll();
	}
	
	public ServizioComune salvaServizioComune(ServizioComune sc) throws SalvataggioServizioException {
		try {
			scdao.saveOrUpdate(sc);
			return sc;
		} catch (Exception e) {
			throw new SalvataggioServizioException("Impossibile salvare il servizio.");
		}
	}
	
	public ServizioComune aggiornaServizioComune(ServizioComune sc) throws SalvataggioServizioException {
		try {
			scdao.saveOrUpdate(sc);
			return sc;
		} catch (Exception e) {
			throw new SalvataggioServizioException("Impossibile aggiornare il servizio.");
		}
	}
	
	public void rimuoviServizioComune(ServizioComune sc) throws CancellazioneServizioException {
		try {
			scdao.delete(sc.getId());
		} catch(Exception e) {
			if (e instanceof org.hibernate.exception.ConstraintViolationException) {
				throw new CancellazioneServizioException("Impossibile cancellare il servizio perchè collegato a strutture esistenti");
			}
			else {
				throw new CancellazioneServizioException("Impossibile cancellare il servizio");
			}
		}
	}
	
	public ServizioComune getServizioComuneById(Long id) {
		return scdao.get(id);
	}

}
