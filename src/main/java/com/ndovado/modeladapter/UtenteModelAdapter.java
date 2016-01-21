package com.ndovado.modeladapter;

import com.ndovado.dominio.core.ARuolo;
import com.ndovado.dominio.core.Utente;
import com.ndovado.tecservices.persistenza.base.UtenteDAO;
import com.ndovado.webapp.beans.core.UtenteBean;

public class UtenteModelAdapter extends GenericModelAdapter<UtenteBean, Utente> {

	private static UtenteDAO udao = new UtenteDAO();
	private static UtenteModelAdapter instance;
	
	public static UtenteModelAdapter getInstance() {
		if (instance==null) {
			instance = new UtenteModelAdapter();
		}
		return instance;
	}
	
	@Override
	public Utente getModelFromBean(UtenteBean bean) {
		if (!bean.isNewBean()) {
			return udao.get(bean.getId());
		}
		return null;
	}
	
	public Utente createOrUpdateModelFromBean(UtenteBean bean) {
		Utente model;
		if (bean.isNewBean()) {
			// nuovo utente da creare
			model = new Utente();
			ARuolo ruolo = (bean.getRuolo() == bean.getValoreGestore()) ? ARuolo.getRuoloGestore() : ARuolo.getRuoloLocatario();
			model.setRuolo(ruolo);
		} else {
			// utente gia esistente
			model = udao.get(bean.getId());
		}
		fillModelFromBean(bean,model);
		return model;
	}

	@Override
	protected void fillModelFromBean(UtenteBean bean, Utente model) {
		model.setCognome(bean.getCognome());
		model.setNome(bean.getNome());
		model.setMail(bean.getMail());
		model.setPassword(bean.getPassword());
	}
}
