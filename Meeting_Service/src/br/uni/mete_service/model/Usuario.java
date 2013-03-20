package br.uni.mete_service.model;

import java.io.Serializable;

public class Usuario implements Serializable{

	private long id;
	private String senha;
	private String email;
	private int tipo;// definir se é cliente ou acompanhante

	
	public Usuario() {	}

	public Usuario(long id, String senha, String email, int tipo) {
		this.id = id;
		this.senha = senha;
		this.email = email;
		this.tipo = tipo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
