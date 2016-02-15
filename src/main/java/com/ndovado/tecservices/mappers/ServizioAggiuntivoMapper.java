package com.ndovado.tecservices.mappers;

import com.ndovado.dominio.servizi.ServizioAggiuntivo;
import com.ndovado.webapp.beans.servizi.ServizioAggiuntivoBean;

public class ServizioAggiuntivoMapper extends AGenericMapper<ServizioAggiuntivoBean, ServizioAggiuntivo> {

	private static volatile ServizioAggiuntivoMapper instance;
	
	private ServizioAggiuntivoMapper() {}
	
	public static ServizioAggiuntivoMapper getInstance() {
		if (instance==null) {
			synchronized (ServizioAggiuntivoMapper.class) {
				if (instance==null)
					instance = new ServizioAggiuntivoMapper();
			}
		}
		return instance;
	}
	@Override
	public ServizioAggiuntivoBean getBeanFromModel(ServizioAggiuntivo model) {
		ServizioAggiuntivoBean bean = mapper.map(model, ServizioAggiuntivoBean.class);
		return bean;
	}

	@Override
	public ServizioAggiuntivo getModelFromBean(ServizioAggiuntivoBean bean) {
		ServizioAggiuntivo model = mapper.map(bean, ServizioAggiuntivo.class);
		return model;
	}

}
