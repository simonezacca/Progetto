package com.ndovado.dominio.servizi;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ndovado.tecservices.persistenza.base.ServizioPersistenzaBase;



/**
 * 
 */
public class CatalogoServizi {

	private static SessionFactory sf = ServizioPersistenzaBase.getSessionFactory();
	
	/**
	 * Default constructor
	 */
	protected CatalogoServizi() {
		serviziDisponibili = new HashSet<ServizioComune>();
	}

	/**
	 * 
	 */
	private static CatalogoServizi istance;

	/**
	 * 
	 */
	private Set<ServizioComune> serviziDisponibili;

	/**
	 * @return
	 */
	public static CatalogoServizi getIstance() {
		if (istance==null) {
			istance = new CatalogoServizi();
		}
		return istance;
	}

	/**
	 * @param idServizio 
	 * @return
	 */
	public ServizioComune getServizio(Long idServizio) {
		for (ServizioComune servizio : serviziDisponibili) {
			if (servizio.getId() == idServizio) {
				return servizio;
			}
		}
		return null;
	}

	/**
	 * @param nomeServizio 
	 * @return
	 */
	public List<ServizioComune> cercaServizioPerNome(String nomeServizio) {
		
		Session session = sf.openSession();
		Query query = session.getNamedQuery("cercaServizioPerNome").setString("nome", "%"+nomeServizio+"%");

		@SuppressWarnings("unchecked")
		List<ServizioComune> elenco = query.list();
		session.close();
		return elenco;
	}

	/**
	 * @return
	 */
	public ServizioComune creaNuovoServizio(String aNomeServizio) {
		ServizioComune sc = new ServizioComune(aNomeServizio);
		ServizioPersistenzaBase.<ServizioComune>saveOrUpdate(sc);
		return sc;
	}

}