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
import com.ndovado.dominio.servizi.DettagliServizioOfferto;
import com.ndovado.dominio.servizi.ServizioComune;
import com.ndovado.tecservices.persistenza.base.IPersistente;

/**
 * Modella l'entità di dominio Struttura
 */
@Entity
@Table(name = "utente")
public class Struttura implements IPersistente {

	/**
	 * Costruttore di default
	 */
	public Struttura() {
		camereInserite = new HashSet<Camera>();
		serviziOfferti = new HashSet<DettagliServizioOfferto>();
		
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
	private Set<DettagliServizioOfferto> serviziOfferti;

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
	 * @param servizio
	 */
	public void addServizioBase(ServizioComune servizio) {
		if (servizio!=null) {
			DettagliServizioOfferto dso = new DettagliServizioOfferto(this, servizio);
			this.serviziOfferti.add(dso);
		}
	}
	
	/**
	 * @param servizio
	 */
	public void addServizioAggiuntivo(ServizioComune servizio, Float prezzo) {
		if (servizio!=null && prezzo >= 0) {
			DettagliServizioOfferto dso = new DettagliServizioOfferto(this, servizio, prezzo);
			this.serviziOfferti.add(dso);
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
	public Set<DettagliServizioOfferto> getServiziOfferti() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((camereInserite == null) ? 0 : camereInserite.hashCode());
		result = prime * result + ((idStruttura == null) ? 0 : idStruttura.hashCode());
		result = prime * result + ((luogoStruttura == null) ? 0 : luogoStruttura.hashCode());
		result = prime * result + ((nomeStruttura == null) ? 0 : nomeStruttura.hashCode());
		result = prime * result + ((proprietario == null) ? 0 : proprietario.hashCode());
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
		if (camereInserite == null) {
			if (other.camereInserite != null)
				return false;
		} else if (!camereInserite.equals(other.camereInserite))
			return false;
		if (idStruttura == null) {
			if (other.idStruttura != null)
				return false;
		} else if (!idStruttura.equals(other.idStruttura))
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
		if (proprietario == null) {
			if (other.proprietario != null)
				return false;
		} else if (!proprietario.equals(other.proprietario))
			return false;
		if (serviziOfferti == null) {
			if (other.serviziOfferti != null)
				return false;
		} else if (!serviziOfferti.equals(other.serviziOfferti))
			return false;
		if (tableau == null) {
			if (other.tableau != null)
				return false;
		} else if (!tableau.equals(other.tableau))
			return false;
		return true;
	} 
}