package br.uni.mette_service.Model;

import java.io.Serializable;

public class Usuario implements Serializable{		

	private int idUsuario;
	private int idPerfil;
	private String email;
	private String senha;
	
	public Usuario(){}
	public Usuario(int idUsuario, int idPerfil, String email, String senha) {
		super();
		this.idUsuario = idUsuario;
		this.idPerfil = idPerfil;
		this.email = email;
		this.senha = senha;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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
