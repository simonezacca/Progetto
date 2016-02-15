package com.ndovado.dominio.prenotazioni;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Column;


import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.MetaValue;
import org.hibernate.annotations.Proxy;

import com.ndovado.dominio.core.Camera;
import com.ndovado.dominio.servizi.ServizioAggiuntivo;
import com.ndovado.tecservices.loggers.AppLogger;
import com.ndovado.tecservices.persistence.base.IPersistente;

/**
 * Implementare i metodi equals() and hasCode()
 */
@Entity
@Proxy(lazy=false)
@Table(name="linea_prenotazione")
public class LineaPrenotazione implements IPersistente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	/**
	 * 
	 */
	@ManyToOne
	@PrimaryKeyJoinColumn
	private Prenotazione prenotazioneCorrente;

	/**
	 * 
	 */
	
	@Any(metaColumn = @Column(name = "tipo_oggetto"),fetch=FetchType.EAGER)
	@AnyMetaDef(idType = "long", metaType = "integer", metaValues = {
	    @MetaValue(value = "1", targetEntity = Camera.class),
	    @MetaValue(value = "2", targetEntity = ServizioAggiuntivo.class)
	})
	@Cascade(CascadeType.MERGE)
	@JoinColumn(name="oggetto_id")
	private IPrenotabile oggettoPrenotato;

	/**
	 * Default constructor
	 */
	
	public LineaPrenotazione() {
		AppLogger.debug("Istanzio nuovo: "+this.getClass().getName());
	}

	public LineaPrenotazione(Prenotazione prenotazione) {
		if (prenotazione!=null) {
			this.prenotazioneCorrente = prenotazione;
		}
	}

	/**
	 * @param oggettoPrenotabile
	 */
	public void addOggettoPrenotato(IPrenotabile oggettoPrenotabile) {
		if (oggettoPrenotabile!=null) {
			this.oggettoPrenotato = oggettoPrenotabile;
		}
	}

	/**
	 * @return
	 */
	public Float getSubTotal() {
		return this.getOggettoPrenotato().getPrezzo();
	}

	/**
	 * @return the oggettoPrenotato
	 */
	public Prenotazione getPrenotazioneCorrente() {
		return this.prenotazioneCorrente;
	}

	/**
	 * @return the oggettoPrenotato
	 */
	public IPrenotabile getOggettoPrenotato() {
		return this.oggettoPrenotato;
	}

	@Override
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setPrenotazioneCorrente(Prenotazione p) {
		if (p!=null) {
			this.prenotazioneCorrente = p;
		}
	}

	/**
	 * @param oggettoPrenotato the oggettoPrenotato to set
	 */
	public void setOggettoPrenotato(IPrenotabile oggettoPrenotato) {
		this.oggettoPrenotato = oggettoPrenotato;
	}

	@Override
	public String toString() {
		return "LineaPrenotazione [id=" + id + ", oggettoPrenotato="
				+ oggettoPrenotato + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((oggettoPrenotato == null) ? 0 : oggettoPrenotato.hashCode());
		result = prime * result + ((prenotazioneCorrente == null) ? 0 : prenotazioneCorrente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LineaPrenotazione))
			return false;
		LineaPrenotazione other = (LineaPrenotazione) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (oggettoPrenotato == null) {
			if (other.oggettoPrenotato != null)
				return false;
		} else if (!oggettoPrenotato.equals(other.oggettoPrenotato))
			return false;
		if (prenotazioneCorrente == null) {
			if (other.prenotazioneCorrente != null)
				return false;
		} else if (!prenotazioneCorrente.equals(other.prenotazioneCorrente))
			return false;
		return true;
	}

}