package com.ndovado.webapp.beans.core;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ndovado.helpers.core.TipoUtente;

@ManagedBean(name="utenteBean")
@SessionScoped
public class UtenteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id = new Long(0);
	private String nome;
	private String cognome;
	private String mail;
	private String password;
	
	private TipoUtente ruolo;
	private String homePageName;
	
	private boolean loggedIn = false;

	@Pattern(regexp = ".*\\S.*", message = "cannot be empty")
    @NotNull
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Pattern(regexp = ".*\\S.*", message = "cannot be empty")
    @NotNull
	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	@Pattern(regexp = ".*\\S.*", message = "cannot be empty")
    @NotNull
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	@Pattern(regexp = ".*\\S.*", message = "cannot be empty")
	@NotNull
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
	
	public boolean isNewBean() {
		return id==0 || id == null;
	}
	
	public TipoUtente getValoreLocatario() {
		return TipoUtente.LOCATARIO;
	}
	public TipoUtente getValoreGestore() {
		return TipoUtente.GESTORE;
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
	 * @return the ruolo
	 */
	public TipoUtente getRuolo() {
		return ruolo;
	}

	/**
	 * @param ruolo the ruolo to set
	 */
	public void setRuolo(TipoUtente ruolo) {
		this.ruolo = ruolo;
	}

	/**
	 * @return the homePageName
	 */
	public String getHomePageName() {
		if(this.ruolo!=null)
			return (this.ruolo.equals(TipoUtente.LOCATARIO)) ? "/areaPrivata?faces-redirect=true": "/gestore/areaGestore?faces-redirect=true" ;
		return null;
	}
}
