package com.ndovado.helpers.core;

import com.ndovado.dominio.core.Luogo;
import com.ndovado.webapp.beans.core.LuogoBean;

public class LuogoHelper {

	
	private static LuogoHelper instance;
	
	public static LuogoHelper getInstance() {
		if (instance==null) {
			instance = new LuogoHelper();
		}
		return instance;
	}
	
}
