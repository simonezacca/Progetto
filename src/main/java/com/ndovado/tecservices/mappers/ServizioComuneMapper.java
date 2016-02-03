package com.ndovado.tecservices.mappers;

import com.ndovado.dominio.servizi.ServizioComune;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.webapp.beans.servizi.ServizioComuneBean;

public class ServizioComuneMapper extends GenericMapper<ServizioComuneBean, ServizioComune> {

	private static ServizioComuneMapper instance;
	
	protected ServizioComuneMapper() {
		super();
	}
	
	public static ServizioComuneMapper getInstance() {
		if (instance==null) {
			instance = new ServizioComuneMapper();
		}
		return instance;
	}
	
	@Override
	public ServizioComuneBean getBeanFromModel(ServizioComune model) {
		AppLogger.debug("model to convert: "+model.toString());
		ServizioComuneBean bean = mapper.map(model, ServizioComuneBean.class);
		return bean;
	}

	@Override
	public ServizioComune getModelFromBean(ServizioComuneBean bean) {
		AppLogger.debug("bean to convert: "+bean.toString());
		ServizioComune model = mapper.map(bean, ServizioComune.class);
		return model;
	}

}
