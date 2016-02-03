package com.ndovado.webapp.beanconverters;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.ndovado.dominio.core.CatalogoLuogo;
import com.ndovado.dominio.core.Luogo;
import com.ndovado.tecservices.mappers.LuogoMapper;
import com.ndovado.webapp.beans.core.LuogoBean;

@ManagedBean(name="luogoBeanConverter")
//@FacesConverter("com.ndovado.webapp.beanconverters.userroleconveter")
public class LuogoBeanConverter implements Converter{

	private CatalogoLuogo clmodel = CatalogoLuogo.getInstance();
	private LuogoMapper lmapper = LuogoMapper.getInstance();
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// input string id luogo bean
		Long idLuogo = Long.parseLong(arg2);
		Luogo lmodel = clmodel.getLuogoById(idLuogo);
		return lmapper.getBeanFromModel(lmodel);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		LuogoBean lb = (LuogoBean) arg2; 
		return lb.getId().toString();
	}

}
