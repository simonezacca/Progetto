package com.ndovado.tecservices.mappers;

import com.ndovado.dominio.core.Luogo;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.webapp.beans.core.LuogoBean;

public class LuogoMapper extends AGenericMapper<LuogoBean, Luogo> {

	private static volatile LuogoMapper instance;
	
	protected LuogoMapper() {
		super();
	}
	
	public static LuogoMapper getInstance() {
		if (instance==null) {
			synchronized (LuogoMapper.class) {
				if (instance==null)
					instance = new LuogoMapper();
			}
		}
		return instance;
	}
	@Override
	public LuogoBean getBeanFromModel(Luogo model) {
		AppLogger.debug("model to convert: "+model.toString());
		LuogoBean bean = mapper.map(model, LuogoBean.class);
		return bean;
	}

	@Override
	public Luogo getModelFromBean(LuogoBean bean) {
		AppLogger.debug("bean to convert: "+bean.toString());
		Luogo model = mapper.map(bean, Luogo.class);
		return model;
	}

}
