package com.ndovado.standalone.controller;

import java.util.List;

import com.ndovado.dominio.servizi.ServizioComune;
import com.ndovado.tecservices.persistence.base.ServizioComuneDAO;

public class ServizioComuneController {
	
	private ServizioComuneDAO scdao = new ServizioComuneDAO();
	
	
	public List<ServizioComune> getElencoServiziComune() {
		return scdao.getAll();
	}
	
	public ServizioComune salvaServizioComune(ServizioComune sc) {
		scdao.saveOrUpdate(sc);
		return sc;
	}
	
	public ServizioComune aggiornaServizioComune(ServizioComune sc) {
		scdao.saveOrUpdate(sc);
		return sc;
	}
	
	public void rimuoviServizioComune(ServizioComune sc) {
		scdao.delete(sc.getId());
	}
	
	public ServizioComune getServizioComuneById(Long id) {
		return scdao.get(id);
	}

}
