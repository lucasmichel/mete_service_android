package br.uni.mete_service.model;

import java.io.Serializable;

public class Cliente extends Usuario {

	private String nome;
	private String cpf;
	private String telefone;

	
	public Cliente(long id, String senha, String email) {
		super(id, senha, email);
		// TODO Auto-generated constructor stub
	}

	public Cliente(String senha, String email, String nome,
			String cpf, String telefone) {
		super( senha, email);
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

}
