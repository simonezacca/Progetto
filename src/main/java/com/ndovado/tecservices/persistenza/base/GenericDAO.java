package com.ndovado.tecservices.persistenza.base;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class GenericDAO<T> {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private Class<T> entityClass;

	 public GenericDAO(Class<T> entityClass) {
		   this.entityClass = entityClass;
	 }

	private static SessionFactory buildSessionFactory() {
//		BasicConfigurator.configure();
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
	
	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}
	
	public Long create(T o){
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.save(o);
		session.getTransaction().commit();
		session.close();
		System.out.println("Oggetto creato " + o.toString());
		return ((IPersistente) o).getId();
    }


    public void delete(Long id){
    	Session session = getSessionFactory().openSession();
		session.beginTransaction();
		T u = (T) get(id);
		session.delete(u);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully deleted " + u.toString());
    }

    public T get(final Long id){
    	Session session = getSessionFactory().openSession();
    	session.beginTransaction();
		T c = (T) session.get(entityClass, id);
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
    public T update(final T o)   {
    	Session session = getSessionFactory().openSession();
    	session.beginTransaction();
		session.merge(o);
		//session.update(o);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully updated " + o.toString());
		return o;
    }

    public void saveOrUpdate(final T o){
    	Session session = getSessionFactory().openSession();
    	session.beginTransaction();
		session.saveOrUpdate(o);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully saved " + o.toString());
    }

    public List<T> getAll() {
    	Session session = getSessionFactory().openSession();
    	session.beginTransaction();
    	Criteria crit = session.createCriteria(entityClass);
		session.getTransaction().commit();
		@SuppressWarnings("unchecked")
		List<T> list = crit.list();
		session.close();
		System.out.println("Successfully retrieved " + list.size() + " objects");
		return list;
    }

	@SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
		Session session = getSessionFactory().openSession();
    	session.beginTransaction();
	    T result = null;
	    try {
		     Query query = session.getNamedQuery(namedQuery);
		     // Method that will populate parameters if they are passed not null and empty
		     if (parameters != null && !parameters.isEmpty()) {
		     populateQueryParameters(query, parameters);
		     if(query.list().size()>0)
		    	 result = (T) query.list().get(0);
		     }
	    } catch (Exception e) {
	     System.out.println("Error while running query: " + e.getMessage());
	     e.printStackTrace();
	    }
	    return result;
	}

	private void populateQueryParameters(Query query, Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}
}
