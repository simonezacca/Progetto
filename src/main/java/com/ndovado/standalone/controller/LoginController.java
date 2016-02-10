package com.ndovado.standalone.controller;

public class LoginController {

	
	public Boolean verificaCoppiaLogin(String username, String password) {
		return username.equals("admin") && password.equals("admin");
	}
}
