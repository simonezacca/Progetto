package com.ndovado.dominio.core;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import com.ndovado.tecservices.persistence.base.LuogoDAO;

public class CatalogoLuogo {

	private static volatile CatalogoLuogo instance;
	private static LuogoDAO ldao = new LuogoDAO();
	
	private CatalogoLuogo() {
	}
	
	public static CatalogoLuogo getInstance() {
		if (instance==null) {
			synchronized (CatalogoLuogo.class) {
				if(instance==null)
					instance = new CatalogoLuogo();
			}
		}
		return instance;
	}
	
	public Luogo getLuogoById(Long id) {
		return ldao.get(id);
	}
	
	public Luogo getLuogo(Luogo l) {
		return ldao.get(l.getId());
	}
	
	public List<Luogo> getListaLuoghiPerNome(String nome) {
		
		@SuppressWarnings("static-access")
		Session session = ldao.getSessionFactory().openSession();
		Query q = session.createQuery("from Luogo where nome like :nome");
		q.setParameter("nome", "%"+nome+"%");
		@SuppressWarnings("unchecked")
		List<Luogo> elencoRisultati = q.list();
		session.close();
		return elencoRisultati;
	}
	
	@SuppressWarnings("static-access")
	public List<Luogo> getListaLuoghiPerProvincia(String provincia) {
		
		Session session = ldao.getSessionFactory().openSession();
		Query q = session.createQuery("from Luogo where provincia = :prov");
		q.setParameter("prov", provincia);
		
		@SuppressWarnings("unchecked")
		List<Luogo> elencoRisultati = q.list();
		session.close();
		return elencoRisultati;

	}
	
	public Luogo addLuogo(Luogo l) {
		if (l!=null) {
			ldao.saveOrUpdate(l);
		}
		return l;
	}
	
	public void removeLuogo(Luogo l) {
		if (l!=null) {
			// rimuovo il Luogo dal DB 
			ldao.delete(l.getId());
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
		
		@SuppressWarnings("unused")
		Luogo l = cl.getListaLuoghiPerNome("Chieti").get(0);
	}
	
	public List<Luogo> getAllLuogo() {
		return ldao.getAll();
	}
	
	public List<String> getListaTutteProvinceStrings() {
		
		@SuppressWarnings("static-access")
		Session session = ldao.getSessionFactory().openSession();
		Query q = session.createQuery("select distinct l.provincia from Luogo l order by l.provincia asc");
		@SuppressWarnings("unchecked")
		List<String> strings = q.list();
		session.close();
		return strings;
	}
}
