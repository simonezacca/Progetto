package com.ndovado.webapp.beanconverters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.primefaces.component.calendar.Calendar;

@FacesConverter("LocalDateConverter")
public class LocalDateConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
		LocalDate date =  dtf.parseLocalDate(arg2);
		return date;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		LocalDate ld = (LocalDate) arg2;
//		String result = ld.toString("dd/MM/yyyy");
		String result = ld.toString("dd/MM/yyyy");
		return result;
	}

	

}
