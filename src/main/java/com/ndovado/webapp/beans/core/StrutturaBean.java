package com.ndovado.webapp.beans.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

import com.ndovado.tecservices.loggers.AppLogger;



@ManagedBean(name="strutturaBean")
public class StrutturaBean implements Serializable, Identifiable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private Long id = null;
	private String nomeStruttura;
	private LuogoBean luogoStruttura;
	
	private String descrizioneStruttura;
	
	private String indirizzoLuogo;
	
	private List<CameraBean> camereInserite;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((camereInserite == null) ? 0 : camereInserite.hashCode());
		result = prime * result + ((elencoServizi == null) ? 0 : elencoServizi.hashCode());
		result = prime * result + ((gestore == null) ? 0 : gestore.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((luogoStruttura == null) ? 0 : luogoStruttura.hashCode());
		result = prime * result + ((nomeStruttura == null) ? 0 : nomeStruttura.hashCode());
		result = prime * result + ((tableau == null) ? 0 : tableau.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof StrutturaBean))
			return false;
		StrutturaBean other = (StrutturaBean) obj;
		if (camereInserite == null) {
			if (other.camereInserite != null)
				return false;
		} else if (!camereInserite.equals(other.camereInserite))
			return false;
		if (elencoServizi == null) {
			if (other.elencoServizi != null)
				return false;
		} else if (!elencoServizi.equals(other.elencoServizi))
			return false;
		if (gestore == null) {
			if (other.gestore != null)
				return false;
		} else if (!gestore.equals(other.gestore))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (luogoStruttura == null) {
			if (other.luogoStruttura != null)
				return false;
		} else if (!luogoStruttura.equals(other.luogoStruttura))
			return false;
		if (nomeStruttura == null) {
			if (other.nomeStruttura != null)
				return false;
		} else if (!nomeStruttura.equals(other.nomeStruttura))
			return false;
		if (tableau == null) {
			if (other.tableau != null)
				return false;
		} else if (!tableau.equals(other.tableau))
			return false;
		return true;
	}

	private GestoreBean gestore;
	
	private Object tableau;
	private Object elencoServizi;
	
	public StrutturaBean() {
		setCamereInserite(new ArrayList<CameraBean>());
	}
	
	public StrutturaBean(GestoreBean gestore) {
		this.gestore = gestore;
		AppLogger.debug("Imposto gestore per StrutturaBean:"+gestore);
		setCamereInserite(new ArrayList<CameraBean>());
	}
	
	/**
	 * @param idStruttura the idStruttura to set
	 */
	public void setId(Long idStruttura) {
		this.id = idStruttura;
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
	 * @return the gestore
	 */
	public GestoreBean getGestore() {
		return gestore;
	}

	/**
	 * @param gestore the gestore to set
	 */
	public void setGestore(GestoreBean gestore) {
		this.gestore = gestore;
	}
	
	public void addCameraBean(CameraBean cb) {
		this.getCamereInserite().add(cb);
		cb.setStruttura(this);
	}
	
	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		
		risultato.append("StrutturaBean[");
		risultato.append("id: "+this.id+", ");
		risultato.append("nome: "+this.nomeStruttura+", ");
		//risultato.append("gestore: "+this.gestore.getMail()+", ");
		risultato.append("luogo: "+this.luogoStruttura.getNomeComune()+"]\n\t");
		
		risultato.append("CamereBeans:\n\t");
		List<CameraBean> camereBeans = this.getCamereInserite();
		for (CameraBean cb : camereBeans) {
			risultato.append(cb+"\n\t");
		}
		
		return risultato.toString();
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public Boolean isNewBean() {
		return this.id==0 || this.id == null;
	}

	/**
	 * @return the tableau
	 */
	public Object getTableau() {
		return tableau;
	}

	/**
	 * @param tableau the tableau to set
	 */
	public void setTableau(Object tableau) {
		this.tableau = tableau;
	}

	/**
	 * @return the elencoServizi
	 */
	public Object getElencoServizi() {
		return elencoServizi;
	}

	/**
	 * @param elencoServizi the elencoServizi to set
	 */
	public void setElencoServizi(Object elencoServizi) {
		this.elencoServizi = elencoServizi;
	}

	/**
	 * @return the camereInserite
	 */
	public List<CameraBean> getCamereInserite() {
		return camereInserite;
	}

	/**
	 * @param camereInserite the camereInserite to set
	 */
	public void setCamereInserite(List<CameraBean> camereInserite) {
		this.camereInserite = camereInserite;
	}

	/**
	 * @return the descrizioneStruttura
	 */
	public String getDescrizioneStruttura() {
		return descrizioneStruttura;
	}

	/**
	 * @param descrizioneStruttura the descrizioneStruttura to set
	 */
	public void setDescrizioneStruttura(String descrizioneStruttura) {
		this.descrizioneStruttura = descrizioneStruttura;
	}

	/**
	 * @return the indirizzoLuogo
	 */
	public String getIndirizzoLuogo() {
		return indirizzoLuogo;
	}

	/**
	 * @param indirizzoLuogo the indirizzoLuogo to set
	 */
	public void setIndirizzoLuogo(String indirizzoLuogo) {
		this.indirizzoLuogo = indirizzoLuogo;
	}
		
	
}
