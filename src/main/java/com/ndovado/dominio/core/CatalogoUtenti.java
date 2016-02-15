package com.ndovado.dominio.core;

import java.util.ArrayList;
import java.util.List;

import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistence.base.UtenteDAO;

public class CatalogoUtenti {

	private static volatile CatalogoUtenti instance;
	private static UtenteDAO udao = new UtenteDAO();

	private List<Utente> elencoUtenti;

	protected CatalogoUtenti() {
		initList();
	}

	public static CatalogoUtenti getInstance() {
		if (instance == null) {
			synchronized (CatalogoUtenti.class) {
				if (instance == null)
					instance = new CatalogoUtenti();
			}
		}
		return instance;
	}

	private void initList() {
		AppLogger.debug("Instanzio lista utenti");
		if (elencoUtenti == null) {
			elencoUtenti = new ArrayList<Utente>();
		}
	}

	public void rimuoviUtente(Utente u) {
		if (u != null) {
			if (elencoUtenti.contains(u)) {
				// rimuovo prima l'utente dal DB
				udao.delete(u.getId());
			}
		}
	}

	public Utente creaNuovoUtente(String cognome, String nome) {
		return new Utente(cognome, nome);

	}

	public void salvaOAggiornaUtente(Utente u) {
		if (u != null) {
			// controllare che l'email sia univoco prima dell'aggiunta in lista
			// aggiorno o salvo l'utente su DB
			udao.saveOrUpdate(u);
			// aggiungo l'utente alla lista in memoria
			// elencoUtenti.add(u);
		}
	}

	public Utente getUtenteById(Long id) {
		return udao.get(id);
	}

	public Utente getUtentePerEmail(String email) {
		return udao.cercaUtentePerMail(email);
	}

	public Boolean verificaCredenzialiUtente(String mail, String password) {
		Utente u = getUtentePerEmail(mail);
		if (u != null) {
			// esiste un utente con la mail fornita
			if (u.getPassword().equals(password)) {
				// coppia mail e password corrette
				return true;
			}
		}
		// si potrebbe sollevare un'eccezzione custom del tipo utente non
		// trovato
		return false;
	}

	public Boolean isMailEsistente(String mail) {
		return udao.cercaUtentePerMail(mail) != null;
	}

	public List<Utente> getAllUtenti() {
		return udao.getAll();
	}

	public static void main(String[] args) {

		CatalogoUtenti cu = CatalogoUtenti.getInstance();

		Utente umodel = new Utente("Orsini", "Federico");
		umodel.setMail("orsini@mail.com");
		umodel.setPassword("ciao");
		umodel.setRuolo(ARuolo.getRuoloLocatario());

		cu.salvaOAggiornaUtente(umodel);

		AppLogger.debug("Utente salvato: " + umodel);

		cu.rimuoviUtente(umodel);
		AppLogger.debug("Utente eliminato");
	}

}
