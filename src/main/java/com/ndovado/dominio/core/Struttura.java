package com.ndovado.dominio.core;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ndovado.dominio.prenotazioni.TableauPrenotazioni;
import com.ndovado.dominio.servizi.Servizio;
import com.ndovado.tecservices.persistenza.base.IIdentificabile;

/**
 * Implementare i metodi equals() and hasCode()
 */
@Entity
@Table(name = "utente")
public class Struttura implements IIdentificabile {

	/**
	 * Default constructor
	 */
	public Struttura() {
	}

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer idStruttura;

	/**
	 * 
	 */
	@Column(name = "nome")
	private String nomeStruttura;

	/**
	 * 
	 */
	@ManyToOne
	private Gestore proprietario;

	/**
	 * 
	 */
	@OneToMany
	private Set<Servizio> serviziOfferti;

	/**
	 * 
	 */
	@Transient
	private TableauPrenotazioni tableau;

	/**
	 * 
	 */
	@OneToMany
	private Set<Camera> camereInserite;

	/**
	 * 
	 */
	@ManyToOne
    @JoinColumn(name="luogo_id")
	private Luogo luogoStruttura;

	/**
	 * @return
	 */
	public static Camera creaNuovaCamera() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public Gestore getProprietario() {
		return this.proprietario;
	}

	/**
	 * @param aGestore
	 */
	public void setProprietario(Gestore aGestore) {
		if (aGestore!=null) {
			this.proprietario = aGestore;
		}
	}

	/**
	 * 
	 */
	public void visualizzaDettagli() {
		// TODO implement here
	}

	/**
	 * @param aIdCamera 
	 * @return
	 */
	public Camera getCamera(Integer aIdCamera) {
		for (Camera c : camereInserite) {
			if (c.getId().equals(aIdCamera)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * @return
	 */
	public String getInformazioniPagamento() {
		// TODO implement here
		return "";
	}

	/**
	 * @return
	 */
	public Luogo getLuogo() {
		return this.luogoStruttura;
	}

	/**
	 * @param aLuogo
	 */
	public void setLuogo(Luogo aLuogo) {
		if (aLuogo!=null) {
			this.luogoStruttura=aLuogo;
		}
	}

	/**
	 * @param aListServizi
	 */
	public void addServiziBase(Set<Servizio> aListServizi) {
		// TODO implement here
	}

	/**
	 * @param aServizi
	 */
	public void addServiziAggiuntivi(Set<Servizio> aServizi) {
		// TODO implement here
	}

	/**
	 * 
	 */
	public void addFAQ() {
		// TODO implement here
	}

	/**
	 * 
	 */
	public void setCondizioniSoggiorno() {
		// TODO implement here
	}

	/**
	 * @return
	 */
	public Set<Servizio> getServiziOfferti() {
		return serviziOfferti;
	}

	/**
	 * @return
	 */
	public TableauPrenotazioni getTableauPrenotazioni() {
		return this.tableau;
	}

	/**
	 * @return the camereInserite
	 */
	public Set<Camera> getCamere() {
		return this.camereInserite;
	}

	/**
	 * @return the idStruttura
	 */
	public Integer getId() {
		return idStruttura;
	}

	/**
	 * @param idStruttura the idStruttura to set
	 */
	public void setIdStruttura(Integer idStruttura) {
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
		if (nomeStruttura!=null) {
			this.nomeStruttura = nomeStruttura;	
		}
	}
}