package com.ndovado.dominio.core;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ndovado.tecservices.persistenza.base.IIdentificabile;
import com.ndovado.tecservices.persistenza.converter.RuoloConverter;

/**
 * Classe di dominio Utente
 */
@Entity
@Table(name = "utente")
public class Utente implements IIdentificabile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	public Integer getId() {
		return id;
	}

	/**
	 * 
	 */
	@Column(name = "nome")
	private String nome;

	/**
	 * 
	 */
	@Column(name = "cognome")
	private String cognome;

	/**
	 * 
	 */
	@Column(name = "mail")
	private String mail;

	/**
	 * 
	 */
	@Column(name = "password")
	private String password;

	/**
	 * 
	 */
	@Column
	@Convert(converter = RuoloConverter.class)
	private ARuolo ruolo = ARuolo.getRuoloLocatario();

	/**
	 * Default constructor
	 */
	public Utente() {
	}
	/**
	 * 
	 * @param aCognome
	 * @param aNome
	 */
	public Utente(String aCognome, String aNome) {
		if (aCognome!=null && aNome!=null) {
			this.cognome=aCognome;
			this.nome = aNome;
		}
	}
	/**
	 * @return
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * @param aNome
	 */
	public void setNome(String aNome) {
		if (aNome!=null) {
			this.nome = aNome;
		}
	}

	/**
	 * @return
	 */
	public String getCognome() {
		return this.cognome;
	}

	/**
	 * @param aCognome
	 */
	public void setCognome(String aCognome) {
		if (aCognome!=null) {
			this.cognome = aCognome;
		}
	}

	/**
	 * @return
	 */
	public String getMail() {
		return this.mail;
	}

	/**
	 * @param aMail
	 */
	public void setMail(String aMail) {
		if (aMail!=null) {
			this.mail = aMail;
		}
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param aPassword
	 */
	public void setPassword(String aPassword) {
		if (aPassword!=null) {
			this.password = aPassword;
		}
	}

	/**
	 * @return
	 */
	public ARuolo getRuolo() {
		return this.ruolo;
	}

	/**
	 * @param aRuolo
	 */
	public void setRuolo(ARuolo aRuolo) {
		if (aRuolo!=null) {
			this.ruolo = aRuolo;
		}
	}
	
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


}