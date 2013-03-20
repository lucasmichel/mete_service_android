package br.uni.mete_service.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import br.uni.mete_service.model.repositorio.RepositorioCliente;

public class Cliente extends Usuario {

	private String nome;
	private String cpf;
	private String telefone;
	private int status;
	private String mensagem;

	public Cliente() {
	}

	public Cliente(long id, String nome, String cpf, int tipo, String telefone,
			String email, String senha) {
		super(id, senha, email, tipo);
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public Usuario logarAndroid(Usuario usuario) throws JSONException {

		RepositorioCliente bd = RepositorioCliente.getInstance();

		return bd.logarAndroid(this);

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
