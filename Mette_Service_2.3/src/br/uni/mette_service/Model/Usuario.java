package br.uni.mette_service.Model;

import java.io.Serializable;

import org.json.JSONException;


import br.uni.mette_service.Model.Repositorio.ModelClass;
import br.uni.mette_service.Model.Repositorio.RepositorioUsuario;



public class Usuario implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int id;
	protected String senha;
	protected String email;
	protected int idPerfil;

	public Usuario() {
		super();
	}

	public Usuario(int id, String senha, String email, int idPerfil) {
		super();
		this.id = id;
		this.senha = senha;
		this.email = email;
		this.idPerfil = idPerfil;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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


	public int getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}
	
	public ModelClass logarAndroid(ModelClass modelo) throws JSONException{
		RepositorioUsuario bd = RepositorioUsuario.getInstance();
		return bd.logarAndroid(modelo);
	
	}
}
