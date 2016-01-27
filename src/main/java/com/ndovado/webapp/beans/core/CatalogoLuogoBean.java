package com.ndovado.webapp.beans.core;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.ndovado.dominio.core.CatalogoLuogo;
import com.ndovado.dominio.core.Luogo;
import com.ndovado.tecservices.mappers.LuogoMapper;

@ManagedBean(name="catalogoLuogoBean",eager=true)
@ApplicationScoped
public class CatalogoLuogoBean {
	
	private LuogoMapper lmapper = LuogoMapper.getInstance();
	private CatalogoLuogo clmodel = CatalogoLuogo.getInstance();
	private List<LuogoBean> listaLuoghiBean;
	

	public CatalogoLuogoBean() {
		popolaListaLuoghiBean();
	}
	
	private void popolaListaLuoghiBean() {
		listaLuoghiBean = new ArrayList<LuogoBean>();
		List<Luogo> lmodels = clmodel.getAllLuogo();
		for (Luogo luogo : lmodels) {
			LuogoBean lb = lmapper.getBeanFromModel(luogo);
			listaLuoghiBean.add(lb);
		}
	}
	
	public List<String> getValoriProvinceDisponibili() {
		List<String> province = new ArrayList<String>();
		for (LuogoBean lb : listaLuoghiBean) {
			String codProvincia = lb.getProvincia();
			if (!province.contains(codProvincia)) {
				province.add(codProvincia);
			}
		}
		return province;
	}
	
	public List<LuogoBean> getLuoghiBeanDisponibiliPerProvincia(String codProvincia) {
		List<LuogoBean> beansComuni = new ArrayList<LuogoBean>();
		for (LuogoBean lb : listaLuoghiBean) {
			if (lb.getProvincia().equals(codProvincia)) {
				beansComuni.add(lb);
			}
		}
		return beansComuni;
	}
}
