package com.ndovado.modeladapter;

import com.ndovado.dominio.core.Luogo;
import com.ndovado.tecservices.persistenza.base.LuogoDAO;
import com.ndovado.webapp.beans.core.LuogoBean;

public class LuogoModelAdapter extends GenericModelAdapter<LuogoBean, Luogo> {
	
	private static LuogoDAO ldao = new LuogoDAO();
	
	@Override
	public Luogo getModelFromBean(LuogoBean bean) {
		if (!bean.isNewBean()) {
			return ldao.get(bean.getId());
		}
		return null;
	}

	@Override
	protected void fillModelFromBean(LuogoBean bean, Luogo model) {
		model.setNomeComune(bean.getNomeComune());
		model.setCAP(bean.getCAP());
		model.setProvincia(bean.getProvincia());
	}
	
	public Luogo createOrUpdateModelFromBean(LuogoBean bean) {
		Luogo model;
		if (bean.isNewBean()) {
			model = new Luogo(bean.getNomeComune());
		} else {
			model = ldao.get(bean.getId());
		}
		fillModelFromBean(bean, model);
		return model;
	}

}
