package com.ndovado.dominio.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistence.base.LuogoDAO;

public class CatalogoLuogo {

	private static CatalogoLuogo instance;
	private static LuogoDAO ldao = new LuogoDAO();
	
	private Map<Long,Luogo> mappaLuoghi;
	
	private CatalogoLuogo() {
		initSet();
		initListaLuoghi();
	}
	
	private void initSet() {
		AppLogger.debug("Istanzio mappa luoghi");
		if (mappaLuoghi==null) {
			mappaLuoghi = new HashMap<Long,Luogo>();
		}
	}
	
	private void initListaLuoghi() {
		AppLogger.debug("Popolo mappa luoghi da DB");
		// ottento dal servizio di persistenza l'elenco completo dei luoghi presenti nel DB
		List<Luogo> elencoLuoghi = ldao.getAll();
		// ciclo sulla lista per aggiungerli alla mappa
		for (Luogo luogo : elencoLuoghi) {
			mappaLuoghi.put(luogo.getId(), luogo);
		}
	}
	
	public static CatalogoLuogo getInstance() {
		if (instance==null) {
			instance = new CatalogoLuogo();
		}
		return instance;
	}
	
	public Luogo getLuogoById(Long id) {
		return mappaLuoghi.get(id);
	}
	
	public Luogo getLuogo(Luogo l) {
		if (mappaLuoghi.containsValue(l)) {
			Long id = l.getId();
			return mappaLuoghi.get(id);
		}
		return null;
	}
	
	public List<Luogo> getListaLuoghiPerNome(String nome) {
		List<Luogo> elencoRisultati = new ArrayList<Luogo>();
		for (Map.Entry<Long, Luogo> entry : mappaLuoghi.entrySet()) {
			if(entry.getValue().getNomeComune().contains(nome)) {
				elencoRisultati.add(entry.getValue());
            }
		}
		return elencoRisultati;
	}
	
	public List<Luogo> getListaLuoghiPerProvincia(String provincia) {
		List<Luogo> elencoRisultati = new ArrayList<Luogo>();
		for (Map.Entry<Long, Luogo> entry : mappaLuoghi.entrySet()) {
			if(entry.getValue().getProvincia().equalsIgnoreCase(provincia)){
				elencoRisultati.add(entry.getValue());
            }
		}
		return elencoRisultati;
	}
	
	public Luogo addLuogo(Luogo l) {
		if (l!=null) {
			if (!mappaLuoghi.containsKey(l.getId())) {
				// persisto l'instanza di luogo su DB
				ldao.saveOrUpdate(l);
				// aggiungo il luogo alla mappa
				mappaLuoghi.put(l.getId(), l);
				AppLogger.debug("Luogo salvato su DB con id="+l.getId());
			} else
				AppLogger.debug("Luogo già presente nella mappa");
		}
		return l;
	}
	
	public void removeLuogo(Luogo l) {
		if (l!=null) {
			// rimuovo prima il Luogo dal DB 
			ldao.delete(l.getId());
			// rimuovo il luogo dalla mappa
			mappaLuoghi.remove(l.getId());
		}
	}
	
	public static void main(String[] args) {
		
		CatalogoLuogo cl = CatalogoLuogo.getInstance();
		
		String provincia = "CH";
		String nomeComune = "ieti";
		
		List<Luogo> ricerca = cl.getListaLuoghiPerProvincia(provincia);
		System.out.println("Elenco luoghi per la provincia "+provincia);
		for (Luogo luogo : ricerca) {
			System.out.println(luogo);
		}
		
		ricerca = cl.getListaLuoghiPerNome(nomeComune);
		System.out.println("Elenco luoghi per il nomeComune "+nomeComune);
		for (Luogo luogo : ricerca) {
			System.out.println(luogo);
		}
		
		Luogo l = cl.getListaLuoghiPerNome("Chieti").get(0);
		Struttura strutturaInLuogo = l.getStruttureInLuogo().get(0);
		
		System.out.println(strutturaInLuogo);
	}
	
	public List<Luogo> getAllLuogo() {
		List<Luogo> elencoRisultati = new ArrayList<Luogo>();
		for (Map.Entry<Long, Luogo> entry : mappaLuoghi.entrySet()) {
				elencoRisultati.add(entry.getValue());
		}
		return elencoRisultati;
	}
	
	public List<String> getListaTutteProvinceStrings() {
		List<String> strings = new ArrayList<String>();
		for (Map.Entry<Long, Luogo> entry : mappaLuoghi.entrySet()) {
			String provincia = entry.getValue().getProvincia();
			if (!strings.contains(provincia)) {
				strings.add(provincia);
			}
		}
		return strings;
	}
}
