package com.ndovado.standalone.model;

import java.io.Serializable;

public class Amministratore implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Amministratore [username=" + username + ", password=" + password + "]";
	}

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean isValid() {
		return username.length()>0 && password.length()>0;
	}

}
