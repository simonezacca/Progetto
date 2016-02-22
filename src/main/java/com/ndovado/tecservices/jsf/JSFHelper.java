package com.ndovado.tecservices.jsf;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class JSFHelper {

	public static void put(String key, Object obj) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put(key, obj);
	}
	
	public static Object get(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(key);
	}
	
	public static void redirectTo(String url) {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect(context.getRequestContextPath()+url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
