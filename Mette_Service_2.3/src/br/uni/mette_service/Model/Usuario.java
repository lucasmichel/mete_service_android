package br.uni.mette_service.Model;

import br.uni.mette_service.Model.Repositorio.ModelClass;



public class Usuario extends ModelClass {

	private int id;
	protected String senha;
	protected String email;
	protected String idPerfil;// definir se é cliente ou acompanhante

	public Usuario() {
		super();
	}

	public Usuario(int id, String senha, String email, String idPerfil,
			int status, String mensagem) {
		super();
		this.id = id;
		this.senha = senha;
		this.email = email;
		this.idPerfil = idPerfil;
		this.setMensagem(mensagem);
		this.setStatus(status);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
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
