package com.ndovado.tecservices.mappers;

import com.ndovado.dominio.core.ARuolo;
import com.ndovado.dominio.core.Utente;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistenza.base.UtenteDAO;
import com.ndovado.webapp.beans.core.UtenteBean;

public class UserMapperTest {

	public static void main(String[] args) {
		
		Utente model = new Utente("Schiazza", "Antonio");
		model.setMail("antonio.schiazza@gmail.com");
		model.setPassword("aschi");
		model.setRuolo(ARuolo.getRuoloGestore());
		
		UtenteBean bean = UserMapper.getInstance().getBeanFromModel(model);
		
		AppLogger.debug("bean: "+bean.toString());
		
		UtenteDAO udao = new UtenteDAO();
		udao.saveOrUpdate(model);
		
		bean = UserMapper.getInstance().getBeanFromModel(model);
		
		bean.setCognome("Orsini");
		AppLogger.debug("bean: "+bean.toString());
		
		model = UserMapper.getInstance().getModelFromBean(bean);
		
		udao.saveOrUpdate(model);
		
		model = UserMapper.getInstance().getModelFromBean(bean);
		
		udao.saveOrUpdate(model);
		
		AppLogger.debug("model: "+model.toString());


	}

}
