package com.ndovado.dominio.servizi;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistence.base.ServizioComuneDAO;



/**
 * 
 */
public class CatalogoServizi {

	private static ServizioComuneDAO scdao = new ServizioComuneDAO();
	private List<ServizioComune> serviziDisponibili;
	private static volatile CatalogoServizi instance;
	
	/**
	 * Default constructor
	 */
	public CatalogoServizi() {
		initList();
	}
	
	private void initList() {
		if (serviziDisponibili==null) {
			serviziDisponibili = new ArrayList<ServizioComune>();
		}
	}


	/**
	 * @return
	 */
	public static CatalogoServizi getInstance() {
		if (instance==null) {
			synchronized (CatalogoServizi.class) {
				if (instance==null)
					instance = new CatalogoServizi();
			}
		}
		return instance;
	}

	/**
	 * @param idServizio 
	 * @return
	 */
	public ServizioComune getServizioById(Long idServizio) {
		return scdao.get(idServizio);
	}

	/**
	 * @param nomeServizio 
	 * @return
	 */
	
	public List<ServizioComune> getListaServiziPerNome(String nome) {
		
		@SuppressWarnings("static-access")
		Session session = scdao.getSessionFactory().openSession();
		Query q = session.createQuery("from ServizioComune sc where sc.nomeServizio like :nome");
		q.setParameter("nome", "%"+nome+"%");

		@SuppressWarnings("unchecked")
		List<ServizioComune> elencoRisultati = q.list();
		session.close();
		return elencoRisultati;
	}
	
	/**
	 * @return
	 */
	
	public ServizioComune aggiungiServizio(String aNomeServizio) {
		// istanzio un nuovo servizio comune
		ServizioComune sc = new ServizioComune(aNomeServizio);
		// persisto il servizio comune su DB
		scdao.saveOrUpdate(sc);
		return sc;
	}
	
	public void rimuoviServizio(ServizioComune s) {
		if(s!=null) {
			// rimuovo il servizio dal DB
			scdao.delete(s.getId());
		}
	} 
	
	public static void main(String[] args) {
		
		CatalogoServizi cs = CatalogoServizi.getInstance();
		
		String chiave = "servizio";
		
		AppLogger.debug("Elenco servizi caricati:");
		for (ServizioComune servizioComune : cs.getServiziDisponibili()) {
			AppLogger.debug(servizioComune.toString());
		}
		List<ServizioComune> lsc = cs.getListaServiziPerNome(chiave);
		AppLogger.debug("Elenco servizi per la chiave di ricerca: "+chiave+", Elementi in lista:"+lsc.size());
		for (ServizioComune servizioComune : lsc) {
			AppLogger.debug(servizioComune.toString());
		}
	}

	/**
	 * @return the serviziDisponibili
	 */
	public List<ServizioComune> getServiziDisponibili() {
		return scdao.getAll();
	}

	/**
	 * @param serviziDisponibili the serviziDisponibili to set
	 */
	public void setServiziDisponibili(List<ServizioComune> serviziDisponibili) {
		this.serviziDisponibili = serviziDisponibili;
	}

}