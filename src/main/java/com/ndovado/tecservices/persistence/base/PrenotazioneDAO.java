package com.ndovado.tecservices.persistence.base;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import com.ndovado.dominio.prenotazioni.Prenotazione;

public class PrenotazioneDAO extends AGenericDAO<Prenotazione> {

	public PrenotazioneDAO() {
		super(Prenotazione.class);
	}

	public List<Prenotazione> getAll() {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Prenotazione.class);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		session.getTransaction().commit();
		@SuppressWarnings("unchecked")
		List<Prenotazione> list = crit.list();
		session.close();
		System.out.println("Successfully retrieved " + list.size() + " objects");
		return list;
	}
}
