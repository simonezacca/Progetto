package com.ndovado.dominio.core;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OrderBy;

import com.ndovado.dominio.prenotazioni.TableauPrenotazioni;
import com.ndovado.dominio.servizi.DettaglioServizio;
import com.ndovado.dominio.servizi.ServizioComune;
import com.ndovado.tecservices.persistenza.base.IPersistente;

/**
 * Modella l'entità di dominio Struttura
 */
@Entity
@Table(name = "struttura")
public class Struttura implements IPersistente {

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;

	/**
	 * Identificativo di tipo <code>Integer</code> utilizzato per il mapping ORM
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	/**
	 * Il nome della struttura
	 */
	@Column(name = "nome", nullable = false)
	private String nomeStruttura;

	/**
	 * Riferimento ad un'istanza di tipo <code>Gestore</code>, utente gestore della struttura
	 */

	@ManyToOne
	@PrimaryKeyJoinColumn
	private Gestore gestore;

	/**
	 * Insieme dei servizi offerti dalla struttura
	 */
	@OneToMany(mappedBy = "struttura",cascade=CascadeType.ALL)
	//@OrderBy(clause="iddettaglio_servizio")
	private List<DettaglioServizio> serviziOfferti;

	/**
	 * Riferimento ad un'istanza del tableau prenotazioni, responsabile della gestione delle prenotazioni nelle camere
	 */

	//@OneToOne(cascade=CascadeType.ALL,mappedBy = "struttura")
	@Transient
	private TableauPrenotazioni tableau;

	/**
	 * Insieme delle camera collegate alla struttura
	 */
	@OneToMany(mappedBy = "struttura",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	//@OrderBy(clause="id")
	private List<Camera> camereInserite;

	/**
	 * Luogo nel quale è situata la struttura
	 */
	@ManyToOne
	@PrimaryKeyJoinColumn
	private Luogo luogoStruttura;

	/**
	 * Costruttore di default
	 */
	public Struttura() {
		setCamereInserite(new ArrayList<Camera>());
		setServiziOfferti(new ArrayList<DettaglioServizio>());
		
		setTableau(new TableauPrenotazioni(this));
	}

	/**
	 * @return una nuova camera, la nuova camera è automaticamente collegata alla struttura corrente
	 */
	public Camera creaNuovaCamera() {
		return new Camera(this);
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
		for (Camera c : getCamereInserite()) {
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
		for (Camera c : getCamereInserite()) {
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
	public Luogo getLuogoStruttura() {
		return this.luogoStruttura;
	}

	/**
	 * @param aLuogo
	 */
	public void setLuogoStruttura(Luogo aLuogo) {
		// imposto il comune per la struttura corrente
		this.luogoStruttura = aLuogo;
		// aggiungo la struttura corrente all'elenco delle strutture 
		// inserite nel luogo
		aLuogo.addStruttura(this);
	}

	/**
	 * @param servizio
	 */
	public void addServizioBase(ServizioComune servizio) {
		if (servizio!=null) {
			DettaglioServizio dso = new DettaglioServizio(this, servizio);
			this.getServiziOfferti().add(dso);
		}
	}
	
	/**
	 * @param servizio
	 */
	public void addServizioAggiuntivo(ServizioComune servizio, Float prezzo) {
		if (servizio!=null && prezzo >= 0) {
			DettaglioServizio dso = new DettaglioServizio(this, servizio, prezzo);
			this.getServiziOfferti().add(dso);
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
	public List<DettaglioServizio> getServiziOfferti() {
		return serviziOfferti;
	}

	/**
	 * @return
	 */
	public TableauPrenotazioni getTableauPrenotazioni() {
		return this.getTableau();
	}


	/** Ritorna il nome della struttura
	 * @return il nome della struttura
	 */
	public String getNomeStruttura() {
		return nomeStruttura;
	}

	/** Imposta il nome della struttura
	 * @param nomeStruttura il nuovo nome della struttura
	 */
	public void setNomeStruttura(String nomeStruttura) {
		if (nomeStruttura!=null) {
			this.nomeStruttura = nomeStruttura;	
		}
	}
	/**
	 * Aggiunge una camera alla struttura
	 * @param c la camera da aggiungere
	 */
	public void addCamera(Camera c) {
		if (c!=null) {
			// aggiungo la camera c all'elenco delle camere associate alla struttura
			this.getCamereInserite().add(c);
			// imposto la struttura corrente come struttura associeta alla camera aggiunta
			c.setStruttura(this);
		}
	}
	/**
	 * Rimuove una camera dalla struttura
	 * @param c la camera da rimuovere
	 */
	public void removeCamera(Camera c) {
		if (this.getCamereInserite().contains(c)) {
			this.getCamereInserite().remove(c);
		}
	}

	

	@Override
	public String toString() {
		return "Struttura [idStruttura=" + getId() + ", nomeStruttura=" + nomeStruttura + ", gestore=" + getGestore()
				+ ", luogoStruttura=" + luogoStruttura + "]";
	}

	/**
	 * @return the id
	 */
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((camereInserite == null) ? 0 : camereInserite.hashCode());
		result = prime * result + ((gestore == null) ? 0 : gestore.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((luogoStruttura == null) ? 0 : luogoStruttura.hashCode());
		result = prime * result + ((nomeStruttura == null) ? 0 : nomeStruttura.hashCode());
		result = prime * result + ((serviziOfferti == null) ? 0 : serviziOfferti.hashCode());
		result = prime * result + ((tableau == null) ? 0 : tableau.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Struttura))
			return false;
		Struttura other = (Struttura) obj;
		if (getCamereInserite() == null) {
			if (other.camereInserite != null)
				return false;
		} else if(!getCamereInserite().equals(other.getCamereInserite()))
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
		if (serviziOfferti == null) {
			if (other.serviziOfferti != null)
				return false;
		} else if (!serviziOfferti.containsAll(other.serviziOfferti))
			return false;
		if (tableau == null) {
			if (other.tableau != null)
				return false;
		} else if (!tableau.equals(other.tableau))
			return false;
		return true;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the camereInserite
	 */
	public List<Camera> getCamereInserite() {
		return camereInserite;
	}

	/**
	 * @param camereInserite the camereInserite to set
	 */
	public void setCamereInserite(List<Camera> camereInserite) {
		this.camereInserite = camereInserite;
	}

	/**
	 * @return the tableau
	 */
	public TableauPrenotazioni getTableau() {
		return tableau;
	}

	/**
	 * @param tableau the tableau to set
	 */
	public void setTableau(TableauPrenotazioni tableau) {
		this.tableau = tableau;
	}

	/**
	 * @param serviziOfferti the serviziOfferti to set
	 */
	public void setServiziOfferti(List<DettaglioServizio> serviziOfferti) {
		this.serviziOfferti = serviziOfferti;
	}

	/**
	 * @return the gestore
	 */
	public Gestore getGestore() {
		return gestore;
	}

	/**
	 * @param gestore the gestore to set
	 */
	public void setGestore(Gestore gestore) {
		this.gestore = gestore;
	} 
	
	
}