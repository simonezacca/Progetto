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
	
	private List<LuogoBean> luoghiDisponibili;
	
	
	public GestioneLuogoController() {
		initLuogoUtils();
	}
	
	private void initLuogoUtils() {
		lmapper = LuogoMapper.getInstance();
		clmodel = CatalogoLuogo.getInstance();
		luoghiDisponibili = populateAllLuoghiBeans();
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
	
	private List<LuogoBean> populateAllLuoghiBeans() {
		List<LuogoBean> lbeans = new ArrayList<LuogoBean>();
		for (Luogo lmodel : clmodel.getAllLuogo()) {
			LuogoBean lbean = lmapper.getBeanFromModel(lmodel);
			lbeans.add(lbean);
		}
		return lbeans;
	}
	
	public List<LuogoBean> completaLuoghiDisponibili(String query) {
		List<LuogoBean> newLBeans = new ArrayList<LuogoBean>();
	
		String s = query.toLowerCase();
		for (LuogoBean lbean : luoghiDisponibili) {
			String nomeLb = lbean.getNomeComune().toLowerCase();
			if (nomeLb.startsWith(s)) {
				newLBeans.add(lbean);
			}
		}
		return newLBeans;
	}

	/**
	 * @return the luoghiDisponibili
	 */
	public List<LuogoBean> getLuoghiDisponibili() {
		return luoghiDisponibili;
	}

}
