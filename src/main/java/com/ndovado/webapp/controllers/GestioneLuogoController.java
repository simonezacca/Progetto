package com.ndovado.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import com.ndovado.dominio.core.CatalogoLuogo;
import com.ndovado.dominio.core.Luogo;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.mappers.LuogoMapper;
import com.ndovado.webapp.beans.core.LuogoBean;

public class GestioneLuogoController {

	private LuogoMapper lmapper;
	private CatalogoLuogo clmodel; 
	
	
	public GestioneLuogoController() {
		initLuogoUtils();
	}
	
	private void initLuogoUtils() {
		lmapper = LuogoMapper.getInstance();
		clmodel = CatalogoLuogo.getInstance();
	}
	
	public List<LuogoBean> getListaLuogoBeanPerProvincia(String codProvincia) {
		List<LuogoBean> lbeans = new ArrayList<LuogoBean>();
		if (codProvincia!=null && !codProvincia.isEmpty() && codProvincia.length()==2) {
			AppLogger.debug("Popolo lista luoghi bean da codProvincia= "+codProvincia);
			List<Luogo> lmodels = clmodel.getListaLuoghiPerProvincia(codProvincia);
			for (Luogo luogo : lmodels) {
				LuogoBean lb = lmapper.getBeanFromModel(luogo);
				lbeans.add(lb);
			}
		}
		return lbeans;
	}
	
	public List<String> getListaTutteProvinceStrings() {
		List<String> strings = clmodel.getListaTutteProvinceStrings();
		return strings;
	}
}
