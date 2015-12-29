package com.ndovado.tecservices.persistenza.base;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ServizioPersistenzaBase<T extends IPersistente>{

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		BasicConfigurator.configure();
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			return sessionFactory;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed. " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static <T> Long create(final T o){
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.save(o);
		session.getTransaction().commit();
		session.close();
		System.out.println("Oggetto creato " + o.toString());
		return ((IPersistente) o).getId();
    }


    public static <T> void delete(final Class<T> type, Long id){
    	Session session = getSessionFactory().openSession();
		session.beginTransaction();
		T u = (T) get(type,id);
		session.delete(u);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully deleted " + u.toString());
    }

    public static <T> T get(final Class<T> type, final Long id){
    	Session session = getSessionFactory().openSession();
    	session.beginTransaction();
		T c = (T) session.get(type, id);
		session.close();
		if (c!=null) {
			System.out.println("Successfully retrieved " + c.toString());
		}
		else {
			System.err.println("Object not found with id: " + id.toString());
			throw new NullPointerException("Object not found with id:" + id.toString());
		}
		return c;
    }

    /***/
    public static <T> T update(final T o)   {
    	Session session = getSessionFactory().openSession();
    	session.beginTransaction();
		session.merge(o);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully updated " + o.toString());
		return o;
    }

    public static <T> void saveOrUpdate(final T o){
    	Session session = getSessionFactory().openSession();
    	session.beginTransaction();
		session.saveOrUpdate(o);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully saved " + o.toString());
    }

    public static <T> List<T> getAll(final Class<T> type) {
    	Session session = getSessionFactory().openSession();
    	session.beginTransaction();
    	Criteria crit = session.createCriteria(type);
		session.getTransaction().commit();
		@SuppressWarnings("unchecked")
		List<T> list = crit.list();
		session.close();
		System.out.println("Successfully retrieved " + list.size() + " objects");
		return list;
    }
}
