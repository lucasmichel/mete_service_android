package mete_service.model;

import java.io.Serializable;

public class Cliente extends Usuario implements Serializable {

	private String nome;
	private String cpf;
	private String telefone;

	public Cliente() {
		super();
	}

	public Cliente(long id, String senha, String email, String nome,
			String cpf, String telefone) {
		super(id, senha, email);
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
