package com.ndovado.webapp.beanconverters;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.ndovado.dominio.servizi.CatalogoServizi;
import com.ndovado.dominio.servizi.ServizioComune;
import com.ndovado.tecservices.mappers.ServizioComuneMapper;
import com.ndovado.webapp.beans.servizi.ServizioComuneBean;

@ManagedBean(name="servizioComuneBeanConverter")
public class ServizioComuneBeanConverter implements Converter { // NO_UCD (unused code)
	
	private ServizioComuneMapper scmapper = ServizioComuneMapper.getInstance();
	private CatalogoServizi csmodel = CatalogoServizi.getInstance();

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Long idServizio = Long.parseLong(arg2);
		ServizioComune scmodel = csmodel.getServizioById(idServizio);
		return scmapper.getBeanFromModel(scmodel);
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		ServizioComuneBean scbean = (ServizioComuneBean) arg2;
		return scbean.getId().toString();
	}



}
