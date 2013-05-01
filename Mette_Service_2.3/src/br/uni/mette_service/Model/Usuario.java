package br.uni.mette_service.Model;

import java.io.Serializable;

public class Usuario implements Serializable{	
	
	private int usuarioId;
	private int usuarioIdPerfil;
	private String email;
	private String senha;
	
	public Usuario() {}

	public Usuario(int usuarioId, int usuarioIdPerfil, String email, String senha) {
		this.usuarioId = usuarioId;
		this.usuarioIdPerfil = usuarioIdPerfil;
		this.email = email;
		this.senha = senha;		
	}
	
	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getUsuarioIdPerfil() {
		return usuarioIdPerfil;
	}

	public void setUsuarioIdPerfil(int usuarioIdPerfil) {
		this.usuarioIdPerfil = usuarioIdPerfil;
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
