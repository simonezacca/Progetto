package com.ndovado.helpers.core;

import com.ndovado.bridge.IBeanModelBridge;
import com.ndovado.dominio.core.Luogo;
import com.ndovado.webapp.beans.core.LuogoBean;

public class LuogoHelper implements IBeanModelBridge<LuogoBean, Luogo>{

	
	private static LuogoHelper instance;
	
	public static LuogoHelper getInstance() {
		if (instance==null) {
			instance = new LuogoHelper();
		}
		return instance;
	}
	
	public static LuogoBean creaLuogoBeanDaModel(Luogo l) {
		return getInstance().createBeanByModel(l);
	}
	@Override
	public LuogoBean createBeanByModel(Luogo model) {
		return new LuogoBean(model);
	}

	@Override
	public Luogo createModelByBean(LuogoBean bean) {
		Luogo l = new Luogo(bean.getNomeComune());
		l.setId(bean.getIdLuogo());
		l.setCAP(bean.getCap());
		l.setProvincia(bean.getProvincia());
		return l;
	}

	@Override
	public LuogoBean updateBeanByModel(Luogo model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Luogo updateModelByBean(LuogoBean bean) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
