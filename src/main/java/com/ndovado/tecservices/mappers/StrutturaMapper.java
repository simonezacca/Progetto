package com.ndovado.tecservices.mappers;

import com.ndovado.dominio.core.Struttura;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.webapp.beans.core.StrutturaBean;

public class StrutturaMapper extends AGenericMapper<StrutturaBean, Struttura> {

	private static volatile StrutturaMapper instance;
	
	protected StrutturaMapper() {
		super();
	}
	
	public static StrutturaMapper getInstance() {
		if (instance==null) {
			synchronized (StrutturaMapper.class) {
				if (instance==null)
					instance = new StrutturaMapper();
			}
		}
		return instance;
	}
	@Override
	public StrutturaBean getBeanFromModel(Struttura model) {
		AppLogger.debug("model to convert: "+model.toString());
		StrutturaBean bean = mapper.map(model, StrutturaBean.class);
		return bean;
	}

	@Override
	public Struttura getModelFromBean(StrutturaBean bean) {
		Struttura model = mapper.map(bean, Struttura.class);
		return model;
	}

}
