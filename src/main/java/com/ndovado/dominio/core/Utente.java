package com.ndovado.dominio.core;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.ndovado.tecservices.persistenza.base.IPersistente;

/**
 * Modella l'entità di dominio Utente
 */

@Entity
@Table(name ="utente")
@NamedQueries({
	   @NamedQuery(
	        name = "cercaUtentePerMail", 
	        query="FROM Utente u WHERE u.mail LIKE :mail")
	})
public class Utente implements IPersistente {

	public static final String FIND_BY_EMAIL = "cercaUtentePerMail";


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Identificativo di tipo <code>Integer</code> utilizzato per il mapping ORM
	 */

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long idutente;

	/**
	 * Nome dell'utente
	 */
	@Column(name="nome", nullable=false)
	private String nome;

	/**
	 * Cognome dell'utente
	 */
	@Column(name="cognome", nullable=false)
	private String cognome;

	/**
	 * Email dell'utente utilizzata come username per il login al sistema
	 */
	@Column(name="mail",nullable=false)
	private String mail;

	/**
	 * Password dell'utente
	 */
	@Column(name="password",nullable=true)
	private String password;

	/**
	 * Riferimento ad un'instanza di tipo <code>ARuolo</code> per indicare il ruolo dell'utente nel sistema
	 */

	@OneToOne(cascade=CascadeType.ALL)
	private ARuolo ruolo = ARuolo.getRuoloLocatario();


	/**
	 * Costrutto di default
	 */
	public Utente() {
	}
	/**
	 * Costruttore paramentrico
	 * @param aCognome il cognome del nuovo utente
	 * @param aNome il nome del nuovo utente
	 */
	public Utente(String aCognome, String aNome) {
		if (aCognome!=null && aNome!=null) {
			this.cognome=aCognome;
			this.nome = aNome;
		}
	}
	public Long getId() {
		return idutente;
	}
	/**
	 * @return il nome dell'utente
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * @param aNome il nuovo nome da assegnare all'utente
	 */
	public void setNome(String aNome) {
		if (aNome!=null) {
			this.nome = aNome;
		}
	}

	/**
	 * @return il cognome dell'utente
	 */
	public String getCognome() {
		return this.cognome;
	}

	/**
	 * @param aCognome il nuovo cognome da assegnare all'utente
	 */
	public void setCognome(String aCognome) {
		if (aCognome!=null) {
			this.cognome = aCognome;
		}
	}

	/**
	 * @return l'indirizzo mail assegnato all'utente
	 */
	public String getMail() {
		return this.mail;
	}

	/**
	 * @param aMail nuovo indirizzo mail da assegnare all'utente
	 */
	public void setMail(String aMail) {
		if (aMail!=null) {
			this.mail = aMail;
		}
	}

	/**
	 * @return la password dell'utente
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param aPassword la nuova password da assegnare all'utente
	 */
	public void setPassword(String aPassword) {
		if (aPassword!=null) {
			this.password = aPassword;
		}
	}

	/**
	 * @return il ruolo assegnato all'utente, se il ruolo non è inizializzato ritorna il ruolo <code>Locatario</code>
	 */
	public ARuolo getRuolo() {
		return this.ruolo;
	}

	/**
	 * @param aRuolo il nuovo ruolo da assegnare all'utente
	 */
	public void setRuolo(ARuolo aRuolo) {
		if (aRuolo!=null) {
			this.ruolo = aRuolo;
			aRuolo.setUtente(this);
		}
	}
	/**
	 * @return una rappresentazione testuale dell'utente nel formato
	 * Utente [nome,cognome,mail,ruolo]
	 */
	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", mail=" + mail + ", Ruolo=" + ruolo.toString()+" ]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Utente))
			return false;
		Utente other = (Utente) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	protected void setId(Long idUtente) {
		this.idutente = idUtente;
	}

}