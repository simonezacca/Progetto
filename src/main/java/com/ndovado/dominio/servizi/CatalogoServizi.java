package com.ndovado.dominio.servizi;

import java.util.*;

import org.apache.taglibs.standard.tag.common.fmt.SetTimeZoneSupport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistence.base.ServizioComuneDAO;



/**
 * 
 */
public class CatalogoServizi {

	private static ServizioComuneDAO scdao = new ServizioComuneDAO();
	private List<ServizioComune> serviziDisponibili;
	private static CatalogoServizi instance;
	
	/**
	 * Default constructor
	 */
	private CatalogoServizi() {
		initList();
//		populateList();
	}
	
	private void initList() {
		if (serviziDisponibili==null) {
			serviziDisponibili = new ArrayList<ServizioComune>();
		}
	}
	
//	@SuppressWarnings("unchecked")
//	private void populateList() {
//		AppLogger.debug("Popolo lista servizi da DB");
//		SessionFactory sf = scdao.getSessionFactory();
//		Session session = sf.openSession();
//		
//		Query q = session.createQuery("from ServizioComune");
//		
//		this.serviziDisponibili = q.list();
//		session.close();
//		AppLogger.debug("Lista popolata con "+serviziDisponibili.size()+" elementi.");
//	}
	
	


	/**
	 * @return
	 */
	public static CatalogoServizi getInstance() {
		if (instance==null) {
			instance = new CatalogoServizi();
		}
		return instance;
	}

	/**
	 * @param idServizio 
	 * @return
	 */
	public ServizioComune getServizioById(Long idServizio) {
		return scdao.get(idServizio);
//		for (ServizioComune servizio : getServiziDisponibili()) {
//			if (servizio.getId() == idServizio) {
//				return servizio;
//			}
//		}
//		return null;
	}

	/**
	 * @param nomeServizio 
	 * @return
	 */
	
	public List<ServizioComune> getListaServiziPerNome(String nome) {
		
		Session session = scdao.getSessionFactory().openSession();
		Query q = session.createQuery("from ServizioComune sc where sc.nomeServizio like :nome");
		q.setParameter("nome", "%"+nome+"%");

		List<ServizioComune> elencoRisultati = q.list();
		session.close();
		return elencoRisultati;
		
//		for (ServizioComune servizioComune : getServiziDisponibili()) {
//			if (servizioComune.getNomeServizio().contains(nome)) {
//				elencoRisultati.add(servizioComune);
//			}
//		}
//		return elencoRisultati;
	}
	
	/**
	 * @return
	 */
	
	public ServizioComune aggiungiServizio(String aNomeServizio) {
		// istanzio un nuovo servizio comune
		ServizioComune sc = new ServizioComune(aNomeServizio);
		// persisto il servizio comune su DB
		scdao.saveOrUpdate(sc);
		//aggiungo il nuovo servizio alla lista
		//getServiziDisponibili().add(sc);
		return sc;
	}
	
	public void rimuoviServizio(ServizioComune s) {
		if(s!=null) {
			// rimuovo il servizio dal DB
			scdao.delete(s.getId());
			// rimuovo il servizio dalla lista in memoria
			//serviziDisponibili.remove(s);
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
//		return serviziDisponibili;
	}

	/**
	 * @param serviziDisponibili the serviziDisponibili to set
	 */
	public void setServiziDisponibili(List<ServizioComune> serviziDisponibili) {
		this.serviziDisponibili = serviziDisponibili;
	}

}