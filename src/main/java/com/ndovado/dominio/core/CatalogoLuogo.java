package com.ndovado.dominio.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ndovado.tecservices.persistenza.base.LuogoDAO;

public class CatalogoLuogo {

	private static CatalogoLuogo instance;
	private static LuogoDAO ldao = new LuogoDAO();
	
	private Map<Long,Luogo> mappaLuoghi;
	
	private CatalogoLuogo() {
		initSet();
		initListaLuoghi();
	}
	
	private void initSet() {
		if (mappaLuoghi==null) {
			mappaLuoghi = new HashMap<Long,Luogo>();
		}
	}
	
	private void initListaLuoghi() {
		SessionFactory sf = ldao.getSessionFactory();
		Session session = sf.openSession();
		
		Query q = session.createQuery("from Luogo");
		@SuppressWarnings("unchecked")
		List<Luogo> elencoLuoghi = q.list();
		for (Luogo luogo : elencoLuoghi) {
			mappaLuoghi.put(luogo.getId(), luogo);
		}
		session.close();
	}
	
	public static CatalogoLuogo getInstance() {
		if (instance==null) {
			instance = new CatalogoLuogo();
		}
		return instance;
	}
	
	public Luogo getListaLuoghiPerNome(String nome) {
		// TODO implementare ricerca per stringa possibilmente senza DB
		return null;
		
	}
	
	public Luogo getLuogo(Long id) {
		return mappaLuoghi.get(id);
	}
	
	public Luogo addLuogo(Luogo l) {
		
	}
	
	public void removeLuogo(Luogo l) {
		if (l!=null) {
			// rimuovo prima il Luogo dal DB 
			ldao.delete(l.getId());
			// rimuovo 
		}
	}
}
