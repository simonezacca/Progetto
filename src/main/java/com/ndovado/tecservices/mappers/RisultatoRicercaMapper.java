package com.ndovado.tecservices.mappers;

import com.ndovado.dominio.prenotazioni.RisultatoRicerca;
import com.ndovado.webapp.beans.prenotazioni.RisultatoRicercaBean;

public class RisultatoRicercaMapper extends AGenericMapper<RisultatoRicercaBean, RisultatoRicerca> {

	private static volatile RisultatoRicercaMapper instance;
	
	public RisultatoRicercaMapper() {
		
	}
	
	public static RisultatoRicercaMapper getInstance() {
		if (instance==null) {
			synchronized (RisultatoRicercaMapper.class) {
				if (instance==null)
					instance = new RisultatoRicercaMapper();
			}
		}
		return instance;
	}
	
	@Override
	public RisultatoRicercaBean getBeanFromModel(RisultatoRicerca model) {
		RisultatoRicercaBean rrbean = mapper.map(model, RisultatoRicercaBean.class);
		return rrbean;
	}

	@Override
	public RisultatoRicerca getModelFromBean(RisultatoRicercaBean bean) {
		RisultatoRicerca rrmodel = mapper.map(bean, RisultatoRicerca.class);
		return rrmodel;
	}

}
