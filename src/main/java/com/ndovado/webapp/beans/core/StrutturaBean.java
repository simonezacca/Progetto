package com.ndovado.webapp.beans.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;

import com.ndovado.dominio.core.Camera;
import com.ndovado.dominio.core.Struttura;

@ManagedBean(name="strutturaBean")
public class StrutturaBean {

	private Long idStruttura = new Long(0);
	private String nomeStruttura;
	private LuogoBean luogoStruttura;
	
	private List<CameraBean> camere;
	
	public StrutturaBean() {
		setCamere(new ArrayList<CameraBean>());
	}
	
	public StrutturaBean(Struttura s) {
		setCamere(new ArrayList<CameraBean>());
		fillBeanFromModel(s);
		
	}
	
	private void fillBeanFromModel(Struttura s) {
		if (s instanceof Struttura && s!=null) {
			this.idStruttura = s.getId();
			this.nomeStruttura = s.getNomeStruttura();
			this.luogoStruttura = new LuogoBean();
			
			List<Camera> camereModel = (List<Camera>) s.getCamere();
			for (Camera camera : camereModel) {
				getCamere().add(new CameraBean(camera));
			}
		}
	}
	
	/**
	 * @return the idStruttura
	 */
	public Long getIdStruttura() {
		return idStruttura;
	}
	/**
	 * @param idStruttura the idStruttura to set
	 */
	public void setIdStruttura(Long idStruttura) {
		this.idStruttura = idStruttura;
	}
	/**
	 * @return the nomeStruttura
	 */
	public String getNomeStruttura() {
		return nomeStruttura;
	}
	/**
	 * @param nomeStruttura the nomeStruttura to set
	 */
	public void setNomeStruttura(String nomeStruttura) {
		this.nomeStruttura = nomeStruttura;
	}
	/**
	 * @return the luogoStruttura
	 */
	public LuogoBean getLuogoStruttura() {
		return luogoStruttura;
	}
	/**
	 * @param luogoStruttura the luogoStruttura to set
	 */
	public void setLuogoStruttura(LuogoBean luogoStruttura) {
		this.luogoStruttura = luogoStruttura;
	}

	/**
	 * @return the camere
	 */
	public List<CameraBean> getCamere() {
		return camere;
	}

	/**
	 * @param camere the camere to set
	 */
	public void setCamere(List<CameraBean> camere) {
		this.camere = camere;
	}
	
	public boolean isNewBean() {
		return this.idStruttura == 0 || this.idStruttura ==null;
	}
		
	
}
