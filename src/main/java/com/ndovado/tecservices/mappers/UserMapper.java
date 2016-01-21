package com.ndovado.tecservices.mappers;

import com.ndovado.dominio.core.Utente;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.webapp.beans.core.UtenteBean;

public class UserMapper extends GenericMapper<UtenteBean, Utente> {

	private static UserMapper instance;
	
	protected UserMapper() {
		super();
	}
	
	public static UserMapper getInstance() {
		if (instance==null) {
			instance = new UserMapper();
		}
		return instance;
	}
	@Override
	public UtenteBean getBeanFromModel(Utente model) {
		AppLogger.debug("model to convert: "+model.toString());
		UtenteBean bean = mapper.map(model, UtenteBean.class);
		return bean;
	}

	@Override
	public Utente getModelFromBean(UtenteBean bean) {
		Utente model = mapper.map(bean, Utente.class);
		return model;
	}


}
