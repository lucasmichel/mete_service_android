package mete_service.model;

import java.io.Serializable;

public class Usuario implements Serializable{

	private long id;
	private String senha;
	private String email;

	public Usuario(String senha, String email) {
		this(0, senha, email);

	}

	public Usuario(long id, String senha, String email) {
		this.id = id;
		this.senha = senha;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
