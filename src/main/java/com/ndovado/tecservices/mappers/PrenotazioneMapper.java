package com.ndovado.tecservices.mappers;

import com.ndovado.dominio.prenotazioni.Prenotazione;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.webapp.beans.prenotazioni.PrenotazioneBean;

public class PrenotazioneMapper extends AGenericMapper<PrenotazioneBean, Prenotazione> {

	private static volatile PrenotazioneMapper instance;
	
	private PrenotazioneMapper() {
		super();
	}
	
	public static PrenotazioneMapper getInstance() {
		if (instance==null) {
			synchronized (PrenotazioneMapper.class) {
				if (instance==null)
					instance = new PrenotazioneMapper();
			}
		}
		return instance;
	}
	
	@Override
	public PrenotazioneBean getBeanFromModel(Prenotazione model) {
		AppLogger.debug("model to convert: "+model.toString());
		PrenotazioneBean pbean = mapper.map(model, PrenotazioneBean.class);
		return pbean;
	}

	@Override
	public Prenotazione getModelFromBean(PrenotazioneBean bean) {
		AppLogger.debug("bean to convert: "+bean.toString());
		Prenotazione pmodel = mapper.map(bean, Prenotazione.class);
		return pmodel;
	}

}
