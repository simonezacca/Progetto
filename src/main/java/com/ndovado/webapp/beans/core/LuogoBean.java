package com.ndovado.webapp.beans.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.ndovado.dominio.core.Luogo;

@ManagedBean(name="luogoBean")
public class LuogoBean implements Serializable, Identifiable{
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CAP == null) ? 0 : CAP.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomeComune == null) ? 0 : nomeComune.hashCode());
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
		result = prime * result + ((struttureInLuogo == null) ? 0 : struttureInLuogo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LuogoBean))
			return false;
		LuogoBean other = (LuogoBean) obj;
		if (CAP == null) {
			if (other.CAP != null)
				return false;
		} else if (!CAP.equals(other.CAP))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeComune == null) {
			if (other.nomeComune != null)
				return false;
		} else if (!nomeComune.equals(other.nomeComune))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		if (struttureInLuogo == null) {
			if (other.struttureInLuogo != null)
				return false;
		} else if (!struttureInLuogo.equals(other.struttureInLuogo))
			return false;
		return true;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id = null;
	private String nomeComune;
	private String CAP;
	private String provincia;
	
	private List<StrutturaBean> struttureInLuogo = new ArrayList<StrutturaBean>();
	
	public LuogoBean() {
		// TODO Auto-generated constructor stub
	}

	public LuogoBean(Luogo l) {
		fillBeanFromModel(l);
	}
	
	protected void fillBeanFromModel(Luogo l) {
		if (l!=null) {
			this.setId(l.getId());
			this.nomeComune = l.getNomeComune();
			this.CAP = l.getCAP();
			this.provincia = l.getProvincia();
		}
	}
	/**
	 * @return the nomeComune
	 */
	public String getNomeComune() {
		return nomeComune;
	}
	/**
	 * @param nomeComune the nomeComune to set
	 */
	public void setNomeComune(String nomeComune) {
		this.nomeComune = nomeComune;
	}
	/**
	 * @return the cap
	 */
	public String getCAP() {
		return CAP;
	}
	/**
	 * @param cap the cap to set
	 */
	public void setCAP(String cap) {
		this.CAP = cap;
	}
	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}
	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Boolean isNewBean() {
		return this.id==0 || this.id ==null;
	}
	
	/**
	 * @return the idLuogo
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param idLuogo the idLuogo to set
	 */
	public void setId(Long idLuogo) {
		this.id = idLuogo;
	}

	@Override
	public String toString() {
		return "LuogoBean [idLuogo=" + id + ", nomeComune=" + nomeComune + ", CAP=" + CAP + ", provincia="
				+ provincia + "]";
	}

	/**
	 * @return the struttureInLuogo
	 */
	public List<StrutturaBean> getStruttureInLuogo() {
		return struttureInLuogo;
	}

	/**
	 * @param struttureInLuogo the struttureInLuogo to set
	 */
	public void setStruttureInLuogo(List<StrutturaBean> struttureInLuogo) {
		this.struttureInLuogo = struttureInLuogo;
	}

}
