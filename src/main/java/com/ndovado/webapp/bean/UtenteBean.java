package com.ndovado.webapp.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ndovado.webapp.shared.AttributeType;
import com.ndovado.webapp.shared.SetByAttribute;

public class UtenteBean {

	private Long id;
	private String nome;
	private String cognome;
	private String mail;
	private String password;
	
	private String ruolo;

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
	@SetByAttribute(type=AttributeType.CHECKED)
	@NotNull
	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
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
	
}
