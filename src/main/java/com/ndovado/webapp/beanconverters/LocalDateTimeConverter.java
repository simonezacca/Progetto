package com.ndovado.webapp.beanconverters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@FacesConverter("LocalDateTimeConverter")
public class LocalDateTimeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy hh:mm");
		LocalDateTime date =  dtf.parseLocalDateTime(arg2);
		return date;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		LocalDateTime ldt = (LocalDateTime) arg2;
		return ldt.toString("dd/MM/yyyy hh:mm"); 
	}

	

}