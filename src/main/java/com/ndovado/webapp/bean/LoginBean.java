package com.ndovado.webapp.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LoginBean {
	private String mail;
	private String password;
	
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
	@Pattern(regexp = ".*\\S.*", message = "cannot be empty")
	@NotNull
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}	
}

