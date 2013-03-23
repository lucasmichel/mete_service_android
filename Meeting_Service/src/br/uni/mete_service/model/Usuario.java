package br.uni.mete_service.model;


import br.uni.mete_service.model.repositorio.ModelClass;

public class Usuario extends ModelClass {

	private String id;
	private String senha;
	private String email;
	private String tipo;// definir se é cliente ou acompanhante

	public Usuario() {
		super();
	}

	public Usuario(String id, String senha, String email, String tipo,
			int status, String mensagem) {
		super();
		this.id = id;
		this.senha = senha;
		this.email = email;
		this.tipo = tipo;
		this.setMensagem(mensagem);
		this.setStatus(status);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
