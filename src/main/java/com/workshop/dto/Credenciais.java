package com.workshop.dto;

import java.io.Serializable;

///DEFINE QUAIS AS CREDENCIAIS NECESSARIAS PARA FAZER LOGIN
public class Credenciais implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String senha;

	public Credenciais() {

	}

	public Credenciais(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
