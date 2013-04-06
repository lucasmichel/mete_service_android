package br.uni.mette_service.Model;

import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Toast;
import br.uni.mette_service.Model.Repositorio.Cliente.RepositorioCliente;


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
		
		//n ta retornando (n ta executando o retorno)
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
	
	public boolean validar ( Cliente Cliente) throws Exception{
		 boolean retorno = true;
		if (getNome().toString().equals("")){
			Toast.makeText(null, "Nome errado", Toast.LENGTH_SHORT).show();
			retorno = false;
					}
		
		return retorno;
		
	}
	
}

