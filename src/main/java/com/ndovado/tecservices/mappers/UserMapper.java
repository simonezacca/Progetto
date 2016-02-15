package com.ndovado.tecservices.mappers;

import com.ndovado.dominio.core.Utente;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.webapp.beans.core.UtenteBean;

public class UserMapper extends AGenericMapper<UtenteBean, Utente> {

	private static volatile UserMapper instance;
	
	protected UserMapper() {
		super();
	}
	
	public static UserMapper getInstance() {
		if (instance==null) {
			synchronized (UserMapper.class) {
				if (instance==null)
					instance = new UserMapper();
			}
		}
		return instance;
	}
	@Override
	public UtenteBean getBeanFromModel(Utente model) {
		AppLogger.debug("model to convert: "+model);
		UtenteBean bean = mapper.map(model, UtenteBean.class);
		AppLogger.debug("bean converted: "+bean);
		return bean;
	}

	@Override
	public Utente getModelFromBean(UtenteBean bean) {
		Utente model = mapper.map(bean, Utente.class);
		return model;
	}


}
