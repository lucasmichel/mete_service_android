package br.uni.mette_service.Model;

import java.io.Serializable;

public class Usuario implements Serializable{		

	private int id;
	private int idPerfil;
	private String email;
	private String senha;
	
	public Usuario(){}
	public Usuario(int id, int idPerfil, String email, String senha) {
		super();
		this.id = id;
		this.idPerfil = idPerfil;
		this.email = email;
		this.senha = senha;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
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
