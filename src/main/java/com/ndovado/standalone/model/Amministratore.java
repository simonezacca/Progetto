package com.ndovado.standalone.model;

public class Amministratore {
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

}
