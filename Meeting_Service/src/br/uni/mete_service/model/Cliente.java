package br.uni.mete_service.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import br.uni.mete_service.model.repositorio.Cliente.RepositorioCliente;

public class Cliente extends Usuario {

	private String nome;
	private String cpf;
	private String telefone;
	

	public Cliente() {
	}

	public Cliente(String id, String nome, String cpf, String tipo, String telefone,
			String email, String senha) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Cliente logarAndroid(Cliente cliente) throws JSONException {
		RepositorioCliente bd = RepositorioCliente.getInstance();
		return bd.logarAndroid(this);
	}

	public Cliente cadastrarCliente(Cliente cliente) throws JSONException {
		RepositorioCliente bd = RepositorioCliente.getInstance();
		return bd.cadastrarCliente(this);
	}

	private JSONObject converteParaJson() throws JSONException {
		JSONObject json = new JSONObject();
		JSONObject manJson = new JSONObject();
		manJson.put("email", this.getEmail());
		manJson.put("senha", this.getSenha());
		json.put("nomeJson", manJson);
		return json;
	}
}
