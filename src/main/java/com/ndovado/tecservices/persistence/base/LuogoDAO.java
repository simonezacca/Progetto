package com.ndovado.tecservices.persistence.base;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.ndovado.dominio.core.Luogo;

public class LuogoDAO extends GenericDAO<Luogo>{
	
	public LuogoDAO() {
		super(Luogo.class);
	}
	
	 public List<Luogo> getAll() {
	    	Session session = getSessionFactory().openSession();
	    	session.beginTransaction();
	    	Criteria crit = session.createCriteria(Luogo.class);
			session.getTransaction().commit();
			@SuppressWarnings("unchecked")
			List<Luogo> list = crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			session.close();
			System.out.println("Successfully retrieved " + list.size() + " objects");
			return list;
	    }
}
