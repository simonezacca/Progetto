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
 * Modella l'entità di dominio Struttura
 */
@Entity
@Table(name = "utente")
public class Struttura implements IIdentificabile {

	/**
	 * Costruttore di default
	 */
	public Struttura() {
		camereInserite = new HashSet<Camera>();
		serviziOfferti = new HashSet<Servizio>();
		
		tableau = new TableauPrenotazioni(this);
	}

	/**
	 * Identificativo di tipo <code>Integer</code> utilizzato per il mapping ORM
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long idStruttura;

	/**
	 * Il nome della struttura
	 */
	@Column(name = "nome")
	private String nomeStruttura;

	/**
	 * Riferimento ad un'istanza di tipo <code>Gestore</code>, utente gestore della struttura
	 */
	@ManyToOne
	private Gestore proprietario;

	/**
	 * Insieme dei servizi offerti dalla struttura
	 */
	@OneToMany
	private Set<Servizio> serviziOfferti;

	/**
	 * Riferimento ad un'istanza del tableau prenotazioni, responsabile della gestione delle prenotazioni nelle camere
	 */
	@Transient
	private TableauPrenotazioni tableau;

	/**
	 * Insieme delle camera collegate alla struttura
	 */
	@OneToMany
	private Set<Camera> camereInserite;

	/**
	 * Luogo nel quale è situata la struttura
	 */
	@ManyToOne
    @JoinColumn(name="luogo_id")
	private Luogo luogoStruttura;

	/**
	 * @return una nuova camera
	 */
	public static Camera creaNuovaCamera() {
		// TODO implement here
		return null;
	}

	/**
	 * @return il gestore della struttura
	 */
	public Gestore getProprietario() {
		return this.proprietario;
	}

	/**Imposta il gestore della struttura
	 * @param aGestore il gestore da assegnare alla struttura
	 */
	public void setProprietario(Gestore aGestore) {
		if (aGestore!=null) {
			// imposto il proprietario corrente
			this.proprietario = aGestore;
			// aggiungo la struttura corrente all'elenco delle struttura gestite da aGestore
			aGestore.gestisciStruttura(this);
		}
	}

	/**
	 * 
	 */
	public void visualizzaDettagli() {
		// TODO implement here
	}

	/**
	 * Ritorna un'istanza di tipo camera
	 * @param aIdCamera  l'identificativo numerico della camera
	 * @return la camera con l'identificativo corrispondente
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
	 * 
	 * @param aCamera
	 * @return
	 */
	public Camera getCamera(Camera aCamera) {
		for (Camera c : camereInserite) {
			if (c.equals(aCamera)) {
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
			// imposto il comune per la struttura corrente
			this.luogoStruttura=aLuogo;
			// aggiungo la struttura corrente all'elenco delle strutture 
			// inserite nel luogo
			aLuogo.addStruttura(this);
		}
	}

	/**
	 * @param aListServizi
	 */
	public void addServiziBase(Set<Servizio> aListServizi) {
		if (aListServizi!=null) {
			this.serviziOfferti.addAll(aListServizi);
		}
	}

	/**
	 * @param aListServizi
	 */
	public void addServiziAggiuntivi(Set<Servizio> aListServizi) {
		if (aListServizi!=null) {
			this.serviziOfferti.addAll(aListServizi);
		}
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
	public Long getId() {
		return idStruttura;
	}

	/**
	 * @param idStruttura the idStruttura to set
	 */
	protected void setId(Long idStruttura) {
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
	
	public void addCamera(Camera c) {
		if (c!=null) {
			// aggiungo la camera c all'elenco delle camere associate alla struttura
			this.getCamere().add(c);
			// imposto la struttura corrente come struttura associeta alla camera aggiunta
			c.setStruttura(this);
		}
	}
	
	public void removeCamera(Camera c) {
		if (this.camereInserite.contains(c)) {
			this.camereInserite.remove(c);
		}
	} 
}