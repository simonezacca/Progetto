package com.ndovado.webapp.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ndovado.bridge.IBean;

public class LoginBean implements IBean {
	private String mail;
	private String password;
	private String ruolo;
	
	/**
	 * @return the password
	 */
	@Pattern(regexp = ".*\\S.*", message = "cannot be empty")
	@NotNull
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the mail
	 */
	@Pattern(regexp = ".*\\S.*", message = "cannot be empty")
	@NotNull
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the ruolo
	 */
	public String getRuolo() {
		return ruolo;
	}
	/**
	 * @param ruolo the ruolo to set
	 */
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}	
}

