package com.ndovado.webapp.beanconverters;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.ndovado.webapp.beans.servizi.ServizioAggiuntivoBean;
import com.ndovado.webapp.beans.servizi.ServizioBaseBean;

@ManagedBean(name="tipologiaServizioBeanConverter")
public class TipologiaServizioBeanConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2.equals("1")) {
			return new ServizioAggiuntivoBean();
		} else {
			return new ServizioBaseBean();
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if(arg2 instanceof ServizioAggiuntivoBean)
			return "1";
		else
			return "0";
	}



}
