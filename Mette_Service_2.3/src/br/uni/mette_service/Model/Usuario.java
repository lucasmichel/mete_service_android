package br.uni.mette_service.Model;

import br.uni.mette_service.Model.Repositorio.ModelClass;



public class Usuario extends ModelClass {

	private int id;
	protected String senha;
	protected String email;
	protected String tipo;// definir se é cliente ou acompanhante

	public Usuario() {
		super();
	}

	public Usuario(int id, String senha, String email, String tipo,
			int status, String mensagem) {
		super();
		this.id = id;
		this.senha = senha;
		this.email = email;
		this.tipo = tipo;
		this.setMensagem(mensagem);
		this.setStatus(status);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
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
