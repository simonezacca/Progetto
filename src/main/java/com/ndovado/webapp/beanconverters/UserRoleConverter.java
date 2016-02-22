package com.ndovado.webapp.beanconverters;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.ndovado.webapp.backingbean.RegistrazioneUtenteBackingBean;
import com.ndovado.webapp.beans.core.GestoreBean;

@ManagedBean(name="userRoleConverter")
//@FacesConverter("com.ndovado.webapp.beanconverters.userroleconveter")
public class UserRoleConverter implements Converter{ // NO_UCD (unused code)

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		Integer idRuolo = Integer.parseInt(arg2);
		if(idRuolo.equals(1)) {
			return new RegistrazioneUtenteBackingBean().getRuoliValues()[1];
		} else
			return new RegistrazioneUtenteBackingBean().getRuoliValues()[0];
			
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof GestoreBean) {
			return new String("1");
		} else 
			return new String("0");
	}

}
