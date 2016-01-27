package com.ndovado.webapp.beans.core;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="utenteBean")
@SessionScoped
public class UtenteBean implements Serializable, Identifiable{

	/**
	 * 
	 */
	private final static String AREA_UTENTE = "/locatario/index?faces-redirect=true";
	private final static String AREA_GESTORE = "/gestore/index?faces-redirect=true";
	private static final long serialVersionUID = 1L;
	
	private Long id = null;
	private String nome;
	private String cognome;
	private String mail;
	private String password;
	
	//private TipoUtente ruolo;
	private ARuoloBean ruolo = null;
	private String homePageName;
	
	private boolean loggedIn = false;
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	public Boolean isNewBean() {
		return id==0 || id == null;
	}

	/**
	 * @return the logged
	 */
	public boolean isLogged() {
		return loggedIn;
	}

	/**
	 * @param logged the logged to set
	 */
	public void setLogged(boolean logged) {
		this.loggedIn = logged;
	}
	
	
	public void cloneFrom(UtenteBean u) {
		if (u instanceof UtenteBean) {
			this.id = u.getId();
			this.nome = u.getNome();
			this.cognome = u.getCognome();
			this.mail = u.getMail();
			this.password = u.getPassword();
			this.setRuolo(u.getRuolo());
			this.loggedIn = u.isLogged();
		}
	}
	
	/**
	 * @return the homePageName
	 */
	public String getHomePageName() {
		if(this.getRuolo()!=null)
			return (this.getRuolo() instanceof LocatarioBean) ? AREA_UTENTE : AREA_GESTORE ;
		return null;
	}
	
	public UtenteBean() {
		
	}
	


	@Override
	public String toString() {
		return "UtenteBean [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", mail=" + mail + ", password="
				+ password + ", ruolo=" + ruolo + ", loggedIn=" + loggedIn + "]";
	}

	/**
	 * @return the ruolo
	 */
	public ARuoloBean getRuolo() {
		return ruolo;
	}

	/**
	 * @param ruolo the ruolo to set
	 */
	public void setRuolo(ARuoloBean ruolo) {
		this.ruolo = ruolo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((homePageName == null) ? 0 : homePageName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (loggedIn ? 1231 : 1237);
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((ruolo == null) ? 0 : ruolo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UtenteBean))
			return false;
		UtenteBean other = (UtenteBean) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (homePageName == null) {
			if (other.homePageName != null)
				return false;
		} else if (!homePageName.equals(other.homePageName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (loggedIn != other.loggedIn)
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
		if (ruolo == null) {
			if (other.ruolo != null)
				return false;
		} else if (!ruolo.equals(other.ruolo))
			return false;
		return true;
	}
	
}
