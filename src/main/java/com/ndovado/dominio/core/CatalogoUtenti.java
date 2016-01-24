package com.ndovado.dominio.core;

import java.util.ArrayList;
import java.util.List;

import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistence.base.UtenteDAO;

public class CatalogoUtenti {

	private static CatalogoUtenti instance;
	private static UtenteDAO udao = new UtenteDAO();
	
	// valutare l'idea di una mappa <String mail, Utente u>
	private List<Utente> elencoUtenti;
	
	protected CatalogoUtenti() {
		initList();
		populateList();
	}
	
	public CatalogoUtenti getInstance(){
		if (instance==null) {
			instance = new CatalogoUtenti();
		}
		return instance;
	}
	
	private void initList() {
		AppLogger.debug("Instanzio lista utenti");
		if (elencoUtenti == null) {
			elencoUtenti = new ArrayList<Utente>();
		}
	}
	
	private void populateList() {
		AppLogger.debug("Popolo lista utenti da DB");
		// ottengo dal servizio di persistenza l'elenco completo degli utenti presenti nel DB
		// e lo assegno ad elencoUtenti
		elencoUtenti = udao.getAll();
		AppLogger.debug("Lista popolata con "+elencoUtenti.size()+" utenti.");
	}
	
	public void rimuoviUtente(Utente u) {
		if (u!=null) {
			if (elencoUtenti.contains(u)) {
				// rimuovo prima l'utente dal DB
				udao.delete(u.getId());
				// rimuovo l'utente dalla lista in memoria
				elencoUtenti.remove(u);
			}
		}
	}
	
	public Utente creaNuovoUtente(String cognome, String nome) {
		return new Utente(cognome, nome);

	}
	
	public void salvaOAggiornaUtente(Utente u) {
		if (u!=null) {
			// controllare che l'email sia univoco prima dell'aggiunta in lista
			// aggiorno o salvo l'utente su DB
			udao.saveOrUpdate(u);
			// aggiungo l'utente alla lista in memoria
			elencoUtenti.add(u);
		}
	}
	
	public Utente getUtenteById(Long id) {
		return udao.get(id);
	}
	
	public Utente getUtentePerEmail(String email) {
		for (Utente utente : elencoUtenti) {
			if (utente.getMail().equals(email)) {
				return utente;
			}
		}
		return null;
	}
	
	public Boolean verificaCredenzialiUtente(String mail, String password) {
		Utente u = getUtentePerEmail(mail);
		if (u!=null) {
			// esiste un utente con la mail fornita
			if(u.getPassword().equals(password)) {
				// coppia mail e password corrette
				return true;
			}
		}
		// si potrebbe sollevare un'eccezzione custom del tipo utente non trovato
		return false;
	}
	
	public Boolean isMailEsistente(String mail) {
		for (Utente utente : elencoUtenti) {
			if (utente.getMail().equals(mail)) {
				return true;
			}
		}
		return false;
	}
	
}
