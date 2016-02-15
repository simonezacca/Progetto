package com.ndovado.tecservices.mappers;

import com.ndovado.dominio.pagamenti.Pagamento;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.webapp.beans.pagamenti.PagamentoBean;

public class PagamentoMapper extends AGenericMapper<PagamentoBean, Pagamento> {

	private static volatile PagamentoMapper instance;
	
	private PagamentoMapper() {
		
	}
	
	public static PagamentoMapper getInstance() {
		if (instance==null) {
			synchronized (PagamentoMapper.class) {
				if (instance==null)
					instance = new PagamentoMapper();
			}
		}
		return instance;
	}
	
	@Override
	public PagamentoBean getBeanFromModel(Pagamento model) {
		AppLogger.debug("model to convert: "+model.toString());
		PagamentoBean pbean = mapper.map(model, PagamentoBean.class);
		return pbean;
	}

	@Override
	public Pagamento getModelFromBean(PagamentoBean bean) {
		AppLogger.debug("bean to convert: "+bean.toString());
		Pagamento pmodel = mapper.map(bean, Pagamento.class);
		return pmodel;
	}

}
